package com.season.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class TestController {
    @Value("${testconfigenv}")
    private String testconfigenv;

    @RequestMapping("/from")
    public String from(){
        return this.testconfigenv;
    }
}
