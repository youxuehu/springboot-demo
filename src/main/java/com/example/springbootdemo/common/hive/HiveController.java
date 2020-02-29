package com.example.springbootdemo.common.hive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


//@RestController
//@RequestMapping("/hive2")
public class HiveController {

//    @Autowired
//    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/list")
    public List<Map<String, Object>> list() {
        String sql = "select * from test001";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }
    @RequestMapping("/add/{id}/{name}")
    public void add(@PathVariable Integer id, @PathVariable String name){
        String sql = "insert into test001 values(?, ?)";
        jdbcTemplate.update(sql, id, name);
    }
    @RequestMapping("/update/{id}/{name}")
    public void update(@PathVariable Integer id, @PathVariable String name){
        String sql = "update test001 set name = ? where id = ?";
        jdbcTemplate.update(sql, name, id);
    }

    @RequestMapping("/delete/{id}")
    public void delete(@PathVariable Integer id){
        String sql = "delete from test001 where id = ?";
        jdbcTemplate.update(sql, id);
    }

}

