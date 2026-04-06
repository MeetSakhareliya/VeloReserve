package com.distributed.reservation_system.util;

import com.distributed.common.repository.TrainTripRepository;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RedisInitializer implements CommandLineRunner {
    @Autowired
    TrainTripRepository trainTripRepository;

    @Autowired
    RedissonClient redissonClient;


    @Override
    public void run(String... args) throws Exception {
        trainTripRepository.findAll().forEach(trainTrip ->{
            RAtomicLong redisSeats = redissonClient.getAtomicLong("train_seats:"+trainTrip.getId());
            redisSeats.set(trainTrip.getAvailableCapacity());
        });
    }
}
