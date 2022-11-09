package com.Spring.chat.kafka.sockets.application.KafkaSocketChatApp.controller;

import com.Spring.chat.kafka.sockets.application.KafkaSocketChatApp.constant.KafkaConstants;
import com.Spring.chat.kafka.sockets.application.KafkaSocketChatApp.model.Movie;
import com.Spring.chat.kafka.sockets.application.KafkaSocketChatApp.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private KafkaTemplate<String, Movie> kafkaTemplate;

    @Autowired
    MovieService service;

    @PostMapping(value="/saveMovie",consumes = "application/json",produces = "application/json")
    public void saveMovie(@RequestBody Movie movie)
    {

        try{

            kafkaTemplate.send(KafkaConstants.TOPIC, movie).get();
            service.saveMovie(movie);
        }
        catch(InterruptedException | ExecutionException e)
        {
           throw new RuntimeException(e);
        }
    }

    @MessageMapping("/sendMovie")
    @SendTo("/topic/group")
    public Movie broadcastGroupMessage(@Payload Movie movie)
    {
        return movie;
    }

    @MessageMapping("/newMovie")
    @SendTo("/topic/group")
    public Movie addMovie(@Payload Movie movie, SimpMessageHeaderAccessor headerAccessor)
    {
        //try to add user for web socket api user
        headerAccessor.getSessionAttributes().put("movieName", movie.getName());
        return movie;
    }

    @PostMapping("/findByName")
    public ResponseEntity<?> findByName(@RequestBody Map<String,Object> obj) {
        return ResponseEntity.ok().body(service.findByName(obj.get("name").toString()));
    }
}
