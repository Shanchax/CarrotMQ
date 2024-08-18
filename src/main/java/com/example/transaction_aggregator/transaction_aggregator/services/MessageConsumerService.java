package com.example.transaction_aggregator.transaction_aggregator.services;

import com.example.transaction_aggregator.transaction_aggregator.entities.Message;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@Getter
@Setter
public class MessageConsumerService {

    @Autowired
    private MessageQueueService messageQueueService;

    @Async
    public CompletableFuture<Message> consume() throws InterruptedException {

        try {
            Message message = messageQueueService.getQueue().peek();
            if (message == null) {
                log.info("Message queue is empty");
            } else {
                log.info("Message consumed");
            }
            return CompletableFuture.completedFuture(message);

        } catch (Exception e) {
            log.error("Error while consuming message : {}" , e.getMessage());
        }

        return CompletableFuture.completedFuture(null);

    }

}
