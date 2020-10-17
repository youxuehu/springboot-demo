package com.example.dubbospringbootserver.service.person.impl;

import org.apache.dubbo.config.annotation.DubboService;
import org.example.PersonService;
import org.example.model.PersonDO;

import java.util.Date;

@DubboService
public class PersonServiceImpl implements PersonService {
    @Override
    public PersonDO quertPerson() {
        PersonDO personDO = new PersonDO();
        personDO.setId(1L);
        personDO.setName("游学虎");
        personDO.setCreateTime(new Date());
        personDO.setDeleted(false);
        return personDO;
    }
}
