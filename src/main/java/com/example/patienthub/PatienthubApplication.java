package com.example.patienthub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.patienthub.model")
public class PatienthubApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatienthubApplication.class, args);
        System.out.println("Hello world");
    }

}
