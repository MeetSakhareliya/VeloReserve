package com.distributed.reservation_system.dto;

import com.distributed.reservation_system.entity.MasterPassenger;

import java.util.List;

public class UserProfileResponseDTO {
    private Long userId;
    private String email;
    private String mobile;
    private String name;
    private List<MasterPassenger> masterPassengers;
}
