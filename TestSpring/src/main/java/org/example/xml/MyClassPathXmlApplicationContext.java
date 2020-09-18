package org.example.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyClassPathXmlApplicationContext implements MyBeanFactory {
    Map<String, Object> instances = new HashMap<>();
    public MyClassPathXmlApplicationContext(String filePath) throws DocumentException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(MyClassPathXmlApplicationContext.class.getClassLoader().getResourceAsStream(filePath));
        Element root = document.getRootElement();
        List<Element> elements = root.elements();
        for (Element element : elements) {
            instances.put(element.attributeValue("id"), Class.forName(element.attributeValue("class")).newInstance());
        }
    }

    @Override
    public Object getBean(String id) {
        return instances.get(id);
    }
}
