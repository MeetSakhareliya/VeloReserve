package com.distributed.reservation_system.controller;

import com.distributed.common.dto.UserProfileResponseDTO;
import com.distributed.common.entity.User;
import com.distributed.common.exception.ValidationException;
import com.distributed.common.mapper.EntityMapper;
import com.distributed.common.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final EntityMapper entityMapper;

    @GetMapping("/")
    public ResponseEntity<List<UserProfileResponseDTO>> getAllUser(){
        List<UserProfileResponseDTO> users = entityMapper.toUserProfileResponse(userRepository.getAllUserProfileList());
        return ResponseEntity.ok(users);

    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserProfileResponseDTO> getUserProfile(@PathVariable Long userId){
        User user = userRepository.getUserProfile(userId)
                .orElseThrow(()-> new ValidationException("User not found"));
        UserProfileResponseDTO users = entityMapper.toUserProfileResponse(user);
        return ResponseEntity.ok(users);

    }
}
