package org.example.beans.factory;

import org.springframework.beans.factory.FactoryBean;

public class MyFactoryBean implements FactoryBean<MyClass> {

    @Override
    public MyClass getObject() throws Exception {
        MyClass myClass = new MyClass();
        myClass.setMessage("<<<<<<<<<<<<<< 吕水涵的老公公 >>>>>>>>>>>>");
        return myClass;
    }

    @Override
    public Class<?> getObjectType() {
        return MyClass.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
