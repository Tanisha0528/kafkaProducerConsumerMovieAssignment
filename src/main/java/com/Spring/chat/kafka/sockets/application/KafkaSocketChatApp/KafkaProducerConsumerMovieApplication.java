package com.Spring.chat.kafka.sockets.application.KafkaSocketChatApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class KafkaProducerConsumerMovieApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerConsumerMovieApplication.class, args);
	}

}
