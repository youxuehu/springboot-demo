package com.example.springbootdemo.utils.easyexcal;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ExcelUtils {
    /**
     *
     * @param is            输入文件的流对象
     * @param clazz         每行数据转换成的对象类
     * @param sheetNo       第几个sheet
     * @param excelListener excel的监听类
     * @return              是否成功,如果成功可以再监听类里面取数据
     */
    public static Boolean readExcel(InputStream is, Class<? extends BaseRowModel> clazz, int sheetNo, AnalysisEventListener excelListener){
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(is);
            // 解析每行结果在listener中处理
            ExcelReader excelReader = EasyExcelFactory.getReader(bis, excelListener);
            excelReader.read(new Sheet(sheetNo, 1, clazz));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    /**
     *
     * @param os 文件输出流
     * @param clazz Excel实体映射类
     * @param data 导出数据
     * @return
     */
    public static Boolean writeExcel(OutputStream os, Class<? extends BaseRowModel> clazz, List<? extends BaseRowModel> data, int seetNo){
        BufferedOutputStream bos= null;
        try {
            bos = new BufferedOutputStream(os);
            ExcelWriter writer = new ExcelWriter(bos, ExcelTypeEnum.XLSX);
            //写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
            Sheet sheet1 = new Sheet(seetNo, 0,clazz);
            writer.write(data, sheet1);
            writer.finish();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        //readExcelFile();
        writeExcelFile();

    }

    private static void writeExcelFile() {
        //2.写入Excel
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("/Users/youxuehu/Desktop/export.xlsx");
            //FileOutputStream fos, Class clazz, List<? extends BaseRowModel> data
            List<LocalWareDTO> list = new ArrayList<>();
            for (int i = 0; i < 500000; i++){
                LocalWareDTO excelEntity = new LocalWareDTO();
                excelEntity.setArticleNo("qqwqq123");
                excelEntity.setOrderId("12321234223");
                excelEntity.setPickId("234234223");
                excelEntity.setPickWareHouse("1232123424233");
                list.add(excelEntity);
            }
            Boolean flag = ExcelUtils.writeExcel(fos,LocalWareDTO.class,list,1);
            System.out.println("导出是否成功："+flag);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void readExcelFile() {
        //1.读取Excel
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("/Users/youxuehu/Desktop/export.xlsx");
            LocalWareExcelListener<LocalWareDTO> localWareExcelListener = new LocalWareExcelListener<>();
            Boolean flag = ExcelUtils.readExcel(fis, LocalWareDTO.class,1,localWareExcelListener);
            List<LocalWareDTO> datas = localWareExcelListener.getDatas();
            if(flag){
//                log.info("{}", datas);
                log.info("解析数据行数为:{}",datas.size());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}