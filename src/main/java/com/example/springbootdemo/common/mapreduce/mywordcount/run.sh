#!/bin/bash
HADOOP_CMD="/Users/youxuehu/SDK/bigdata/hadoop-2.6.5/bin/hadoop"
$HADOOP_CMD jar /Users/youxuehu/IdeaProjects/springboot-demo/target/springboot-demo-0.0.1-SNAPSHOT.jar \
com.example.springbootdemo.common.mapreduce.mywordcount.TestWordCountMain \
hdfs://localhost:9000/The_Man_of_Property.txt hdfs://localhost:9000/tmp/testwordcount