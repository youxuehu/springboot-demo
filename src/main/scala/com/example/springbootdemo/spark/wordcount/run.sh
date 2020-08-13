#spark-submit --class com.example.springbootdemo.spark.wordcount.WordCount --master local examples/jars/spark-examples_2.11-2.0.2.jar
#spark-submit --class com.example.springbootdemo.spark.wordcount.WordCount --master spark://localhost:7077 examples/jars/spark-examples_2.11-2.0.2.jar
#spark-submit --class com.example.springbootdemo.spark.wordcount.WordCount --master yarn-client examples/jars/spark-examples_2.11-2.0.2.jar
#spark-submit --class com.example.springbootdemo.spark.wordcount.WordCount --master yarn-cluster examples/jars/spark-examples_2.11-2.0.2.jar
SPARK_SUBMIT=/Users/youxuehu/SDK/bigdata/spark-2.0.2-bin-hadoop2.6/bin/spark-submit
$SPARK_SUBMIT --class com.example.springbootdemo.spark.wordcount.WordCount \
              --master yarn-cluster \
              /Users/youxuehu/IdeaProjects/springboot-demo/target/springboot-demo-0.0.1-SNAPSHOT.jar hdfs://localhost:9000/lineitem.tbl hdfs://localhost:9000/tmp/WordCount
