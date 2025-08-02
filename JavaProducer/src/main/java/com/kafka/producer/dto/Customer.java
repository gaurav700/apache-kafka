package com.kafka.producer.dto;

import lombok.Data;

@Data
public class Customer {
    private long id;
    private String name;
    private int age;
    private String email;

    public Customer(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }
}
