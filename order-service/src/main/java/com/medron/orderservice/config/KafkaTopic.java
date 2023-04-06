package com.medron.orderservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {
    @Value("${spring.kafka.topic.name}")
    String topicName;
    @Bean
    public NewTopic getTopic(){
        return TopicBuilder.name(topicName).partitions(3).build();

    }
}
