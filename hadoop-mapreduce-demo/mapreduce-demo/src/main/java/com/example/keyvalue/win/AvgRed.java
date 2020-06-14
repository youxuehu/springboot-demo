package com.example.keyvalue.win;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class AvgRed extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {
    @Override
    protected void reduce(Text key, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException {
        Iterator<DoubleWritable> iterator = values.iterator();
        double sum = 0;
        double count = 0;
        while (iterator.hasNext()) {
            DoubleWritable next = iterator.next();
            double score = next.get();
            sum += score;
            count++;
        }
        context.write(new Text(key), new DoubleWritable(sum  /count));
    }
}
