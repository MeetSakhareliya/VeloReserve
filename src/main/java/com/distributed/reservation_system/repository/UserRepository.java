package com.distributed.reservation_system.repository;

import com.distributed.reservation_system.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = {"masterPassengerList"})
    @Query("SELECT u FROM User u")
    List<User> getAllUserProfileList();

    @EntityGraph(attributePaths = {"masterPassengerList"})
    @Query("SELECT u FROM User u where u.userId = :userId")
    Optional<User> getUserProfile(Long userId);
}
