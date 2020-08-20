package com.example.springbootdemo.common.mapreduce.mywordcount;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.log4j.BasicConfigurator;
public class TestWordCountMain {
    public static void main(String[] args) {
        try {
            if (args.length != 2) {
                System.out.println("please input path inpath and outpath");
                System.exit(-1);
            }
            Configuration conf = new Configuration();
            conf.set("fs.defaultFS", "hdfs://localhost:9000");
            conf.setBoolean("dfs.support.append", true);
            System.setProperty("HADOOP_USER_NAME", "youxuehu");
            BasicConfigurator.configure();
            FileSystem fs = FileSystem.get(conf);
            Job job = Job.getInstance(conf);
            job.setJarByClass(TestWordCountMain.class);
            job.setInputFormatClass(TextInputFormat.class);
            Path srcPath = new Path("hdfs://localhost:9000/The_Man_of_Property.txt");
            TextInputFormat.addInputPath(job, srcPath);
            job.setOutputFormatClass(TextOutputFormat.class);
            Path resultPath = new Path("hdfs://localhost:9000/tmp/testwordcount");
            boolean b = fs.exists(resultPath);
            if (b) {
                fs.delete(resultPath, true);
            }
            TextOutputFormat.setOutputPath(job, resultPath);
            job.setMapperClass(MyWordCountMapper.class);
            job.setReducerClass(MyWordCountReducer.class);
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(IntWritable.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(IntWritable.class);
            job.waitForCompletion(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
