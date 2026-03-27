package com.distributed.reservation_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class TrainTrip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "train_id")
    private Train train;

    @Column(nullable = false)
    private LocalDateTime departureTime;

    @Column(nullable = false)
    private LocalDateTime arrivalTime;

    @Min(0)
    @Column(nullable = false)
    private Integer availableCapacity; // Synchronized with Redis

    @Version
    private Long version; // as we are using pessimistid locking we do not require this now. But will keep for double safety and other rare contenation task like changing capacity, designation.
}
