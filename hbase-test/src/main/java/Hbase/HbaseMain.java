package Hbase;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.protobuf.ServiceException;
import org.apache.hadoop.mapred.JobID;
import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class HbaseMain {

    private static final String tableName= "test";

    private static final AtomicInteger atomicInteger = new AtomicInteger();

    private static final List<String> list = Arrays.asList("jack","tom","hike");

    public static void main(String[] args) {

        HBaseClient hBaseClient = new HBaseClient();
//        boolean success = hBaseClient.createTable("haha", "info");
//        System.out.println(success);


        List<String> tables = hBaseClient.listTables();
        boolean a = hBaseClient.exists(tableName) ? false : hBaseClient.createTable(tableName, "info");
        hBaseClient.put(tableName, atomicInteger.toString(), "name", list.get(new Random().nextInt(3)));
        String describeTable = hBaseClient.describeTable(tableName);
        System.out.println(describeTable);
        System.out.println(tables);

        try {
            hBaseClient.scanTable(tableName, "info", "name");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
