package org.example.xml;

import org.dom4j.DocumentException;

public class TestXmlBeanAPP {

    public static void main(String[] args)
            throws ClassNotFoundException, InstantiationException,
            DocumentException, IllegalAccessException {

        MyClassPathXmlApplicationContext myClassPathXmlApplicationContext =
                new MyClassPathXmlApplicationContext("config/test-bean.xml");
        TestXmlBean testXmlBean = (TestXmlBean) myClassPathXmlApplicationContext.getBean("testXmlBean");
        testXmlBean.run();
    }
}
