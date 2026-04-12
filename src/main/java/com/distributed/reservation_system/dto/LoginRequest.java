package com.distributed.reservation_system.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String userId;
    private String password;

}
