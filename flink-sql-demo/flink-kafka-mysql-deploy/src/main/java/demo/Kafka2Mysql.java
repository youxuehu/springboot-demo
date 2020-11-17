package demo;

import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableEnvironment;

/**
 * 描述：flink消费kafka数据写入mysql
 *
 * 操作步骤：
 *  mvn clean package -DskipTests
 *  cp flink-kafka-mysql-deploy-1.0-SNAPSHOT.jar ~/flinkDeploy
 *  docker-compose up -d
 *  docker exec -it mysql bash
 *      // 初始化mysql数据
 *      create database flinkdb character set utf8;
 *      use flinkdb
 *      create table cdn_log(msg text);
 *  docker exec -it kafka bash
 *      // 初始化kafka数据
 *      kafka-topics.sh --create --topic cdn-log --zookeeper zookeeper:2181 --replication-factor 1 partitions 1
 *      kafka-console-provider.sh --topic cdn-log --broker-list kafka:9092
 *      kafka-console-consumer.sh --topic cdn-log --bootstrap-server kafka:9092 --from-beginning
 *
 *  docker exec -it jobmanager bash
 *      bin/flink run /opt/flinkDeploy/flink-kafka-mysql-deploy-1.0-SNAPSHOT.jar -d
 *
 */
public class Kafka2Mysql {
    public static void main(String[] args) throws Exception {
        // Kafka {"msg": "welcome flink users..."}
        String sourceDDL = "CREATE TABLE kafka_source (\n" +
                " msg STRING\n" +
                ") WITH (\n" +
                " 'connector' = 'kafka-0.11',\n" +
                " 'topic' = 'cdn-log',\n" +
                " 'properties.bootstrap.servers' = 'kafka:9092',\n" +
                " 'format' = 'json',\n" +
                " 'scan.startup.mode' = 'latest-offset'\n" +
                ")";

        // Mysql
        String sinkDDL = "CREATE TABLE mysql_sink (\n" +
                " msg STRING \n" +
                ") WITH (\n" +
                "  'connector' = 'jdbc',\n" +
                "   'url' = 'jdbc:mysql://mysql:3306/flinkdb?characterEncoding=utf-8&useSSL=false',\n" +
                "   'table-name' = 'cdn_log',\n" +
                "   'username' = 'root',\n" +
                "   'password' = '123456',\n" +
                "   'sink.buffer-flush.max-rows' = '1'\n" +
                ")";

        // 创建执行环境
        EnvironmentSettings settings = EnvironmentSettings
                .newInstance()
                .useBlinkPlanner()
                .inStreamingMode()
                .build();
        TableEnvironment tEnv = TableEnvironment.create(settings);

        //注册source和sink
        tEnv.executeSql(sourceDDL);
        tEnv.executeSql(sinkDDL);

        //数据提取
        Table sourceTab = tEnv.from("kafka_source");
        //这里我们暂时先使用 标注了 deprecated 的API, 因为新的异步提交测试有待改进...
        sourceTab.insertInto("mysql_sink");
        //执行作业
        tEnv.execute("Flink Hello World");
    }
}
