package com.distributed.reservation_system.repository;

import com.distributed.reservation_system.entity.MasterPassenger;
import com.distributed.reservation_system.entity.Reservation;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MasterPassengerRepository extends JpaRepository<MasterPassenger, Long> {
    @Query("SELECT mp FROM MasterPassenger mp WHERE mp.id IN :ids AND mp.userId.userId = :userId")
    List<MasterPassenger> findByIdsAndUserId(
            @Param("ids") List<Long> ids,
            @Param("userId") Long userId
    );
}
