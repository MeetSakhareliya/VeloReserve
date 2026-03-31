package com.distributed.reservation_system.service;

import com.distributed.reservation_system.dto.ReservationRequest;
import com.distributed.reservation_system.dto.ReservationResponse;
import com.distributed.reservation_system.entity.*;
import com.distributed.reservation_system.enums.PaymentStatus;
import com.distributed.reservation_system.enums.ReservationStatus;
import com.distributed.reservation_system.exception.BusinessException;
import com.distributed.reservation_system.exception.SystemException;
import com.distributed.reservation_system.exception.ValidationException;
import com.distributed.reservation_system.mapper.EntityMapper;
import com.distributed.reservation_system.repository.MasterPassengerRepository;
import com.distributed.reservation_system.repository.ReservationRepository;
import com.distributed.reservation_system.repository.TrainTripRepository;
import com.distributed.reservation_system.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final TrainTripRepository trainTripRepository;
    private final UserRepository userRepository;
    private final MasterPassengerRepository masterPassengerRepository;
    private final ReservationRepository reservationRepository;
    private final EntityMapper entityMapper;
    private final PaymentService paymentService;

    @Transactional
    public ReservationResponse bookTicket(ReservationRequest reservationRequest){
        TrainTrip trainTrip;
        try{
            trainTrip = trainTripRepository.findTripWithLock(reservationRequest.getTripId())
                    .orElseThrow(()-> new ValidationException("Not a valid trip id. Train trip not found"));
        }catch (PessimisticLockingFailureException ex){
            throw new SystemException("This train is currently in high demand. Please retry.",ex);
        }

        User user = userRepository.findById(reservationRequest.getUserId())
                .orElseThrow(() -> new BusinessException("User not found"));

        List<Long> passengerIds = reservationRequest.getMasterPassengerIdList();
        List<MasterPassenger> masterPassengerList = masterPassengerRepository.findByIdsAndUserId(passengerIds, reservationRequest.getUserId());  //this will only returns data which are present. So need to check if all ids are available or not.

        if(masterPassengerList.size() != passengerIds.size()){
            Set<Long> masterListIds = masterPassengerList.stream()
                    .map(MasterPassenger::getId)
                    .collect(Collectors.toSet());

            List<Long> missingIds = passengerIds.stream()
                    .filter(requestId -> !masterListIds.contains(requestId))
                    .toList();

            throw new ValidationException("The following passenger IDs are invalid: " + missingIds);
        }

        if(trainTrip.getAvailableCapacity()<passengerIds.size()){
            throw new BusinessException("Not enough seats available");
        }

        trainTrip.setAvailableCapacity(trainTrip.getAvailableCapacity()-passengerIds.size());
        trainTripRepository.save(trainTrip);
        //Todo: payment will be done here first.

        Payment payment = paymentService.pay(trainTrip.getTrain().getTicketPrice()*passengerIds.size());
        if(payment.getStatus()!= PaymentStatus.CONFIRMED){
            throw new BusinessException("Payment failed");
        }

        Reservation reservation = new Reservation();
        reservation.setTrainRun(trainTrip);
        reservation.setUserId(user);
        reservation.setStatus(ReservationStatus.CONFIRMED);
        reservation.setPaymentId(payment);

        List<ReservationPassenger> reservationPassengers = new ArrayList<>();
        masterPassengerList.forEach(p -> reservationPassengers.add(new ReservationPassenger(reservation, p.getName(),p.getAge())));
        reservation.setPassengers(reservationPassengers);
        reservationRepository.save(reservation);

        return entityMapper.toReservationResponse(reservation);

        /*

            1.Lock in traintripRepository but effect in this function.: At the start of function we have applied Transactional annotation so connection scope extend till complete bookTicket function is not executed(Which includes repository call where lock is applied and thus lock also gets extended.
            2.why @Transactional annotation was added.: it is required for all service level where database is involved(simple get call to database can be excluded)
            3. What if we do not lock on database and make this entire function synchronized?
            4. can we explicitely remove lock before function ends?

         */
    }

}


