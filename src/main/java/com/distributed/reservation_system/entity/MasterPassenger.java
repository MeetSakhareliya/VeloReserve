package com.distributed.reservation_system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MasterPassenger {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private Integer age;

    @Column(nullable = true)
    private String gender;
}
