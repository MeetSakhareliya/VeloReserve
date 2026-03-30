package com.distributed.reservation_system.mapper;

import com.distributed.reservation_system.dto.*;
import com.distributed.reservation_system.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EntityMapper {

    @Mapping(source="userId.userId", target="userId")
    ReservationResponse toReservationResponse(Reservation reservation);
    List<ReservationResponse> toReservationResponse(List<Reservation> reservation);


    @Mapping(source="id", target="trainTripid")
    @Mapping(source="train.id", target="trainId")
    @Mapping(source="train.name", target = "name")
    @Mapping(source="train.sourceStation", target = "sourceStation")
    @Mapping(source="train.destinationStation", target = "destinationStation")
    TrainTripResponseDTO toTrainTripResponse(TrainTrip trainTrip);
    List<TrainTripResponseDTO> toTrainTripResponse(List<TrainTrip> trainTrip);

    ReservationPassengerResponseDTO toPassengerResponse(ReservationPassenger passenger);
    PaymentResponseDTO toPaymentResponse(Payment payment);

    @Mapping(source = "masterPassengerList", target = "masterPassengers")
    UserProfileResponseDTO toUserProfileResponse(User user);
    List<UserProfileResponseDTO> toUserProfileResponse(List<User> user);
    UserSummaryResponseDTO toUserSummaryResponse(User user);


    MasterPassenger toMasterPassenger(MasterPassenger entity);
    List<MasterPassenger> toMasterPassenger(List<MasterPassenger> entity);

}
