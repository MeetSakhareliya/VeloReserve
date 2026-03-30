package com.distributed.reservation_system.dto;

import java.time.LocalDateTime;

public class TrainTripResponseDTO {
    private Long trainTripid;
    private Long trainId;
    private String name;
    private String sourceStation;
    private String destinationStation;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
}
