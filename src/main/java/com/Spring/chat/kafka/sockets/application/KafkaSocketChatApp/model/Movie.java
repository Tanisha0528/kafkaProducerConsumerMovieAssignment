package com.Spring.chat.kafka.sockets.application.KafkaSocketChatApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "detail")
public class Movie {

    private String name;
    private int rating;
    private String timestamp;
}
