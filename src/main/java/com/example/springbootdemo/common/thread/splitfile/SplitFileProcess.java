package com.example.springbootdemo.common.thread.splitfile;
import java.io.*;

public class SplitFileProcess {
    public static void main(String args[]) {
        try {
            FileReader read = new FileReader("/Users/youxuehu/logs/sofa-runtime/sofa-default.log");
            BufferedReader br = new BufferedReader(read);
            String row;
            int rownum = 1;
            int fileNo = 1;
            FileWriter fw = new FileWriter("/Users/youxuehu/logs/sofa-runtime/"+fileNo +".txt");
            while ((row = br.readLine()) != null) {
                rownum ++;
                fw.append(row + "\r\n");

                if((rownum / 10) > (fileNo - 1)){
                    fw.close();
                    fileNo ++ ;
                    fw = new FileWriter("/Users/youxuehu/logs/sofa-runtime"+fileNo +".txt");
                }
            }
            fw.close();
            System.out.println("rownum="+rownum+";fileNo="+fileNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
