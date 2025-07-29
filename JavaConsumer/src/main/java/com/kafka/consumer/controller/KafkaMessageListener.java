package com.kafka.consumer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

import com.kafka.consumer.dto.Customer;

@Service
public class KafkaMessageListener {
    private final Logger log = LoggerFactory.getLogger(KafkaMessageListener.class);

    // listen to plain‑text on "default-topic"
    @KafkaListener(
      topics = "default-topic",
      groupId = "tt-1",
      containerFactory = "stringKafkaListenerContainerFactory",
      topicPartitions = {@TopicPartition(topic="default-topic", partitions = {"2"})}
    )
    public void consumeText(String message) {
        log.info("Text consumer got → {}", message);
    }

    // listen to JSON on "Customer-topic"
    @KafkaListener(
      topics = "Customer-topic",
      groupId = "ct-1"
    )
    public void consumeCustomer(Customer customer) {
        log.info("JSON consumer got → {}", customer);
    }
}
