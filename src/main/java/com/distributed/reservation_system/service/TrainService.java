package com.distributed.reservation_system.service;

import com.distributed.reservation_system.dto.TrainTripResponseDTO;
import com.distributed.reservation_system.entity.Train;
import com.distributed.reservation_system.entity.TrainTrip;
import com.distributed.reservation_system.exception.ValidationException;
import com.distributed.reservation_system.mapper.EntityMapper;
import com.distributed.reservation_system.repository.TrainRepository;
import com.distributed.reservation_system.repository.TrainTripRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TrainService {
    private final TrainRepository trainRepository;
    private final TrainTripRepository trainTripRepository;
    private final EntityMapper entityMapper;

    public List<Train> getAllTrains(){
        return trainRepository.findAll();
    }

    public Train getTrain(Long trainId){
        return trainRepository.findById(trainId)
                .orElseThrow(() -> new ValidationException("Train: " + trainId + " not found"));
    }

    public List<TrainTripResponseDTO> getAllTrainTrips(){
        List<TrainTrip> trainTrips = trainTripRepository.findAll();
        return entityMapper.toTrainTripResponse(trainTrips);
    }

    public TrainTripResponseDTO getTrainTrip(Long trainTripId) {
        TrainTrip trainTrip = trainTripRepository.findById(trainTripId)
                .orElseThrow(() -> new ValidationException("Trip: " + trainTripId + " not found"));
        return entityMapper.toTrainTripResponse(trainTrip);
    }
}
