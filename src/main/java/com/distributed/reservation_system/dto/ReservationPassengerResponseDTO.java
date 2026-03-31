package com.distributed.reservation_system.dto;

import lombok.Data;

@Data
public class ReservationPassengerResponseDTO {
    private Long id;
    private String name;
    private Integer age;
    private String seatNumber;
}
