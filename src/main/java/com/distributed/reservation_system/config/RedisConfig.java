package com.distributed.reservation_system.config;

import io.lettuce.core.RedisClient;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {
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
}
