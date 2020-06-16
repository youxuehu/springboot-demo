package com.example.hbase;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
/**
 * reducer继承的是TableReducer类。 后边指定三个泛型参数，前两个必须对应map过程的输出key/value类型， 第三个必须是
 * put或者delete。write的时候可以把key写null，它是不必要的。 这样reducer输出的数据会自动插入outputTable指定的 表内。
 * @author Administrator
 */
import java.io.IOException;
import java.util.Iterator;

public class HbaseRed  extends TableReducer<Text, IntWritable, NullWritable> {
    public void reduce(Text key, Iterable<IntWritable> values, Context context)
            throws IOException, InterruptedException {
        System.out.println("in method reduce..."+key.toString());
        Iterator<IntWritable> iterator = values.iterator();
        System.out.println(key.toString());
        while (iterator.hasNext()) {
            int age = iterator.next().get();
            System.out.println(age);
        }
        Put put = new Put("aa".getBytes());
        put.addColumn("info".getBytes(), "age".getBytes(), "12".getBytes());
        context.write(NullWritable.get(), put);
    }
}