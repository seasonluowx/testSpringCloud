package com.season.rabbitmq.component;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Sender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(){
        String contenxt = "hello,today is "+new Date();
        System.out.println("sender : "+ contenxt);
        amqpTemplate.convertAndSend("hello",contenxt);
    }
}
