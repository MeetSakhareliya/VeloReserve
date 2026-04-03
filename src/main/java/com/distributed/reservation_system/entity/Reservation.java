package com.distributed.reservation_system.entity;

import com.distributed.reservation_system.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Reservation {
    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private UUID id;   //works as a PNR.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "train_run_id")
    private TrainTrip trainRun;

//    @Min(1)
//    @Max(6)
//    private Integer noOfSeats; //For each seat there will be one row entry, so even

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User userId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reservation")
    private List<ReservationPassenger> passengers;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 16)
    private ReservationStatus status; // PENDING, CONFIRMED, EXPIRED

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE} )
    @JoinColumn(name = "paymentId")
    private Payment paymentId;

    @CreatedDate  //for this to work EntityListeners and EnableJpaAuditing needs to be added,
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
