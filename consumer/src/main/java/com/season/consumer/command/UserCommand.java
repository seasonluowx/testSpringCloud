package com.season.consumer.command;

import com.netflix.hystrix.HystrixCommand;
import com.season.consumer.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class UserCommand extends HystrixCommand<User> {
    @Autowired
    RestTemplate restTemplate;
    private Long id;

    protected UserCommand(Setter setter,RestTemplate restTemplate,Long id) {
        super(setter);
        this.restTemplate = restTemplate;
        this.id = id;
    }

    @Override
    protected User run() throws Exception {
        return restTemplate.getForObject("http://service1/userInfo/{1}",User.class, id);
    }
}
