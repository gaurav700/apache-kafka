package com.kafka.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.producer.dto.Customer;
import com.kafka.producer.services.KafkaMessagePublisher;

@RestController
@RequestMapping("/producer")
public class EventController {

    @Autowired
    private KafkaMessagePublisher publisher;

    @PostMapping("/publish1")
    public ResponseEntity<String> publishMessage1(@RequestBody String message) {
        for (int i = 0; i < 100; i++) {
            publisher.sendMessageToTopic(message);
        }
        return ResponseEntity.ok("Published successfully");
    }

    @PostMapping("/publish2")
    public ResponseEntity<String> publishMessage2(@RequestBody Customer dto) {
        publisher.sendCustomerMessageToTopic(dto);
        return ResponseEntity.ok("Customer details published");
    }
}
