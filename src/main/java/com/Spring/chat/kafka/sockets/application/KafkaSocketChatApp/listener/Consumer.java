package com.Spring.chat.kafka.sockets.application.KafkaSocketChatApp.listener;

import com.Spring.chat.kafka.sockets.application.KafkaSocketChatApp.constant.KafkaConstants;
import com.Spring.chat.kafka.sockets.application.KafkaSocketChatApp.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Component
public class Consumer {

    @Autowired
    SimpMessagingTemplate messagingTemplate;

    @KafkaListener(topics= KafkaConstants.TOPIC, groupId = KafkaConstants.GROUP_ID)
    public void consume(Movie movie)
    {
        System.out.println(movie);
        messagingTemplate.convertAndSend("/topic/group", movie);
    }
}
