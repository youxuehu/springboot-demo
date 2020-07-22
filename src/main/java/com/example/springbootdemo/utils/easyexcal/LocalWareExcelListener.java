package com.example.springbootdemo.utils.easyexcal;

import lombok.extern.java.Log;

@Log
public class LocalWareExcelListener<T> extends BaseExcelListener<T> {
    @Override
    boolean addListBefore(T object) {
        //log.info("添加数据之前的操作~如果想过滤某条数据，在满足某个条件下，返回false");
        return true;
    }

    @Override
    void doListAfter(T object) {
        //log.info("添加数据之后的操作~");
    }
}