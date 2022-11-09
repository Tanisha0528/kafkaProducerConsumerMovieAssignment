package com.Spring.chat.kafka.sockets.application.KafkaSocketChatApp.service;

import com.Spring.chat.kafka.sockets.application.KafkaSocketChatApp.model.Movie;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieService {

    List<Movie> findByName(String name);

    List<Movie> findAllMovies();

    Movie saveMovie(Movie movie);

}