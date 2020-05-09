package com.example.springbootdemo.common.db.helper;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class DBContextHolder {

    private static final ThreadLocal<DBTypeEnum> CONTEXT_HOLDER = new ThreadLocal<>();

    private static final AtomicInteger COUNTER = new AtomicInteger(0);

    public static void set(DBTypeEnum dbTypeEnum) {
        CONTEXT_HOLDER.set(dbTypeEnum);
    }

    public static DBTypeEnum get() {
        return CONTEXT_HOLDER.get();
    }

    public static void remove() {
        CONTEXT_HOLDER.remove();
    }

    public static void setMaster7() {
        set(DBTypeEnum.MASTER7);
        log.info("切换到{}", DBTypeEnum.MASTER7);
    }

    public static void setSLAVE() {
        int index = COUNTER.getAndIncrement() % (DBTypeEnum.values().length - 1);
        if (COUNTER.get() > 9999) {
            COUNTER.set(0);
        }
        set(DBTypeEnum.values()[index + 1]);
        log.info("切换到{}", DBTypeEnum.values()[index + 1]);
    }
}
