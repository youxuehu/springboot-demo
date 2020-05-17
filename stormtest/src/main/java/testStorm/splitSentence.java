package testStorm;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class splitSentence extends  BaseRichBolt {

    OutputCollector outputCollector;
    @Override
    public void prepare(Map stormConf, TopologyContext context,
                        OutputCollector collector) {
        // TODO Auto-generated method stub
        outputCollector = collector;
    }

    @Override
    public void execute(Tuple input) {
        // TODO Auto-generated method stub
        String sentence = input.getString(0);
        System.out.println("sentence: " + sentence);

//        if ("how do you do".equals(sentence)) {
//            outputCollector.fail(input);
//        } else {
         for (String word : sentence.split(" ")) {
             outputCollector.emit("split_stream", new Values(word));
                //_collector.emit(input, new Values(word));
             outputCollector.ack(input);
        }
    }

    @Override
    public void cleanup() {
        // TODO Auto-generated method stub

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        // TODO Auto-generated method stub
        //declarer.declare(new Fields("word"));
        declarer.declareStream("split_stream", new Fields("word"));

    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        // TODO Auto-generated method stub
        return null;
    }

}
