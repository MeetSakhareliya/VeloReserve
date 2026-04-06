package com.distributed.reservation_system.config;

import com.distributed.common.dto.ReservationRequest;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.topic.reservation-request}")
    private String topicName;

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.producer.linger.ms}")
    private String lingerMS;

    @Value("${spring.kafka.producer.batch.size}")
    private String batchSize;

    @Bean
    public NewTopic createNewTopic(){
//        System.out.println("Topic name;"+topicName);
        return TopicBuilder.name(topicName)
                .partitions(6) //Todo:didin't work
                .replicas(1)
                .build();
    }

    @Bean
    public ProducerFactory<String, ReservationRequest> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        // Optimization for 100k test
        configProps.put(ProducerConfig.LINGER_MS_CONFIG, lingerMS);
        configProps.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
        configProps.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, ReservationRequest> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
