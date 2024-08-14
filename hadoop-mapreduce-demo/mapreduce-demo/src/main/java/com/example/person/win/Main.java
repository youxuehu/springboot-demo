package com.example.person.win;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

/**
 * 计算平均薪资
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
            job.setInputFormatClass(TextInputFormat.class);
            Path in = new Path("hdfs://master:9000/person.txt");
            TextInputFormat.addInputPath(job, in);
            job.setOutputFormatClass(TextOutputFormat.class);
            Path out = new Path("hdfs://master:9000/tmp/person");
            if(fileSystem.exists(out)) {
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
