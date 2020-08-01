package com.example.local;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;
public class WordCount {

    public static void main(String[] args) throws Exception {
        String host;
        int port;
        try{
            ParameterTool parameterTool = ParameterTool.fromArgs(args);
            host = parameterTool.get("host");
            port = parameterTool.getInt("port");
        }catch (Exception e){
            System.err.println("没有指定port参数，使用默认值9999");
            host = "leader";
            port = 9999;
        }
        System.out.println(String.format("host=%s,port=%d", host, port));
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> text = env.socketTextStream(host, port, "\n");
        DataStream<WordWithCount> windowCount = text.flatMap(new FlatMapFunction<String, WordWithCount>() {
            public void flatMap(String value, Collector<WordWithCount> out) throws Exception {
                String[] splits = value.split("\\s");
                for (String word:splits) {
                    out.collect(new WordWithCount(word,1L));
                }
            }
        })//打平操作，把每行的单词转为<word,count>类型的数据
                .keyBy("word")//针对相同的word数据进行分组
                .timeWindow(Time.seconds(2),Time.seconds(1))//指定计算数据的窗口大小和滑动窗口大小
                .sum("count");
        //把数据打印到控制台
        System.out.println("####### print ######");
        windowCount.print()
                .setParallelism(1);//使用一个并行度
        windowCount.writeAsText("/flink/test/" + System.currentTimeMillis());
        //注意：因为flink是懒加载的，所以必须调用execute方法，上面的代码才会执行
        env.execute("streaming word count");
    }

    /**
     * 主要为了存储单词以及单词出现的次数
     */
    public static class WordWithCount{
        public String word;
        public long count;
        public WordWithCount(){}
        public WordWithCount(String word, long count) {
            this.word = word;
            this.count = count;
        }

        @Override
        public String toString() {
            return "WordWithCount{" +
                    "word='" + word + '\'' +
                    ", count=" + count +
                    '}';
        }
    }
}