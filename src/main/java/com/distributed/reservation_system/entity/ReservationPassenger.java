package com.distributed.reservation_system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ReservationPassenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    @JsonIgnore
    private Reservation reservation;

    private String name;      // Copied from Master List
    private Integer age;       // Copied from Master List
    private String seatNumber; // Assigned by system

    public ReservationPassenger(Reservation reservation, String name, int age){
        this.reservation = reservation;
        this.name = name;
        this.age = age;
    }
}
