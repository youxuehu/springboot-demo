package com.example.keyvalue.win;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

/**
 * 计算每个班级的平均值
 */
public class Main {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.addResource("core-site.xml");
        conf.addResource("hdfs-site.xml");
        conf.addResource("yarn-site.xml");
        conf.addResource("mapred-site.xml");
        conf.set("fs.defaultFS", "hdfs://master:9000");
        // 针对 key value 类型的格式 KeyValueTextInputFormat
        /**
         * [root@master ~]# cat avg.txt
         * class101:zhangsan 94
         * class101:lisi 77
         * class102:zhaoliu 88
         * class102:wanger 98
         * class103:jack 100
         * class104:tom 68
         * class105:tom 18
         * class106:tom 98
         * class107:tom 38
         * class108:tom 51
         */
        conf.set("mapreduce.input.keyvaluelinerecordreader.key.value.separator", ":");
        conf.setBoolean("ds.support.append", true);
        System.setProperty("HADOOP_USER_NAME", "root");
        try {
            FileSystem fileSystem = FileSystem.get(conf);
            Job job = Job.getInstance(conf);
            job.setJarByClass(Main.class);
            // 指定输入格式
            /**
             * -rw-r--r--   2 root supergroup        111 2020-06-14 20:27 /avg/1.txt
             * -rw-r--r--   2 root supergroup        111 2020-06-14 20:27 /avg/2.txt
             * -rw-r--r--   2 root supergroup        111 2020-06-14 20:27 /avg/3.txt
             * -rw-r--r--   2 root supergroup        111 2020-06-14 20:27 /avg/4.txt
             * -rw-r--r--   2 root supergroup        111 2020-06-14 20:28 /avg/5.txt
             * -rw-r--r--   2 root supergroup        175 2020-06-14 20:34 /avg/avg.txt
             */
            job.setInputFormatClass(KeyValueTextInputFormat.class);
            // 输入数据
            Path in = new Path("hdfs://master:9000/avg/");
            // TextInputFormat 默认一个文件一个map task， 浪费资源，一般使用CombineTextInputFormat
            KeyValueTextInputFormat.addInputPath(job, in);
            // 执行输出格式
            job.setOutputFormatClass(TextOutputFormat.class);
            // 输出路径
            // 一个文件生成一个map task线程
            // 文件超过128M， 启动一个map task

            Path out = new Path("hdfs://master:9000/tmp/avg");
            if(fileSystem.exists(out)) {
                // 如果输出路径存在，就需要删除
                fileSystem.delete(out, true);
            }
            TextOutputFormat.setOutputPath(job, out);
            job.setMapperClass(AvgMap.class);
            job.setReducerClass(AvgRed.class);
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(DoubleWritable.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(DoubleWritable.class);
            job.waitForCompletion(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}