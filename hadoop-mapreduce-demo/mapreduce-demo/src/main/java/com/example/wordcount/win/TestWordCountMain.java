package com.example.wordcount.win;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.log4j.BasicConfigurator;

/**
 * /usr/local/src/hadoop-2.6.5/share/hadoop/*.jar
 *
 * @author youxuehu
 */
public class TestWordCountMain {
    /**
     * winutils.exe 设置环境变量
     * @param args
     */
    public static void main(String[] args) {
        try {
            Configuration conf =new Configuration();
            conf.addResource("core-site.xml");
            conf.addResource("hdfs-site.xml");
            conf.addResource("mapred-site.xml");
            conf.addResource("yarn-site.xml");
            System.setProperty("HADOOP_USER_NAME", "root");
            conf.setBoolean("dfs.support.append", true);
            // 指定mapreduce运行过程中使用的jar包
//            conf.set("mapreduce.job.jar", "wordcount.jar");
            conf.set("fs.defaultFS","hdfs://master:9000");
            FileSystem fs = FileSystem.get(conf);
            Job job = Job.getInstance(conf);
            // 指定mapreduce的入口类
            job.setJarByClass(TestWordCountMain.class);
            job.setInputFormatClass(TextInputFormat.class);
            Path srcPath1 = new Path("hdfs://master:9000/wc.txt");
            TextInputFormat.addInputPath(job, srcPath1);
            job.setOutputFormatClass(TextOutputFormat.class);
            Path resultPath = new Path("/tmp/testwordcount");
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
