package com.example.springbootdemo.common.storm.example.kafka;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class KafkaMessageSaveBolt extends BaseBasicBolt {
    @Override
    public void execute(Tuple tuple, BasicOutputCollector basicOutputCollector) {
        String word = (String) tuple.getValue(0);
        System.out.println("output:" + word);
        try {
            //输出数据到本地文件
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("kafkastorm.out"));
            dataOutputStream.writeUTF("output:" + word);
            dataOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        basicOutputCollector.emit(new Values("output:" + word));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("message"));
    }
}
