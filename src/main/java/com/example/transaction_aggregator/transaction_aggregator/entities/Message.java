package com.example.transaction_aggregator.transaction_aggregator.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class Message {

    private String id;
    private String content;
    private final Instant timeStamp;

    public Message(String id, String content, Instant timeStamp) {
        this.id = id;
        this.content = content;
        this.timeStamp = Instant.now();
    }
    public boolean isExpired(long expirationMillis) {
        return Instant.now().toEpochMilli() - this.timeStamp.toEpochMilli() > expirationMillis;
    }
}
