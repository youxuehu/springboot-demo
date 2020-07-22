//package com.example.springbootdemo.common.elasticsearch;
//
//import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//
//import java.util.List;
//
///**
// * @Description:定义ItemRepository 接口
// * @Param:
// * 	Item:为实体类
// * 	Long:为Item实体类中主键的数据类型
// * @Author:  xxx
// * @Date: 2018/9/29 0:50
// */
//public interface ItemRepository extends ElasticsearchRepository<Item,Long> {
//    /**
//     *
//     * springdata  自定义方法
//     */
//
//
//    /**
//     * @Description:根据价格区间查询
//     * @Param price1
//     * @Param price2
//     * @Author: https://blog.csdn.net/chen_2890
//     */
//    List<Item> findByPriceBetween(double price1, double price2);
//}
//
