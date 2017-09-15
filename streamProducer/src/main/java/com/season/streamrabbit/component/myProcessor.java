package com.season.streamrabbit.component;

import com.alibaba.fastjson.JSON;
import com.season.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;

import java.text.SimpleDateFormat;
import java.util.Date;

//import org.springframework.integration.core.MessageSource;


@EnableBinding(Processor.class)
public class myProcessor {
    private static Logger logger = LoggerFactory.getLogger(myProcessor.class);

    /*@StreamListener(Processor.INPUT)
    public void receiver(Object object){
        logger.info("Receiver is :"+object.toString());
    }*/

    /*@Bean
    @InboundChannelAdapter(value=Processor.OUTPUT,poller = @Poller(fixedDelay = "2000"))
    public MessageSource<Date> timerMessageSource(){
        return ()->new GenericMessage<>(new Date());
    }*/

    @Bean
    @InboundChannelAdapter(value=Processor.OUTPUT,poller = @Poller(fixedDelay = "2000"))
    public MessageSource<String> UserJSONMessageSource(){
        return ()->new GenericMessage<>(JSON.toJSONString(new User(1L,"hellobaby")));
    }

    @Bean
//    @InboundChannelAdapter(value=Processor.OUTPUT,poller = @Poller(fixedDelay = "2000"))
    public MessageSource<User> UserMessageSource(){
        return ()->new GenericMessage<>(new User(1L,"hellobaby"));
    }

    /**
     * spring integration core的注解Transformer 配合@ServiceActivator使用。对streamListener无效。
     * */
//    @Transformer(inputChannel = Processor.OUTPUT,outputChannel = Processor.OUTPUT)
    public String transform(Date message){
        String s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(message);
        System.out.println(s);
        return s;
    }

}
