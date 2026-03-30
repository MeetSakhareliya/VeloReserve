package com.distributed.reservation_system.controller;

import com.distributed.reservation_system.dto.ReservationRequest;
import com.distributed.reservation_system.entity.Reservation;
import com.distributed.reservation_system.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/")
    public ResponseEntity<Reservation> doReservation(@Valid ReservationRequest reservationRequest){
        Reservation reservation = reservationService.bookTicket(reservationRequest);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }
}
