package com.distributed.reservation_system.controller;

import com.distributed.common.dto.TrainTripResponseDTO;
import com.distributed.common.entity.Train;
import com.distributed.reservation_system.service.TrainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/train")
@RequiredArgsConstructor
public class TrainController {
    private final TrainService trainService;

    @GetMapping("/")
    public ResponseEntity<List<Train>> getTrains(){
        List<Train> trains = trainService.getAllTrains();
        return new ResponseEntity<>(trains, HttpStatus.OK);
    }

    @GetMapping("/{trainId}")
    public ResponseEntity<Train> getTrains(@PathVariable Long trainId){
        Train train = trainService.getTrain(trainId);
        return new ResponseEntity<>(train, HttpStatus.OK);
    }

    @GetMapping("/trips")
    public ResponseEntity<List<TrainTripResponseDTO>> getTrainTrips(){
        List<TrainTripResponseDTO> trainTrips = trainService.getAllTrainTrips();
        return new ResponseEntity<>(trainTrips, HttpStatus.OK);
    }

    @GetMapping("/trip/{tripId}")
    public ResponseEntity<TrainTripResponseDTO> getTrainTrip(@PathVariable Long tripId){
        TrainTripResponseDTO trainTrip = trainService.getTrainTrip(tripId);
        return new ResponseEntity<>(trainTrip, HttpStatus.OK);
    }
}
