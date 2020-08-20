spark_cmd="/Users/youxuehu/SDK/bigdata/spark-2.0.2-bin-hadoop2.6/bin/spark-submit"
hadoop_cmd="/Users/youxuehu/SDK/bigdata/hadoop-2.6.5/bin/hadoop"
input_path="hdfs://localhost:9000/test.txt"
output_path="hdfs://localhost:9000/Spark/Java/WordCount"
$hadoop_cmd fs -test -e $output_path
if [ $? -eq 0 ]; then
    $hadoop_cmd fs -rmr -skipTrash $output_path
fi
$spark_cmd --class com.example.springbootdemo.common.spark.wordcount.WordCountJava \
              --master yarn-cluster \
              /Users/youxuehu/IdeaProjects/springboot-demo/target/springboot-demo-0.0.1-SNAPSHOT.jar \
              $input_path $output_path