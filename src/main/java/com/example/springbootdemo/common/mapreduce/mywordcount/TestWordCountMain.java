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
            Configuration conf = new Configuration();
            conf.set("fs.defaultFS", "hdfs://master7:9000");
            conf.setBoolean("dfs.support.append", true);
            System.setProperty("HADOOP_USER_NAME", "root");
            BasicConfigurator.configure();
            FileSystem fs = FileSystem.get(conf);
            Job job = Job.getInstance(conf);

            job.setJarByClass(TestWordCountMain.class);
            job.setInputFormatClass(TextInputFormat.class);

            Path srcPath1 = new Path("hdfs://master7:9000/The_Man_of_Property.txt");
//			Path srcPath2 = new Path("hdfs://192.168.0.4:9000/dir01/c.txt");
            TextInputFormat.addInputPath(job, srcPath1);

            job.setOutputFormatClass(TextOutputFormat.class);
            Path resultPath = new Path("/result/myfriend1");

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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    }
