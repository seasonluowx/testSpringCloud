package com.season.streamrabbit.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;

//@EnableBinding(value={Sink.class,Source.class, Processor.class})
@EnableBinding(value={Processor.class})
public class SinkReceiver {

    private static Logger logger = LoggerFactory.getLogger(SinkReceiver.class);

/*    @StreamListener(Sink.INPUT)
//    @ServiceActivator(inputChannel = Sink.INPUT)
    public void receive(User payload){
        logger.info("Receiver SA: " +payload);
    }*/

    /*@StreamListener(Processor.OUTPUT)
    @SendTo(Processor.INPUT)
    public User receiveOutput(User payload){
        logger.info("Receiver output: " +payload.toString());
        return payload;
    }*/


    /**
     * spring integration core的注解@ServiceActivator，建议使用StreamListener替代
     * */
//    @ServiceActivator(inputChannel=Processor.OUTPUT)
    public void receiveOutputSA(String payload){
        logger.info("ReceiverSA output: " +payload);
    }
}
