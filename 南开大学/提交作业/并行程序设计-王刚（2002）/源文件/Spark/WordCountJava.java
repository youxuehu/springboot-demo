package com.example.springbootdemo.common.spark.wordcount;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;
import java.util.Arrays;

/**
 * @author youxuehu
 */
public class WordCountJava {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("please input hdfs file path!");
            System.exit(-1);
        }
        SparkConf conf = new SparkConf().setAppName("WordCountJava");
        JavaSparkContext sc = new JavaSparkContext(conf);
        computeWordCount(sc, args);
    }
    public static void computeWordCount(JavaSparkContext sc, String[] args) {
        sc.textFile(args[0])
                .flatMap(s -> Arrays.asList(s.split(" ")).iterator())
                .mapToPair(s -> new Tuple2<>(s, 1))
                .reduceByKey((x, y) -> x + y)
                .saveAsTextFile(args[1]);
    }
}
