package com.Spring.chat.kafka.sockets.application.KafkaSocketChatApp.service;

import com.Spring.chat.kafka.sockets.application.KafkaSocketChatApp.model.Movie;
import com.Spring.chat.kafka.sockets.application.KafkaSocketChatApp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MovieServiceImplementation implements MovieService {

    MovieRepository movieRepository;

    @Autowired
    public MovieServiceImplementation(MovieRepository repository) {
        this.movieRepository = repository;
    }

    @Override
    public List<Movie> findByName(String name) {
        return movieRepository.findByName(name);
    }

    @Override
    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }


    @Override
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }
}