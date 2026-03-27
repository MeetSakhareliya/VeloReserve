package com.distributed.reservation_system.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users") //user is a reserved keyword in PostgreSQL. That's why seperate keyword
public class User {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String mobile;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<MasterPassenger> masterPassengerList;
}
