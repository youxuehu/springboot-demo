package org.example.lock;

import org.example.db.dao.bizlock.model.BizLock;
import org.example.db.service.bizlock.BizLockService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DistributeLockTest {

    

    @Test
    public void init() {
        BizLock DEFAULTLOCK = new BizLock();
        DEFAULTLOCK.setVersion(0);
        DEFAULTLOCK.setBizId(TestLock.BizEnum.BIZID.name());
        DEFAULTLOCK.setBizType(TestLock.BizEnum.BIZTYPE.name());
        DEFAULTLOCK.setLockType(TestLock.BizEnum.LOCKTYPE.name());
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        BizLockService bizLockService = (BizLockService) applicationContext.getBean("bizLockService");
        bizLockService.delete();
        bizLockService.insertSlector(DEFAULTLOCK);
    }

    @Test
    public void testLock() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        final TestLock testLock = applicationContext.getBean(TestLock.class);

        for (int i = 0; i < 10; i++) {
            new Thread(){
                @Override
                public void run() {
                    testLock.execute();
                }
            }.start();
        }

    }

    @Test
    public void testLock2() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    }
}
