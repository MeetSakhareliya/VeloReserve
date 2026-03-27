package com.distributed.reservation_system.repository;

import com.distributed.reservation_system.entity.Train;
import com.distributed.reservation_system.entity.TrainTrip;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainTripRepository extends JpaRepository<TrainTrip, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "3000")}) //Request will wait for 3 second before it throws an error.
    @Query("SELECT t FROM TrainTrip t WHERE t.id = :id")
    Optional<TrainTrip> findTripWithLock(@Param("id") Long tripId);
}

