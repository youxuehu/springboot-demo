package com.example.springbootdemo.utils.easyexcal;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Data
@ToString
@Slf4j
public class TianMaWarehouseOrderInfo extends BaseRowModel {
    @ExcelProperty(value = "订单ID",index = 0)
    private String orderId;
    @ExcelProperty(value = "配货ID",index = 1)
    private String pickId;
    @ExcelProperty(value = "配货仓",index = 2)
    private String pickWareHouse;
    @ExcelProperty(value = "货号",index = 3)
    private String articleNo;
}