//package com.example.springbootdemo.common.spark.sparksql;
//
//import org.apache.spark.SparkConf;
//import org.apache.spark.api.java.JavaSparkContext;
//import org.apache.spark.sql.hive.HiveContext;
//
//public class SparkHiveJava {
//    public static void main(String[] args) {
//        SparkConf conf = new SparkConf().setAppName(SparkHiveJava.class.getSimpleName());
//        JavaSparkContext sc = new JavaSparkContext(conf);
//        HiveContext hiveContext = new HiveContext(sc);
//        //查询hvie 中的数据
//        DataFrame df = hiveContext.table("word");
//        df.show();
//        //向数据库中写数据
//
//        /**
//         * 向hive中写数据
//         * teacher_info
//         *     name,height
//         * teacher_basic
//         *     name,age,married,children
//         *  这是两张有关联的表，关联字段是name，
//         *  需求：
//         *    teacher_info和teacher_basic做join操作，求出每个teacher的name，age，height，married，children
//         *    要求height>180
//         */
//        //在hive中创建相应的表
//        hiveContext.sql("DROP TABLE IF EXISTS teacher_basic");
//        hiveContext.sql("CREATE TABLE teacher_basic(" +
//                "name string, " +
//                "age int, " +
//                "married boolean, " +
//                "children int) " +
//                "row format delimited " +
//                "fields terminated by ','");
//        //向teacher_basic表中加载数据
//        hiveContext.sql("LOAD DATA LOCAL INPATH '/opt/data/spark/teacher_basic.txt' INTO TABLE teacher_basic");
//        //创建第二张表
//        hiveContext.sql("DROP TABLE IF EXISTS teacher_info");
//        hiveContext.sql("CREATE TABLE teacher_info(name string, height int) row format delimited fields terminated by ','");
//        hiveContext.sql("LOAD DATA LOCAL INPATH '/opt/data/spark/teacher_info.txt' INTO TABLE teacher_info");
//        //执行多表关联
//        DataFrame joinDF = hiveContext.sql("select b.name, b.age, b.married, b.children, i.height from teacher_basic b left join teacher_info i on b.name = i.name where i.height > 180");
//        hiveContext.sql("DROP TABLE IF EXISTS teacher");
//        joinDF.show();
//        joinDF.write().saveAsTable("teacher");
//        sc.stop();
//    }
