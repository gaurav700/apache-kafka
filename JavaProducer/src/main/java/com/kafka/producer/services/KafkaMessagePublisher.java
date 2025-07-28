package com.kafka.producer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.kafka.producer.dto.Customer;

@Service
public class KafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessageToTopic(String message) {
        kafkaTemplate.send("default-topic", message)
            .whenComplete((res, ex) -> {
              if (ex == null) {
                System.out.println("✅ Sent: " + message
                  + " (offset " + res.getRecordMetadata().offset() + ")");
              } else {
                System.err.println("❌ Failed to send: " + ex.getMessage());
              }
            });
    }

    public void sendCustomerMessageToTopic(Customer customerDTO) {
        kafkaTemplate.send("Customer-topic", customerDTO)
            .whenComplete((res, ex) -> {
              if (ex == null) {
                System.out.println("✅ Sent: " + customerDTO
                  + " (offset " + res.getRecordMetadata().offset() + ")");
              } else {
                System.err.println("❌ Failed to send: " + ex.getMessage());
              }
            });
    }
}
