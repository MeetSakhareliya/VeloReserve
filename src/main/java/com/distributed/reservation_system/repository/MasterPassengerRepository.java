package com.distributed.reservation_system.repository;

import com.distributed.reservation_system.entity.MasterPassenger;
import com.distributed.reservation_system.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MasterPassengerRepository extends JpaRepository<MasterPassenger, Long> {
}
