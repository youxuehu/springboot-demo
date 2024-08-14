package com.example.combiner.win;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

/**
 * 计算每个班级的平均值
 */
public class Main {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.addResource("core-site.xml.bak");
        conf.addResource("hdfs-site.xml.bak");
        conf.addResource("yarn-site.xml");
        conf.addResource("mapred-site.xml");
        conf.set("fs.defaultFS", "hdfs://master:9000");
        conf.setBoolean("ds.support.append", true);
        System.setProperty("HADOOP_USER_NAME", "root");
        try {
            FileSystem fileSystem = FileSystem.get(conf);
            Job job = Job.getInstance(conf);
            job.setJarByClass(Main.class);
            // map split size 计算格式
            // 一个文件一个创建一个Map task线程
            // 当文件大小超过128M, 创建一个Map task线程
            // 当一个目录下面有多个小文件需要计算时，默认多少个文件启动多少个map task线程
            //
            // 指定输入格式为CombineTextInputFormat ,合并成一个map task,
            // 可以提高效率， 提高资源利用率
            job.setInputFormatClass(CombineTextInputFormat.class);
            // 输入数据
            /**
             * -rw-r--r--   2 root supergroup        111 2020-06-14 20:27 /avg/1.txt
             * -rw-r--r--   2 root supergroup        111 2020-06-14 20:27 /avg/2.txt
             * -rw-r--r--   2 root supergroup        111 2020-06-14 20:27 /avg/3.txt
             * -rw-r--r--   2 root supergroup        111 2020-06-14 20:27 /avg/4.txt
             * -rw-r--r--   2 root supergroup        111 2020-06-14 20:28 /avg/5.txt
             * -rw-r--r--   2 root supergroup        175 2020-06-14 20:34 /avg/avg.txt
             */
            Path in = new Path("hdfs://master:9000/avg/");
            // 使用CombineTextInputFormat ，可以将6个文件，起1个map task
            CombineTextInputFormat.addInputPath(job, in);
            // 执行输出格式
            job.setOutputFormatClass(TextOutputFormat.class);
            // 输出路径
            Path out = new Path("hdfs://master:9000/result/avg");
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
