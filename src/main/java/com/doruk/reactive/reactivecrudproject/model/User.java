package com.doruk.reactive.reactivecrudproject.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class User {
    @Id
    private String id;
    private String name;
    private Integer age;
    private Double salary;

    public User() {

    }

    public User(String name, Integer age, Double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
}
