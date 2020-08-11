SPARK_SUBMIT=/Users/youxuehu/SDK/bigdata/spark-2.0.2-bin-hadoop2.6/bin/spark-submit
$SPARK_SUBMIT --class org.apache.spark.examples.SparkPi --master local examples/jars/spark-examples_2.11-2.0.2.jar
$SPARK_SUBMIT --class org.apache.spark.examples.SparkPi --master spark://localhost:7077 examples/jars/spark-examples_2.11-2.0.2.jar
$SPARK_SUBMIT --class org.apache.spark.examples.SparkPi --master yarn-cluster examples/jars/spark-examples_2.11-2.0.2.jar
$SPARK_SUBMIT --class org.apache.spark.examples.SparkPi --master yarn-client examples/jars/spark-examples_2.11-2.0.2.jar