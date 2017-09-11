package com.season.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class TestController {

    @Value("${from}")
    private String from;

    @Autowired
    private Environment environment;


    @RequestMapping("/from")
    public String from() {
        System.out.println(this.from);
        String s =  environment.getProperty("from");
        return s;
    }
}
