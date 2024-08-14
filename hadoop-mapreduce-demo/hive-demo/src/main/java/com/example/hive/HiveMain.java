package com.example.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * 1：hive 元数据查询服务
 * hive --service metastore &
 * 2：hive server2 服务 通过jdbc操作hive，支持高并发
 * hive --service hiveserver2 &
 * 3：hive web interface 启动服务
 * hive --service hwi &
 * cd hive/hwi/web
 * tar -zcf hive-hwi-1.2.1.tar.gz ./*
 * mv hive-hwi-1.2.1.tar.gz hive-hwi-1.2.1.war
 * hive-hwi-1.2.1.war
 * 增加hwi服务的配置
 * hive-site.xml.bak 添加属性
 *     <property>
 *         <name>hive.querylog.location</name>
 *         <value>/usr/hive/log</value>
 *     </property>
 *     <property>
 *         <name>hive.hwi.listen.host</name>
 *         <value>0.0.0.0</value>
 *     </property>
 *     <property>
 *         <name>hive.hwi.listen.port</name>
 *         <value>9999</value>
 *     </property>
 *     <property>
 *         <name>hive.hwi.war.file</name>
 *         <value>lib/hive-hwi-1.2.1.war</value>
 *     </property>
 * 4： hive shell ,终端操作, 只能起一个，起第二个会阻塞，不支持并发访问
 */
public class HiveMain {
    private static String driverName = "org.apache.hive.jdbc.HiveDriver";

    static Connection con = null;
    static Statement stmt = null;
    static void getConnect() {
        try {
            Class.forName(driverName);
            con = DriverManager.getConnection("jdbc:hive2://master:10000/default", "root", "zhf123..");
            stmt = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createDatabase() {
        try {
            getConnect();
            stmt.execute("create database if not exists hive_2020");
            System.out.println("Database userdb created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != con) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) throws Exception {
        createDatabase();
//        createTable();
//        insertTable();
    }

    private static void insertTable() {
        try {
            getConnect();
            stmt.execute("insert into table student values (30,'杰克')");
            System.out.println("insert table success");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != con) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void createTable() {
        try {
            getConnect();
            stmt.execute(
                    "create table if not exists student(\n" +
                    "    id int,\n" +
                    "    name string\n" +
                    ") row format delimited fields terminated by ',' stored as textfile\n" +
                    "location 'hdfs://master:9000/hive/warehouse/student'");
            System.out.println("hive create table success");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != con) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
