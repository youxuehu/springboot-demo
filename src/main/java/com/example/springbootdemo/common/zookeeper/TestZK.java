package com.example.springbootdemo.common.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

public class TestZK implements Watcher {
    private ZooKeeper zk = null;
    boolean bk = true;

    public void createConnect(String connectStr, int timeout) {
        try {
            zk = new ZooKeeper(connectStr, timeout, this::process);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnect() {
        try {
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void createPath(String path, String data) {
        try {
            zk.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteNode(String path) {
        try {
            zk.delete(path, -1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    public void writeNode(String path, String data) {
        try {
            zk.setData(path, data.getBytes(), -1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    public String readNode(String path) {
        String rest = null;
        try {
            rest = new String(zk.getData(path, true, null));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
        return rest;
    }

    public String existsNode(String path) {
        String rest = null;
        try {
            Stat exists = zk.exists(path, this);
            return exists.toString();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
        return rest;
    }
    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~zookeeper watch is "+watchedEvent +"~~~~~~~~~~~~~~~~~~~~~~~");
        //this.bk=false;
    }

    public static void main(String[] args) {
        TestZK zk = new TestZK();
        zk.createConnect("master:2181", 5000);

        zk.createPath("/data", "1111");
        zk.readNode("/data");
        while (zk.bk) {
            System.out.print(".");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        zk.closeConnect();
    }


}
