package com.distributed.reservation_system.controller;

import com.distributed.common.dto.ReservationRequest;
import com.distributed.reservation_system.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
@Slf4j
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/")
    public ResponseEntity<String> doReservation(@Valid @RequestBody ReservationRequest reservationRequest, Authentication authentication){
        UserDetails userDetails  = (UserDetails) authentication.getPrincipal();
        Long userId = Long.valueOf(userDetails.getUsername());
        String reservationMessage = reservationService.bookTicket(reservationRequest, userId);
        return new ResponseEntity<>(reservationMessage, HttpStatus.OK); //todo: Send 202 Accpeted HTTP code. - we have delegated
    }

    @GetMapping("/test")
    public void test() throws InterruptedException {
        int noOfUser=500;

        CyclicBarrier cyclicBarrier = new CyclicBarrier(noOfUser);
        CountDownLatch countDownLatch = new CountDownLatch(noOfUser);
        ExecutorService executorService = Executors.newFixedThreadPool(noOfUser);

        AtomicInteger successCount = new AtomicInteger(0);

        long startTime = System.currentTimeMillis();
        for(int i=1;i<=noOfUser;i++){
            ReservationRequest reservationRequest = createReservationRequest((long) i);
            System.out.println("StartTime:"+startTime);
            executorService.submit(()->{
                try{
                    cyclicBarrier.await();
                    reservationService.bookTicket(reservationRequest, reservationRequest.getUserId());
                    System.out.println("success"+(System.currentTimeMillis()-startTime));
                    successCount.incrementAndGet();

                } catch (Exception e) {
                    System.out.println("Exception: "+e.getMessage());
//                    throw new RuntimeException(e);
                }finally {
                    countDownLatch.countDown();
                }
            });

        }

        System.out.println("here reached at:"+(System.currentTimeMillis()-startTime));


        countDownLatch.await();
        long endTime = System.currentTimeMillis();

        System.out.println("--- BASELINE RESULTS (DB ONLY) ---");
        System.out.println("Total Time: " + (endTime - startTime) + "ms");
        System.out.println("Final Success Count: " + successCount.get());
        executorService.shutdown();
    }

    public ReservationRequest createReservationRequest(Long userId){
        List<Long> masterPassengerList = new ArrayList<>();
        masterPassengerList.add(userId+1000);
        return new ReservationRequest(userId,masterPassengerList, 37L);
    }
}
