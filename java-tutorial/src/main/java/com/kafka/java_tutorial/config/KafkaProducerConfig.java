package com.kafka.java_tutorial.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaProducerConfig {

    public NewTopic createTopic(String topicName){
        return new NewTopic(topicName, 5, (short) 1);
    }
}
