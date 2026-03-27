package com.distributed.reservation_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "train_no_seq")
    @SequenceGenerator(name = "train_no_seq", initialValue =  10000 , allocationSize = 5)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String sourceStation;

    @Column(nullable = false)
    private String destinationStation;

    @Min(1)
    @Max(2000)
    private Integer totalCapacity;
}