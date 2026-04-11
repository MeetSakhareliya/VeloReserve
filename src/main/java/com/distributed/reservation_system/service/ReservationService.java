package com.distributed.reservation_system.service;

import com.distributed.common.dto.ReservationRequest;
import com.distributed.common.entity.*;
import com.distributed.common.exception.BusinessException;
import com.distributed.common.exception.ValidationException;
import com.distributed.common.repository.MasterPassengerRepository;
import com.distributed.common.repository.UserRepository;
import com.distributed.reservation_system.aop.IdempotentReservation;
import com.distributed.reservation_system.kafka.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RScript;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationService {

    private final UserRepository userRepository;
    private final MasterPassengerRepository masterPassengerRepository;
    private final RedissonClient redisson;
    private final KafkaProducer kafkaProducer;
    private final RedissonClient redissonClient;

    @Transactional
    @IdempotentReservation
    public String bookTicket(ReservationRequest reservationRequest){
        log.info("Reservation request from user:{} for tripId:{}", reservationRequest.getUserId(), reservationRequest.getTripId());
        List<Long> passengerIds = reservationRequest.getMasterPassengerIdList();
        if (!userRepository.existsById(reservationRequest.getUserId())) {
            throw new BusinessException("User not found"); //todo: Add errorCodes as well.
        }

        List<MasterPassenger> masterPassengerList = masterPassengerRepository.findByIdsAndUserId(passengerIds, reservationRequest.getUserId());  //this will only returns data which are present. So need to check if all ids are available or not.
        //todo: here we just check if record exist or not rather fetching.
        if(masterPassengerList.size() != passengerIds.size()){
            Set<Long> masterListIds = masterPassengerList.stream()
                    .map(MasterPassenger::getId)
                    .collect(Collectors.toSet());

            List<Long> missingIds = passengerIds.stream()
                    .filter(requestId -> !masterListIds.contains(requestId))
                    .toList();

            throw new ValidationException("The following passenger IDs are invalid: " + missingIds);
        }

        Long remainingSeats = findRemainingSeats(reservationRequest.getTripId(),passengerIds.size());
        if(remainingSeats == -1) {
            throw new BusinessException("Not enough seats available or no valid trip.");
        }
        log.info("Trip:{}, remaining seat:{}", reservationRequest.getTripId(), remainingSeats);
        //Todo: what if it fails here.

        kafkaProducer.send(reservationRequest);

        redissonClient.getAtomicLong("pending_requests:"+reservationRequest.getTripId()).incrementAndGet(); //Will be used for reconciliation.


        return "Your request is in progress...";
        /*

            1.Lock in traintripRepository but effect in this function.: At the start of function we have applied Transactional annotation so connection scope extend till complete bookTicket function is not executed(Which includes repository call where lock is applied and thus lock also gets extended.
            2.why @Transactional annotation was added.: it is required for all service level where database is involved(simple get call to database can be excluded)
            3. What if we do not lock on database and make this entire function synchronized?
            4. can we explicitely remove lock before function ends?

         */
    }

    private Long findRemainingSeats(Long tripId, int size) {
        System.out.println(tripId+" "+size);
        String script =
                "local current = redis.call('get',KEYS[1]); "+
                "if current and tonumber(current)>=tonumber(ARGV[1]) then "+
                    "return redis.call('decrby', KEYS[1], ARGV[1]); " +
                "else " +
                    "return -1; "+
                "end;";

        return redisson.getScript().eval(
                RScript.Mode.READ_WRITE,
                script,
                RScript.ReturnType.LONG,
                Collections.singletonList("train_seats:" + tripId),
                Integer.valueOf(size)
        );
    }
}


