package com.kafka.producer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(
          AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, 
          "localhost:9092"
        );
        return new KafkaAdmin(configs);
    }

    // this will create “default-topic” on startup
    @Bean
    public NewTopic defaultTopic() {
        return TopicBuilder
                 .name("default-topic")
                 .partitions(5)
                 .replicas(1)
                 .build();
    }

    @Bean
    public NewTopic customerTopic() {
        return TopicBuilder
                 .name("Customer-topic")
                 .partitions(5)
                 .replicas(1)
                 .build();
    }
}
