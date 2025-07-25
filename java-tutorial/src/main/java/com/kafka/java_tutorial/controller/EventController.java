package com.kafka.java_tutorial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.java_tutorial.config.KafkaProducerConfig;
import com.kafka.java_tutorial.dto.publishDTO;
import com.kafka.java_tutorial.services.KafkaMessagePublisher;



@RestController
@RequestMapping("/producer-app")
public class EventController {
    
    @Autowired
    private KafkaMessagePublisher kafkaMessagePublisher;

    @Autowired
    private KafkaProducerConfig kafkaProducerConfig;

    @PostMapping("/publish")
    public ResponseEntity<?> publishMessage(@RequestBody publishDTO message) {
        try {
            kafkaProducerConfig.createTopic(message.getTopicName());
            kafkaMessagePublisher.sendMessageToTopic(message);
            return ResponseEntity.ok("Message Published Successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
    
}
