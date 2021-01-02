package com.example.springbootdemo.init;

import com.example.springbootdemo.bloomFilter.BloomFilter;
import com.example.springbootdemo.bloomFilter.BloomFilterCache;
import com.example.springbootdemo.common.db.dao.admin.model.Admin;
import com.example.springbootdemo.common.db.service.AdminService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloomFilterTask implements InitializingBean {
    @Autowired
    AdminService adminService;
    @Override
    public void afterPropertiesSet() throws Exception {
        List<Admin> admins = adminService.queryAdmins();
        BloomFilter bloomFilter = new BloomFilter();
        admins.forEach(admin -> {
            bloomFilter.add(admin.getId().toString());
        });
        BloomFilterCache.bloomFilter = bloomFilter;
    }
}
