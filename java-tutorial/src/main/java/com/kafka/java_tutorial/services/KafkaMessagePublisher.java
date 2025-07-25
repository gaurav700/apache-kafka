package com.kafka.java_tutorial.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.kafka.java_tutorial.dto.publishDTO;

@Service
public class KafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessageToTopic(publishDTO publishdto) {
        kafkaTemplate.send(publishdto.getTopicName(), publishdto.getMessage())
            .whenComplete((result, ex) -> {
                if (ex == null) {
                    System.out.println("✅ Sent message: " + publishdto.getMessage() +
                            " to topic: " + publishdto.getTopicName() +
                            " offset: " + result.getRecordMetadata().offset());
                } else {
                    System.out.println("❌ Failed to send: " + ex.getMessage());
                }
            });
    }
}
