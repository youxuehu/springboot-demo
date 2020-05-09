package com.example.springbootdemo.utils.easyexcal;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseExcelListener<T> extends AnalysisEventListener {
    private List<T> datas = new ArrayList<T>();

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }
    /**
     * 在添加到List集合之前(数据过滤，格式转换等)
     * @param object 一行数据
     */
    abstract boolean addListBefore(T object);

    /**
     * 在添加到List集合之后(添加数据库，缓存等)
     * @param object 一行数据
     */
    abstract void doListAfter(T object);

    /**
     * 执行类
     * @param o 一行数据
     * @param analysisContext ~
     */
    @Override
    public void invoke(Object o, AnalysisContext analysisContext) {
        T t = (T)o;
        boolean flag= addListBefore(t);
        //满足条件的数据， 数据存储到list，供批量处理。
        if(flag){
            datas.add(t);
            doListAfter(t);
        }
    }

    /**
     * 最后。做一些资源销毁
     * @param analysisContext ~
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {}
}