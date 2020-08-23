package com.elon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProducerConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProducerConsumerApplication.class);
        System.out.println("Startup ProducerConsumerApplication success.");
    }
}
