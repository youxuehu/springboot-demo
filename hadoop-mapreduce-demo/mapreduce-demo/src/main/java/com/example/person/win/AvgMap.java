package com.example.person.win;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * person.txt
 * zhangsan        91100.0
 * zhangsan2       28100.0
 * zhangsan3       10100.0
 * zhangsan4       29100.0
 * zhangsan5       18100.0
 * zhangsan6       19100.0
 */
public class AvgMap extends Mapper<LongWritable, Text, Text, DoubleWritable> {
    @Override
    protected void map(LongWritable key, Text stuInfo, Context context) throws IOException, InterruptedException {
        String[] split = stuInfo.toString().split("-");
        String salary = split[3];
        context.write(new Text("平均薪资"), new DoubleWritable(Double.parseDouble(salary)));
    }
}
