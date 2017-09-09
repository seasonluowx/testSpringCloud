package com.season.consumer.controller;

import com.season.consumer.entity.User;
import com.season.consumer.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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

    @RequestMapping(value="/userinfo/{id}")
    public User Userinfo(@PathVariable("id") Long id){
        /*try {*/
            /*Future<User> future = helloService.userInfoService(id);
            if(future.isDone()){
                return future.get();
            };*/
            return helloService.userInfoServiceSync(id);
        /*} catch (InterruptedException e) {
        try {
            Future future =  helloService.userInfoServiceSync(id);
            return (User) future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/
        }
        return null;
//        return helloService.userInfoServiceSync(id).get();
    }

    @RequestMapping(value="/userinfo")
    public List<User> UserinfoAll(@RequestParam("ids") List<Long> ids){
        return helloService.userInfoServiceSyncAll(ids);
    }
}
