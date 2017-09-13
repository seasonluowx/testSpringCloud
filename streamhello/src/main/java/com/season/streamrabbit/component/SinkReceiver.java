package com.season.streamrabbit.component;

import com.season.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.SendTo;

//@EnableBinding(value={Sink.class,Source.class, Processor.class})
@EnableBinding(Sink.class)
public class SinkReceiver {

    private static Logger logger = LoggerFactory.getLogger(SinkReceiver.class);

//    @StreamListener(Sink.INPUT)
    @ServiceActivator(inputChannel = Sink.INPUT)
    public void receive(Object payload){
        logger.info("Receiver SA: " +payload);
    }

    @StreamListener(Processor.OUTPUT)
    @SendTo(Processor.INPUT)
    public String receiveOutput(User payload){
        logger.info("Receiver output: " +payload.toString());
        return payload.toString();
    }

    @ServiceActivator(inputChannel=Processor.OUTPUT)
    public void receiveOutputSA(String payload){
        logger.info("ReceiverSA output: " +payload);
    }
}
