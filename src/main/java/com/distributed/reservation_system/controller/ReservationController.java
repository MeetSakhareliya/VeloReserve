package com.distributed.reservation_system.controller;

import com.distributed.reservation_system.dto.ReservationRequest;
import com.distributed.reservation_system.dto.ReservationResponse;
import com.distributed.reservation_system.entity.Reservation;
import com.distributed.reservation_system.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
@Slf4j
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/")
    public ResponseEntity<ReservationResponse> doReservation(@Valid @RequestBody ReservationRequest reservationRequest){
        ReservationResponse reservation = reservationService.bookTicket(reservationRequest);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }
}
