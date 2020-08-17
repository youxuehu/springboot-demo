package com.example.springbootdemo.common.spark.wordcount;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;

public class WordCountJava {

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setMaster("local").setAppName("WordCountJava");
        JavaSparkContext sc = new JavaSparkContext(conf);
        countJava8(sc);
    }

    public static void countJava8(JavaSparkContext sc) {
        sc.textFile("hdfs://localhost:9000/test.txt")
                .flatMap(s -> Arrays.asList(s.split(" ")).iterator())
                .mapToPair(s -> new Tuple2<>(s, 1))
                .reduceByKey((x, y) -> x + y)
                .saveAsTextFile("hdfs://localhost:9000/Spark/20200817/test");
    }
}
