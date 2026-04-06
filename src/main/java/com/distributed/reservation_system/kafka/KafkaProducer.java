package com.distributed.reservation_system.kafka;

import com.distributed.common.dto.ReservationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProducer {
    @Value("${spring.kafka.topic.reservation-request}")
    private String topicName;

    @Autowired
    public KafkaTemplate<String, ReservationRequest> kafkaTemplate;

    public void send(ReservationRequest reservationRequest){
        log.info("Passing request over kafka for UserId: {}", reservationRequest.getUserId());
        try{
            kafkaTemplate.send(topicName, reservationRequest);
        }catch (Exception e){
            log.info("Could not send over kafka for userId:{}",reservationRequest.getUserId());
        }

    }
}
