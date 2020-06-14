package com.example.avg.win;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 计算班级平均分
 * class101:zhangsan 94
 * class101:lisi 77
 * class102:zhaoliu 88
 * class102:wanger 98
 * class103:jack 100
 * class103:tom 58
 */
public class AvgMap extends Mapper<LongWritable, Text, Text, DoubleWritable> {
    @Override
    protected void map(LongWritable key, Text stuInfo, Context context) throws IOException, InterruptedException {
        String[] split = stuInfo.toString().split(":");
        String className = split[0];
        String score = split[1].split(" ")[1];
        context.write(new Text(className), new DoubleWritable(Double.parseDouble(score)));
    }
}
