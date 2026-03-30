package com.distributed.reservation_system.dto;

import com.distributed.reservation_system.enums.PaymentStatus;

import java.math.BigDecimal;
import java.util.UUID;

public class PaymentResponseDTO {
    private UUID paymentId;
    private BigDecimal totalAmount;
    private String method;
    private PaymentStatus status;
}
