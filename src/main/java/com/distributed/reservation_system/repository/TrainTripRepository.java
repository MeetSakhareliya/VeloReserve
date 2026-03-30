package com.distributed.reservation_system.repository;

import com.distributed.reservation_system.entity.Train;
import com.distributed.reservation_system.entity.TrainTrip;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainTripRepository extends JpaRepository<TrainTrip, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "3000")}) //Request will wait for 3 second before it throws an error.
    @Query("SELECT t FROM TrainTrip t WHERE t.id = :id")
    Optional<TrainTrip> findTripWithLock(@Param("id") Long tripId);

//    @Query("SELECT t FROM TrainTrip t JOIN FETCH t.train")
//    @EntityGraph(attributePaths = {"train"})
    @Override
    List<TrainTrip> findAll();
}

