package com.example.transaction_aggregator.transaction_aggregator.services;

import com.example.transaction_aggregator.transaction_aggregator.entities.Message;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
@Service
@Getter
@Setter
@EnableScheduling
public class MessageQueueService {

    private final LinkedBlockingQueue<Message> queue = new LinkedBlockingQueue<>();

    private final long expirationMillis = 4*60*1000;

    private final long scheduledTime = 1*60*1000;

    // Concern : do we need to wrap it in a synchronized block? No BlockingQueue itself takes care
    // of this issue since it is very thread safe
    //
    @Scheduled(fixedRate = scheduledTime)
    private void removeExpiredMessages() {
        queue.removeIf(message -> message.isExpired(expirationMillis));
        log.info("Removed expired messages");
    }

}
