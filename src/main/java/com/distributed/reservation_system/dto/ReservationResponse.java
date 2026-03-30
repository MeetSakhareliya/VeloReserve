package com.distributed.reservation_system.dto;


import com.distributed.reservation_system.entity.User;
import com.distributed.reservation_system.enums.ReservationStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class ReservationResponse {
    private UUID id;
    private TrainTripResponseDTO trainTrip;
    private Long userId; //Not returning private UserResponseDTO user;  Because in UserService is different microservice then need to do grpc call in future. Use case of grpc.
    private List<ReservationPassengerResponseDTO> passengers;
    private ReservationStatus status;
    private PaymentResponseDTO payment;
    private LocalDateTime createdAt;
}
