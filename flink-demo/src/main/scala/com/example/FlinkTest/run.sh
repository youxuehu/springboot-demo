#!/bin/bash
FLINK_CMD=/Users/youxuehu/SDK/bigdata/flink-1.7.2/bin/flink
$FLINK_CMD run -c com.example.FlinkTest.WordCount \
          /Users/youxuehu/IdeaProjects/springboot-demo/flink-demo/target/flink-demo-1.0-SNAPSHOT.jar \
          --input hdfs://localhost:9000/The_Man_of_Property.txt \
          --output hdfs://localhost:9000/output/