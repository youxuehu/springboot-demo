package com.example.multi.win;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
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
            // 指定输入格式
            /**
             * -rw-r--r--   2 root supergroup        111 2020-06-14 20:27 /avg/1.txt
             * -rw-r--r--   2 root supergroup        111 2020-06-14 20:27 /avg/2.txt
             * -rw-r--r--   2 root supergroup        111 2020-06-14 20:27 /avg/3.txt
             * -rw-r--r--   2 root supergroup        111 2020-06-14 20:27 /avg/4.txt
             * -rw-r--r--   2 root supergroup        111 2020-06-14 20:28 /avg/5.txt
             * -rw-r--r--   2 root supergroup        175 2020-06-14 20:34 /avg/avg.txt
             */
            job.setInputFormatClass(TextInputFormat.class);
            // 输入数据MultipleInputs ，文件在不同的文件夹下面
            Path in1 = new Path("hdfs://master:9000/avg/");
            Path in2 = new Path("hdfs://master:9000/avg2/");
            MultipleInputs.addInputPath(job, in1, TextInputFormat.class);
            MultipleInputs.addInputPath(job, in2, TextInputFormat.class);

//            MultipleInputs.addInputPath(job, in1, TextInputFormat.class, Map1.class);
//            MultipleInputs.addInputPath(job, in2, TextInputFormat.class, Map2.class);
            // 执行输出格式
            job.setOutputFormatClass(TextOutputFormat.class);
            // 输出路径
            // 一个文件生成一个map task线程
            // 文件超过128M， 启动一个map task

            Path out = new Path("hdfs://master:9000/tmp/multi");
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
