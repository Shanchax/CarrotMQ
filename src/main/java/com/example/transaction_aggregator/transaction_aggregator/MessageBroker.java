package com.example.transaction_aggregator.transaction_aggregator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MessageBroker {

    public static void main(String[] args) {
        SpringApplication.run(MessageBroker.class, args);

    }

}
