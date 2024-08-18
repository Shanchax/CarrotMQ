package com.example.transaction_aggregator.transaction_aggregator.controllers;

import com.example.transaction_aggregator.transaction_aggregator.entities.Message;
import com.example.transaction_aggregator.transaction_aggregator.services.MessageConsumerService;
import com.example.transaction_aggregator.transaction_aggregator.services.MessagePublishService;
import com.example.transaction_aggregator.transaction_aggregator.services.MessageQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
public class MessageController {

    // autowired is not required here since
    // messageQueue has been annotated as a service
    @Autowired
    private MessageQueueService messageQueueService;
    @Autowired
    private MessagePublishService messagePublishService;
    @Autowired
    private MessageConsumerService messageConsumerService;

    @GetMapping(value = "/")
    public String testLandingPage() {
        return "Dispatcher Servlet has been started";
    }

    @PostMapping(value = "/publish" , consumes = {"application/json"})
    public void publishMessage(@RequestBody Message message) {
        messagePublishService.publish(message);
    }

    @GetMapping("/consume")
    public CompletableFuture<Message> getMessage() throws InterruptedException{
        return messageConsumerService.consume();
    }
}
