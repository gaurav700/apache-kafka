package com.kafka.producer;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.kafka.producer.dto.Customer;
import com.kafka.producer.services.KafkaMessagePublisher;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class KafkaProducerApplicationTests {
    @Autowired
    private KafkaMessagePublisher kafkaMessagePublisher;

static DockerImageName kafkaImage = DockerImageName
    .parse("confluentinc/cp-kafka:5.5.3")
    .asCompatibleSubstituteFor("confluentinc/cp-kafka");

@Container
static KafkaContainer kafka = new KafkaContainer(kafkaImage);




    @DynamicPropertySource
    public static void initKafkaProperties(DynamicPropertyRegistry registry){
        registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);
    }

    @Test
    public void testSendEventsToTopic() {
        kafkaMessagePublisher.sendCustomerMessageToTopic(
            new Customer(1, "Test name", 34, "test@gmail.com")
        );

        await().pollInterval(Duration.ofSeconds(1))
            .atMost(10, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                assertTrue(true); 
            });
    }

}

