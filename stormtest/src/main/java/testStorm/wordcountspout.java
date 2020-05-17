package testStorm;

import java.util.Map;
import java.util.Random;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

public class wordcountspout extends BaseRichSpout implements IRichSpout {
    SpoutOutputCollector outputCollector;
    @Override
    public void open(Map conf, TopologyContext context,
                     SpoutOutputCollector collector) {
        // TODO Auto-generated method stub
        outputCollector = collector;
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub

    }

    @Override
    public void activate() {
        // TODO Auto-generated method stub

    }

    @Override
    public void deactivate() {
        // TODO Auto-generated method stub

    }

    @Override
    public void nextTuple() {
        // TODO Auto-generated method stub
        String [] words = new String[] {"how do you do", "you do what", "do you kown"};
        Random rand = new Random();
        String word = words[rand.nextInt(words.length)];
//		outputCollector.emit(new Values(word));

        //Object msgid = "1";
        Object msgid = rand.hashCode();
        System.out.println("msgid" + msgid.toString());

        outputCollector.emit("spout_stream", new Values(word), msgid);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void ack(Object msgId) {
        System.out.println("Recive ACK!!!, msgid: "+ msgId);
        // TODO Auto-generated method stub

    }

    @Override
    public void fail(Object msgId) {
        // TODO Auto-generated method stub
        System.out.println("Recive Fail!!!, msgid: " + msgId);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        // TODO Auto-generated method stub
        declarer.declareStream("spout_stream", new Fields("sentence"));
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        // TODO Auto-generated method stub
        return null;
    }

}
