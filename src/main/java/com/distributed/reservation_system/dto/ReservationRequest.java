package com.distributed.reservation_system.dto;

import com.distributed.reservation_system.entity.MasterPassenger;
import com.distributed.reservation_system.entity.User;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ReservationRequest {
    @NotNull(message = "User ID is required")
    private Long userId;

    @NotEmpty(message = "Passenger list can't be empty")
    private List<Long> masterPassengerIdList;

    @NotNull(message = "Trip id can't be null")
    private Long tripId;
}
