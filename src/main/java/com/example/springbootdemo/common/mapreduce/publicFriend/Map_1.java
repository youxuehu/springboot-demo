package com.example.springbootdemo.common.mapreduce.publicFriend;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


public class Map_1 extends Mapper<Text, Text, Text, Text> {

    @Override
    protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] values = line.trim().split(",");
        for (String v : values){
            context.write(new Text(v), new Text(key));
        }
    }
}
