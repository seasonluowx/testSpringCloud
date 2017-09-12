package com.season.rabbitmq;

import com.season.rabbitmq.component.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
//@SpringApplicationConfiguration(classes = HelloApplicationtest.class) // 指定我们SpringBoot工程的Application启动类
//@WebAppConfiguration // 由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloApplicationtest {
    @Autowired
    private Sender sender;

    @Test
    public  void hello() {
        sender.send();
    }
}
