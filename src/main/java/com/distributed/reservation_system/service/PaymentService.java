package com.distributed.reservation_system.service;

import com.distributed.reservation_system.entity.Payment;
import com.distributed.reservation_system.enums.PaymentStatus;
import com.distributed.reservation_system.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public Payment pay(Integer totalAmount){
        PaymentStatus[] paymentStatuses = PaymentStatus.values();
        Payment payment = new Payment(BigDecimal.valueOf(totalAmount), "UPI", paymentStatuses[new Random().nextInt(paymentStatuses.length)]);
        paymentRepository.save(payment);
        return payment;
    }
}