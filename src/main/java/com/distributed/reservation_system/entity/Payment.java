package com.distributed.reservation_system.entity;

import com.distributed.reservation_system.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private UUID paymentId;

    @Column(nullable = false)
    private BigDecimal totalAmount;

    @Column(nullable = true)
    private String method;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 16)
    private PaymentStatus status;

    public Payment(BigDecimal bigDecimal, String method, PaymentStatus paymentStatus) {
        this.totalAmount = bigDecimal;
        this.method = method;
        this.status = paymentStatus;
    }
}
