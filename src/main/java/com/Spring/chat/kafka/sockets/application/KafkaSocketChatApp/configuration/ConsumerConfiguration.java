package com.Spring.chat.kafka.sockets.application.KafkaSocketChatApp.configuration;

import com.Spring.chat.kafka.sockets.application.KafkaSocketChatApp.constant.KafkaConstants;
import com.Spring.chat.kafka.sockets.application.KafkaSocketChatApp.model.Movie;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;
@EnableKafka
@Configuration
public class ConsumerConfiguration {
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Movie> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, Movie> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, Movie> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(
                consumerConfig(),
                new StringDeserializer(),
                new JsonDeserializer<>(Movie.class)
        );
    }

    @Bean
    public Map<String, Object> consumerConfig( ){
        Map<String,Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.BROKER);
        config.put(ConsumerConfig.GROUP_ID_CONFIG,KafkaConstants.GROUP_ID);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"latest");
        return config;
    }
}
