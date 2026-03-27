package com.distributed.reservation_system.service;

import com.distributed.reservation_system.entity.*;
import com.distributed.reservation_system.enums.ReservationStatus;
import com.distributed.reservation_system.exception.BusinessException;
import com.distributed.reservation_system.exception.SystemException;
import com.distributed.reservation_system.exception.ValidationException;
import com.distributed.reservation_system.repository.ReservationRepository;
import com.distributed.reservation_system.repository.TrainTripRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponse;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final TrainTripRepository trainTripRepository;
    private final ReservationRepository reservationRepository;

    @Transactional
    public Reservation bookTicket(Long tripId, List<MasterPassenger> passengerList, User user){
        TrainTrip trainTrip;
        try{
            trainTrip = trainTripRepository.findTripWithLock(tripId)
                    .orElseThrow(()-> new ValidationException("Not a valid trip id. Train trip not found"));
        }catch (PessimisticLockingFailureException ex){
            throw new SystemException("This train is currently in high demand. Please retry.",ex);
        }


        if(trainTrip.getAvailableCapacity()<passengerList.size()){
            throw new BusinessException("Not enough seats available");
        }

        trainTrip.setAvailableCapacity(trainTrip.getAvailableCapacity()-passengerList.size());
        trainTripRepository.save(trainTrip);
        //Todo: payment will be done here first.

        Reservation reservation = new Reservation();
        reservation.setTrainRun(trainTrip);
        reservation.setUserId(user);
        reservation.setStatus(ReservationStatus.CONFIRMED);



        List<ReservationPassenger> reservationPassengers = new ArrayList<>();
        passengerList.forEach(p -> reservationPassengers.add(new ReservationPassenger(reservation, p.getName(),p.getAge())));
        reservation.setPassengers(reservationPassengers);
        reservationRepository.save(reservation);
        return reservation;

        /*

            1.Lock in traintripRepository but effect in this function.: At the start of function we have applied Transactional annotation so connection scope extend till complete bookTicket function is not executed(Which includes repository call where lock is applied and thus lock also gets extended.
            2.why @Transactional annotation was added.: it is required for all service level where database is involved(simple get call to database can be excluded)
            3. What if we do not lock on database and make this entire function synchronized?
            4. can we explicitely remove lock before function ends?

         */
    }

}


