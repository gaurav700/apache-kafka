package com.kafka.java_tutorial.services;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessagePublisher {
    @Autowired
    private final KafkaTemplate<String, Object> kafkaTemplate = null;
    
    public void sendMessageToTopic(String message) {
        CompletableFuture<SendResult<String, Object>> future= kafkaTemplate.send("javaTutorial-topic", message);
        future.whenComplete((result, ex)->{
            if(ex == null){
                System.out.println("Sent message=["+message+"] with offset=["+result.getRecordMetadata().offset()+"]");
            }else{
                System.out.println("Unable to send Message=["+message+"] due to : "+ ex.getMessage());
            }
        });
    }
}
