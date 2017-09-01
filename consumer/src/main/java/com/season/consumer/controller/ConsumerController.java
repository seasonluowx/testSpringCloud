package com.season.consumer.controller;

import com.season.consumer.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {
    @Autowired
    HelloService helloService;
    /*@Autowired
    RestTemplate restTemplate;*/

    @RequestMapping(value="/helloConsumer")
    public String helloConsumer(){
        return helloService.helloService();
        /*String body =  restTemplate.getForEntity("http://service1/hello",String.class).getBody();
        return body;*/
    }

}
