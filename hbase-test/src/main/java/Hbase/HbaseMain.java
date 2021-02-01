package Hbase;

import java.util.List;

public class HbaseMain {

    public static void main(String[] args) {

        HBaseClient hBaseClient = new HBaseClient();
//        boolean success = hBaseClient.createTable("haha", "info");
//        System.out.println(success);


        List<String> tables = hBaseClient.listTables();
        System.out.println(tables);
    }
}
