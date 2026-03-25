package com.distributed.reservation_system.Entity;

import com.distributed.reservation_system.Enum.ReservationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "train_run_id")
    private TrainTrip trainRun;

//    @Min(1)
//    @Max(6)
//    private Integer noOfSeats; //For each seat there will be one row entry, so even

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User userId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private List<ReservationPassenger> passengers;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(32) check (status in ('PENDING', 'CONFIRMED', 'EXPIRED', 'CANCELLED'))")
    private ReservationStatus status; // PENDING, CONFIRMED, EXPIRED

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "paymentId")
    private Payment paymentId;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
