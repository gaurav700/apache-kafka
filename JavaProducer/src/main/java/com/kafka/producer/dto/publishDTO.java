package com.kafka.producer.dto;

import lombok.Data;

@Data
public class publishDTO {
    private String topicName;
    private String message;   

}
