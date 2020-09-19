#!/bin/bash
HADOOP_CMD="/Users/youxuehu/SDK/bigdata/hadoop-2.6.5/bin/hadoop"
FLINK_CMD="/Users/youxuehu/SDK/bigdata/flink-1.7.2/bin/flink"
#INPUT_PATH="hdfs://localhost:9000/The_Man_of_Property.txt"
INPUT_PATH="hdfs://localhost:9000/output2/part-00000"
OUTPUT_PATH="hdfs://localhost:9000/Flink/Java/testwordcount"
JAR_PATH="/Users/youxuehu/IdeaProjects/springboot-demo/target/springboot-demo-0.0.1-SNAPSHOT.jar"
$HADOOP_CMD fs -test -e $OUTPUT_PATH
if [ $? -eq 0 ] ; then
  $HADOOP_CMD fs -rmr -skipTrash $OUTPUT_PATH
fi
$FLINK_CMD run -m yarn-cluster -c com.example.springbootdemo.common.flink.JavaWordCount \
$JAR_PATH --input $INPUT_PATH --output $OUTPUT_PATH