#!/bin/bash
HADOOP_CMD="/Users/youxuehu/SDK/bigdata/hadoop-2.6.5/bin/hadoop"
FLINK_CMD=/Users/youxuehu/SDK/bigdata/flink-1.7.2/bin/flink
INPUT_PATH="hdfs://localhost:9000/The_Man_of_Property.txt"
OUTPUT_PATH="hdfs://localhost:9000/output/wordcount/result.txt"
JAR_PATH="/Users/youxuehu/IdeaProjects/springboot-demo/flink-demo/target/flink-demo-1.0-SNAPSHOT.jar"
$HADOOP_CMD fs -test -e $OUTPUT_PATH
if [ $? -eq 0 ] ; then
  $HADOOP_CMD fs -rmr -skipTrash $OUTPUT_PATH
fi
$FLINK_CMD run -c com.example.FlinkTest.WordCount \
          $JAR_PATH \
          --input $INPUT_PATH \
          --output $OUTPUT_PATH