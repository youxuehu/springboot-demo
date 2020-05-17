package com.example.springbootdemo.common.storm.example.kafka;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.spout.SchemeAsMultiScheme;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.utils.Utils;
import storm.kafka.BrokerHosts;
import storm.kafka.KafkaSpout;
import storm.kafka.SpoutConfig;
import storm.kafka.ZkHosts;
import storm.kafka.bolt.KafkaBolt;

import java.util.HashMap;
import java.util.Map;

public class StormKafkaTopologMain {

    public static void main(String[] args) {
        BrokerHosts brokerHosts = new ZkHosts("master:2181");
        SpoutConfig spoutConfig = new SpoutConfig(brokerHosts, "storm-kafka-topic1", "/kafka", "kafkaspout");

        Config conf = new Config();
        Map<String, String> map = new HashMap<String, String>();

        map.put("metadata.broker.list", "master:9092");
        map.put("serializer.class", "kafka.serializer.StringEncoder");
        conf.put("kafka.broker.properties", map);
        conf.put("storm-kafka-topic1", "storm-kafka-topic2");

        spoutConfig.scheme = new SchemeAsMultiScheme(new KafkaMessageReceiver());

        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("spout", new KafkaSpout(spoutConfig));
        builder.setBolt("bolt", new KafkaMessageSaveBolt()).shuffleGrouping("spout");
        builder.setBolt("kafkabolt", new KafkaBolt<String, Integer>()).shuffleGrouping("bolt");

        if(args != null && args.length > 0) {
            //提交到集群运行
            try {
                StormSubmitter.submitTopology(args[0], conf, builder.createTopology());
            } catch (AlreadyAliveException e) {
                e.printStackTrace();
            } catch (InvalidTopologyException e) {
                e.printStackTrace();
            }
        } else {
            //本地模式运行
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("StormKafkaTopologMain", conf, builder.createTopology());
            Utils.sleep(1000000);
            cluster.killTopology("StormKafkaTopologMain");
            cluster.shutdown();
        }
    }
}
