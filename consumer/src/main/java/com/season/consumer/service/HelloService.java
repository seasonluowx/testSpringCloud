package com.season.consumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFallback")
    public String helloService(){
        /*String body = restTemplate.getForEntity("http://service1/hello",String.class).getBody();
        return body;*/
        String body =  restTemplate.getForEntity("http://service1/hello",String.class).getBody();
        return body;
    }

    public String helloFallback(){
        return "error2";
    }
}
