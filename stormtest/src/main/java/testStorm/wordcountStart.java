package testStorm;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.topology.InputDeclarer;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;

public class wordcountStart {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        TopologyBuilder builder = new TopologyBuilder();

        builder.setSpout("1", new wordcountspout(), 3);
        builder.setBolt("2", new splitSentence(), 5).
                shuffleGrouping("1","spout_stream");
        builder.setBolt("3", new wordcount(), 5).
                fieldsGrouping("2", "split_stream", new Fields("word"));

        //builder.setSpout("1", new wordcountspout(), 3);
        //builder.setBolt("2", new splitSentence(), 5).shuffleGrouping("1", "spout_stream");
        //builder.setBolt("3", new wordcount(), 5).fieldsGrouping("2", "split_stream", new Fields("word"));

        Config conf = new Config();

        conf.setDebug(false);

        if (args[0].equals("local")) {
            LocalCluster localCluster = new LocalCluster();
            localCluster.submitTopology("wordcount-demo-123", conf, builder.createTopology());
        } else {
            try {
                StormSubmitter.submitTopology("wordcount-online", conf, builder.createTopology());
            } catch (AlreadyAliveException e) {
                System.out.println("[AlreadyAliveException] error:" + e);
            } catch (InvalidTopologyException e) {
                System.out.println("[InvalidTopologyException] error:" + e);
            }
        }


//		try {
//			Thread.sleep(10000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		cluster.killTopology("wordcount-demo");
//		cluster.shutdown();
    }

}
