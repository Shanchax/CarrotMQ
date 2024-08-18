package com.example.transaction_aggregator.transaction_aggregator.services;

import com.example.transaction_aggregator.transaction_aggregator.entities.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
@Service
public class MessagePublishService {

    @Autowired
    private MessageQueueService messageQueueService;

    @Async
    // since blocking queue is by nature thread safe , we do not have to worry about locking before attempting to
    //put in the queue, we can rest assured that at any given time one thread has lock on it
    public void publish(Message message) {

        // TODO : add a time limit on retries, since this might descend into an infinite loop
        boolean added = false;
        while (!added) {
            try{
                messageQueueService.getQueue().put(message);
                added = true;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.info("thread interrupted");
                try{
                    Thread.sleep(10);

                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(ex);
                }
            }
        }

    }



}
