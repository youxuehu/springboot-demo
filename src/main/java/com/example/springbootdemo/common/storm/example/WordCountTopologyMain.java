package com.example.springbootdemo.common.storm.example;
import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import java.util.HashMap;
import java.util.Map;
public class WordCountTopologyMain {
    public static void main(String[] args) throws AlreadyAliveException, InvalidTopologyException {
        // 1、准备一个 TopologyBuilder
        TopologyBuilder topologyBuilder = new TopologyBuilder();
        topologyBuilder.setSpout("mySpout",new MySpout(),1);
        topologyBuilder.setBolt("mybolt1",new MySplitBolt(),10).shuffleGrouping("mySpout");
        topologyBuilder.setBolt("mybolt2",new MyCountBolt(),2).fieldsGrouping("mybolt1",new Fields("word"));

        //2、创建configuration,指定topology 需要的worker的数量
        Config config=new Config();
        config.setNumWorkers(2);

        //3、提交任务，分为本地模式、集群模式
        // StormSubmitter.submitTopologyWithProgressBar("mywordcount",config,topologyBuilder.createTopology());

        LocalCluster localCluster = new LocalCluster();
        localCluster.submitTopology("mywordcount",config,topologyBuilder.createTopology());
    }
    static class MyCountBolt extends BaseRichBolt {
        OutputCollector collector;
        Map<String, Integer> map = new HashMap<String, Integer>();
        public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
            this.collector = outputCollector;
        }
        public void execute(Tuple tuple) {
            String word = tuple.getString(0);
            Integer num = tuple.getInteger(1);
            if (map.containsKey(word)) {
                Integer count = map.get(word);
                map.put(word, count + num);
            }else{
                map.put(word,1);
            }
            System.out.println("count:"+map);
        }
        public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        }
    }
    static class MySpout  extends BaseRichSpout {
        SpoutOutputCollector collector;
        public void open(Map map, TopologyContext context, SpoutOutputCollector collector) {
            this.collector = collector;
        }
        public void nextTuple() {
            // 数据源 ，循环执行
            collector.emit(new Values("Hello world tiger"));
            try {
                // 每5秒处理一次
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public void declareOutputFields(OutputFieldsDeclarer declarer) {
            declarer.declare(new Fields("firstStorm"));
        }
    }
    static class MySplitBolt extends BaseRichBolt {
        OutputCollector outputCollector;
        public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
            this.outputCollector = outputCollector;
        }
        public void execute(Tuple tuple) {
            String line = tuple.getString(0);
            String[] words = line.split(" ");
            for (String word : words) {
                outputCollector.emit(new Values(word,1));
            }
        }
        public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
            outputFieldsDeclarer.declare(new Fields("word","num"));
        }
    }
}


