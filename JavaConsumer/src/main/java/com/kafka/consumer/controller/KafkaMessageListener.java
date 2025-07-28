package com.kafka.consumer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

public class KafkaMessageListener {
    Logger log = LoggerFactory.getLogger(KafkaMessageListener.class);
    
    @KafkaListener(topics = "test-topic", groupId = "tt-group-1")
    public void consume(String message){
        log.info("Consumer consume the message {}"+ message);
    }
}
