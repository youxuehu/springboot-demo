package testStorm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;

public class wordcount extends BaseRichBolt {
    OutputCollector outputCollector;
    public Map<String , Integer> countMap = new HashMap<String ,Integer>();
    @Override
    public void prepare(Map stormConf, TopologyContext context,
                        OutputCollector collector) {
        // TODO Auto-generated method stub
        outputCollector = collector;
    }

    @Override
    public void execute(Tuple input) {
        // TODO Auto-generated method stub
        String word = input.getString(0);
        System.out.println("word: " + word);
        Integer count = this.countMap.get(word);
        if (null == count)
        {
            count = 0;
        }
        count++;
        this.countMap.put(word, count);

        Iterator<String> iter = this.countMap.keySet().iterator();
        while(iter.hasNext())
        {
            String next = iter.next();
            System.out.println(next + ":" + this.countMap.get(next));
        }

        outputCollector.ack(input);
    }

    @Override
    public void cleanup() {
        // TODO Auto-generated method stub

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        // TODO Auto-generated method stub
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        // TODO Auto-generated method stub
        return null;
    }

}
