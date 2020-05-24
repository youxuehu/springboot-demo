package com.example.springbootdemo.common.storm.example.hbase;

import com.alibaba.fastjson.JSON;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class KafkaConsoleProducer {
    private static Producer<Integer,String> producer;
    private static final Properties props=new Properties();
    static {
        //定义连接的broker list
        props.put("metadata.broker.list", "master:9092");
        //定义序列化类 Java中对象传输之前要序列化
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        producer = new Producer<Integer, String>(new ProducerConfig(props));
    }
    public static void main(String[] args) {
        //定义topic
        String topic="storm-kafka-hbase-20200524";
        for (int i = 0; i < 50; i++) {
            String gender = "famale";
            if (i % 2 == 0) {
                gender = "male";
            }
            Message message = new Message("jack" + i, i, gender);
            List<KeyedMessage<Integer, String>> datalist = new ArrayList<KeyedMessage<Integer, String>>();
            //构建消息对象
            KeyedMessage<Integer, String> data = new KeyedMessage<Integer, String>(topic, JSON.toJSONString(message));
            datalist.add(data);
            producer.send(datalist);
        }
        producer.close();
    }
}
