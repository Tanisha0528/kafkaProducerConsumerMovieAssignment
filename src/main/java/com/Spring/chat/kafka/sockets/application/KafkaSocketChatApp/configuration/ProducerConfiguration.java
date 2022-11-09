package com.Spring.chat.kafka.sockets.application.KafkaSocketChatApp.configuration;


import com.Spring.chat.kafka.sockets.application.KafkaSocketChatApp.constant.KafkaConstants;
import com.Spring.chat.kafka.sockets.application.KafkaSocketChatApp.model.Movie;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class ProducerConfiguration {
    @Bean
    public ProducerFactory<String, Movie> producerFactory()
    {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }
     @Bean
    public Map<String, Object> producerConfig()
    {
        Map<String, Object> config=new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.BROKER);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return config;

    }
    @Bean
    public KafkaTemplate<String, Movie> kafkaTemplate()
    {
        return new KafkaTemplate<>(producerFactory());
    }

}
