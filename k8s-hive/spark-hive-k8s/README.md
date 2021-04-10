# Single-node Hadoop with Hive

The initial sources for Dockerfile and configs are based on https://github.com/prestosql/docker-images/tree/master/prestodev/cdh5.15-hive

## Hadoop/Hive setup
### Create the namespace and deployment:

```
k create ns hadoop
k apply -f cdh.yaml
```

Once the pod is up, check NameNode UI:
```
k -n hadoop get pods
k -n hadoop port-forward hadoop-master-xxxxx-xxxxx 50070
```
Navigate to [http://localhost:50070](http://localhost:50070) to see the UI.

Exec into CDH container:
```
k -n hadoop get pods
k -n hadoop exec -ti hadoop-master-xxxxx-xxxxx bash
```

### Create some dummy data
Create some data in HDFS:
```
export HADOOP_USER_NAME=hdfs
hdfs dfs -mkdir /test
curl  -O https://data.ok.gov/sites/default/files/res_median_household_income_93ry-4338.csv
mv res_median_household_income_93ry-4338.csv demo.csv
hdfs dfs -copyFromLocal demo.csv /test
hdfs dfs -chmod -R 777 /test
hdfs dfs -ls /test
```

Create some tables in Hive
```
beeline

-- connecting as 'hive' user
!connect jdbc:hive2://hadoop-master.hadoop:10000/default hive hive

create table hive(id int, value string);
insert into hive values(1, 'hive');
select * from hive;

-- connecting as 'anonymous' user
!connect jdbc:hive2://hadoop-master.hadoop:10000/default anonymous anonymous

create table anon(id int, value string);
insert into anon values(1, 'anon');
select * from anon;
!quit
```

Verify the data is in HDFS:
```
hdfs dfs -ls -R /user/hive/warehouse
Found 2 items
drwxrwxrwt   - anonymous supergroup          0 2020-06-24 01:20 /user/hive/warehouse/anon
drwxrwxrwt   - hive      supergroup          0 2020-06-24 01:19 /user/hive/warehouse/hive
```

## Using Spark
### Running Spark container and configuring access to Hive
Launch a container with Spark:
```
kubectl run -ti --rm spark --image=mesosphere/spark:spark-2.4.5-hadoop-2.9-k8s --restart=Never bash
```

Create a file `/opt/spark/conf/hive-site.xml` with the following contents:
```
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>
<configuration>
  <property>
    <name>hive.metastore.uris</name>
    <value>thrift://hadoop-master.hadoop.svc.cluster.local:9083</value>
  </property>
</configuration>
```

Run spark-shell:
```
/opt/spark/bin/spark-shell --conf spark.sql.warehouse.dir=hdfs://hadoop-master.hadoop.svc.cluster.local:9000/user/hive/warehouse
```

### Interacting with HDFS/Hive

Checking tables:
```
sc.setLogLevel("INFO")

spark.sharedState.externalCatalog.listTables("default")

spark.table("hive").show
```

Reading data from HDFS:
```
val df = spark.read.csv("hdfs://hadoop-master.hadoop.svc.cluster.local:9000/test/demo.csv")
df.show
```

Save results to Hive and query with Spark SQL:
```
df.write.mode("overwrite").saveAsTable("demo_data")

spark.sql("select * from demo_data").show()
```

Check the data in HDFS (go back to HDFS/Hive container):
```
hdfs dfs -ls -R /user/hive/warehouse
```
Note: the new table files are owned by the user of Spark ('root')
