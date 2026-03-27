package com.distributed.reservation_system.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class ErrorResponse {
    private String error;
    private String message;
    private Timestamp time;
}
