package com.example.springbootdemo.common.mapreduce.publicFriend;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class Red_1 extends Reducer<Text, Text, Text, Text> {

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        String f = null;
        List<String> list = null;
        Iterator<Text> iterator = values.iterator();
        while(iterator.hasNext()) {
            Text text = iterator.next();
            String ss = text.toString();
            list.add(ss);
        }
        context.write(new Text(key), new Text(list.toString()));
    }
}
