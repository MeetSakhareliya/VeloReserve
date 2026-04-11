package com.distributed.reservation_system.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.BucketConfiguration;
import io.github.bucket4j.Refill;
import io.lettuce.core.RedisClient;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@Slf4j
public class RedisConfig {
    @Value("${system-properties.rate-limiter.capacity}")
    private int capacity;

    @Bean
    public RedisClient redisClient() {
        return RedisClient.create("redis://127.0.0.1:6379");
    }

    @Bean
    public RedissonClient redissonClient(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        config.setCodec(new org.redisson.client.codec.IntegerCodec());
        return Redisson.create(config);
    }

    @Bean
    public BucketConfiguration createRateLimiterBucket(){
        log.info("Creating rate limiting bucket with capacity:{}",capacity);
        return  BucketConfiguration.builder()
                .addLimit(Bandwidth.classic(capacity, Refill.intervally(10, Duration.ofSeconds(30))))
                .build();
    }
}
