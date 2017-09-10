package com.season.service;

import com.season.dto.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/refactor")
public interface helloService {
    @RequestMapping(value="/hello")
    public  String say();

    @RequestMapping(value="/userInfo/{id}")
    public User getUserInfo(@PathVariable("id") Long id);

    @RequestMapping(value="/userInfo")
    public List<User> getUserInfoAll(@RequestParam("ids")List<Long> ids);
}
