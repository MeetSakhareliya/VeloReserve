package com.distributed.reservation_system.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TrainTripResponseDTO {
    private Long trainTripid;
    private Long trainId;
    private String name;
    private String sourceStation;
    private String destinationStation;
    private Integer ticketPrice;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
}
