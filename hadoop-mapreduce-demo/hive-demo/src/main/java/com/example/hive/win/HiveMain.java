package com.example.hive.win;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
            boolean execute = stmt.execute("create database if not exists db_hive");
            System.out.println("execute: " + execute);
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
//        createDatabase();
//        createTable();
        insertTable();
    }

    private static void insertTable() {
        try {
            getConnect();
            boolean execute = stmt.execute("insert into table student values (30,'杰克')");
            System.out.println("insert: " + execute);
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
            boolean execute = stmt.execute("create table if not exists student(\n" +
                    "    id int,\n" +
                    "    name string\n" +
                    ") row format delimited fields terminated by ',' stored as textfile\n" +
                    "location 'hdfs://master:9000/hive/warehouse/student'");
            System.out.println("execute: " + execute);
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
