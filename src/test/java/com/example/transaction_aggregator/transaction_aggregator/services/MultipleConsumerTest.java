package com.example.transaction_aggregator.transaction_aggregator.services;

import com.example.transaction_aggregator.transaction_aggregator.entities.Message;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Objects;
import java.util.concurrent.*;

@SpringBootTest
public class MultipleConsumerTest {

    private static final Logger log = LoggerFactory.getLogger(MultipleConsumerTest.class);
    @Autowired
    private MessageQueueService messageQueueService;

    @Autowired
    private MessageConsumerService messageConsumerService;


    @Autowired
    private MessagePublishService messagePublishService;

    @Test
    public void testMultipleConsumer() throws ExecutionException, InterruptedException {

        messagePublishService.publish(new Message("234" , "32789" , Instant.now()));

        CountDownLatch latch = new CountDownLatch(2);

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        //lambda funtions
        Runnable consumer = () -> {
            try {
                latch.await();
                messageConsumerService.consume();
                log.info("Consumed message");

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Future<?> future =  executorService.submit(consumer);
        Future<?> future1 = executorService.submit(consumer);

        latch.countDown();
        latch.countDown();

        executorService.shutdown();

        executorService.awaitTermination(10, TimeUnit.SECONDS);


    }
}
