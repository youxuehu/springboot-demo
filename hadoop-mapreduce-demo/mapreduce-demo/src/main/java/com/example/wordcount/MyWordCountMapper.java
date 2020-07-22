package com.example.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * @author youxuehu
 */
public class MyWordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    static PrintWriter out = null;
    static int counter = 0;

    static {
        try {
            out = new PrintWriter("/Users/youxuehu/IdeaProjects/springboot-demo/hadoop-mapreduce-demo/mapreduce-demo/src/main/java/com/example/wordcount/out.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        System.out.println("##【map】######## 【key】 : " + key + "     #########【value】: " + value);
        out.println(counter);
        out.flush();
        /**
         * map 端 key 默认是字节偏移量
         */
        counter++;
        Thread.sleep(500);
        String line = value.toString();
        String[] split = line.split(" ");
        for (String item : split) {
            context.write(new Text(item), new IntWritable(1));
        }
    }
}
