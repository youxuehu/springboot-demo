package com.example.springbootdemo.common.db.helper;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.annotation.Nullable;

public class RoutingDataSource extends AbstractRoutingDataSource {
    @Override
    @Nullable
    protected Object determineCurrentLookupKey() {
        return DBContextHolder.get();
    }
}
