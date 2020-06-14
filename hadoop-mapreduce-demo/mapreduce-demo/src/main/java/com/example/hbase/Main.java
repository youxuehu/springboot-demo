package com.example.hbase;
import java.io.IOException;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper.Context;
public class Main {

    public static void main(String[] args) {

        try {
            Configuration conf = HBaseConfiguration.create();
            conf.set("hbase.zookeeper.quorum", "master:2181");
            Job job = null;

            job = Job.getInstance(conf);


            job.setJarByClass(Main.class);

            Scan scan = new Scan();
            /**
             * 数据输入源是hbase的inputTable表， 执行mapper.class进行map过程，输出的key/value类型是
             * ImmutableBytesWritable和Put类型，
             * 最后一个参数是作业对象。需要指出的是需要声明一个扫描读入对象scan，进行表扫描读取数 据用，
             * 其中scan可以配置参数，这里为了例子简单不再详述。
             */

            TableMapReduceUtil.initTableMapperJob("map", scan, HbaseMap.class, Text.class,IntWritable.class,job);
            /**
             * 数据输出目标是hbase的outputTable表，输出执行的reduce过程是reducer.class类，操作的作业目标是job。
             * 与map比
             * 缺少输出类型的标注，因为他们不是必要的，看过源代码就知道mapreduce的TableRecordWriter中write(key
             * ,value) 方法中，key值是没有用到的。value只能是Put或者Delete两种类型，write方法会自行判断并不用用户指明。
             */
            TableMapReduceUtil.initTableReducerJob("red", HbaseRed.class, job);
            job.waitForCompletion(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
