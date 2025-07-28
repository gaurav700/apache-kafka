package com.kafka.consumer.dto;

import lombok.Data;

@Data
public class Customer {
    private long id;
    private String name;
    private int age;
    private String email;
}
