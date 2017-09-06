package com.season.service.controller;

import com.season.service.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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

    @RequestMapping(value="/userInfo/{id}")
    public User getUserInfo(@PathVariable("id") Long id){
        ServiceInstance instance = client.getLocalServiceInstance();
        logger.info("get userInfo ,host:"+instance.getHost()+",service_id:"+instance.getServiceId());
        return new User(id,"游客");
    }

    @RequestMapping(value="/userInfo")
    public List<User> getUserInfoAll(@RequestParam("ids")List<Long> ids){
        ServiceInstance instance = client.getLocalServiceInstance();
        logger.info("get userInfo ,host:"+instance.getHost()+",service_id:"+instance.getServiceId());
        return ids.stream().map(id->new User(id,"游客")).collect(Collectors.toList());
    }
}
