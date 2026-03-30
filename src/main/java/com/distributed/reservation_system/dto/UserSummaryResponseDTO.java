package com.distributed.reservation_system.dto;

import lombok.Data;

@Data
public class UserSummaryResponseDTO {
    private Long userId;
    private String email;
    private String mobile;
    private String name;
}
