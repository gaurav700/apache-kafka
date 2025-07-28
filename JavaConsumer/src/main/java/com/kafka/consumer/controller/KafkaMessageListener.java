package com.kafka.consumer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageListener {
    Logger log = LoggerFactory.getLogger(KafkaMessageListener.class);
    
    @KafkaListener(topics = "test-topic", groupId = "test-topic-1")
    public void consume1(String message){
        log.info("Consumer1 consume the message { " + message + " }" );
    }

    @KafkaListener(topics = "test-topic", groupId = "test-topic-11")
    public void consume2(String message){
        log.info("Consumer2 consume the message { " + message + " }" );
    }


    @KafkaListener(topics = "test-topic", groupId = "test-topic-1")
    public void consume3(String message){
        log.info("Consumer3 consume the message { " + message + " }" );
    }


}
