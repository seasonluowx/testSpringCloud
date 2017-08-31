package com.season.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class hello {
    private final Logger logger = Logger.getLogger(String.valueOf(hello.class));

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value="/hello")
    public  String say(){
        ServiceInstance instance = client.getLocalServiceInstance();
        logger.info("hello,host:"+instance.getHost()+",service_id:"+instance.getServiceId());
        return "hello world";
    }
}
