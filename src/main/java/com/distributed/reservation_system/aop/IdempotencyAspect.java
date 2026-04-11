package com.distributed.reservation_system.aop;

import com.distributed.common.dto.ReservationRequest;
import com.distributed.common.exception.IdempotentException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@Slf4j
@Aspect
@RequiredArgsConstructor
public class IdempotencyAspect {
    private final RedissonClient redissonClient;

    @Around("@annotation(IdempotentReservation)")
    public Object handleIdempotentReservation(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ReservationRequest reservationRequest = (ReservationRequest) proceedingJoinPoint.getArgs()[0];

        String idempotentKey = "idempotent:Reservation:"+reservationRequest.getUserId()+":"+reservationRequest.getTripId();

        RBucket<String> idempotentBucket = redissonClient.getBucket(idempotentKey);
        boolean isNewKey = idempotentBucket.setIfAbsent("PROCESSING", Duration.ofMinutes(1));
        log.info("IdempotentKey:{}, exists?:{}",idempotentKey,isNewKey);
        if(!isNewKey){
            throw new IdempotentException("Reservation already in progress..");
        }

        try{
            return proceedingJoinPoint.proceed();
        }catch (Throwable ex){
            idempotentBucket.delete();
            log.info("Cleaned up idempotent bucket");
            throw ex; //so exception can be handled by global handler
        }
    }

}
