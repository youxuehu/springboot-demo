package org.example.lock;

import org.example.db.dao.bizlock.model.BizLock;
import org.example.db.service.bizlock.BizLockService;
import org.example.lock.mysql.TestMySqlLock;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MySqlLockTest {

    

    @Test
    public void init() {
        BizLock DEFAULTLOCK = new BizLock();
        DEFAULTLOCK.setVersion(0);
        DEFAULTLOCK.setBizId(TestMySqlLock.BizEnum.BIZID.name());
        DEFAULTLOCK.setBizType(TestMySqlLock.BizEnum.BIZTYPE.name());
        DEFAULTLOCK.setLockType(TestMySqlLock.BizEnum.LOCKTYPE.name());
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        BizLockService bizLockService = (BizLockService) applicationContext.getBean("bizLockService");
        bizLockService.delete();
        bizLockService.insertSlector(DEFAULTLOCK);
    }

    @Test
    public void testLock() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        final TestMySqlLock testMySqlLock = applicationContext.getBean(TestMySqlLock.class);

        for (int i = 0; i < 10; i++) {
            new Thread(){
                @Override
                public void run() {
                    testMySqlLock.execute();
                }
            }.start();
        }

    }

    @Test
    public void testLock2() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    }
}
