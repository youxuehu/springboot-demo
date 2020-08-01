package com.example.remote;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
public class WordCount {

    public static void main(String[] args) throws Exception {
        ExecutionEnvironment env = ExecutionEnvironment
                .createRemoteEnvironment("leader", 8081,
                        "/Users/youxuehu/IdeaProjects/springboot-demo/flink-demo/target/flink-demo-1.0-SNAPSHOT.jar");
        DataSet<String> data = env.readTextFile("hdfs://leader:9000/test.txt");
        data.filter(new FilterFunction<String>() {
            public boolean filter(String s) throws Exception {
                return false;
            }
        }).writeAsText("hdfs://leader:9000/test/file6");
        data.writeAsText("hdfs://leader:9000/test/file6");
        env.execute();
    }
}