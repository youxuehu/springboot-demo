package com.example.dubbospringbootclient.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.example.PersonService;
import org.example.model.PersonDO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {

    @DubboReference
    private PersonService personService;

    @RequestMapping("queryPerson")
    public PersonDO queryPerson() {
        PersonDO personDO = personService.quertPerson();
        return personDO;
    }
}
