package com.example.springbootdemo.common.storm.example.kafka;

import backtype.storm.spout.Scheme;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class KafkaMessageReceiver implements Scheme {
    @Override
    public List<Object> deserialize(byte[] bytes) {
        // storm 接受 Kafka 的消息
        try {
            String message = new String(bytes, "utf-8");
            return new Values(message);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Fields getOutputFields() {
        return new Fields("message");
    }
}
