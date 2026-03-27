package com.distributed.reservation_system.service;

import com.distributed.reservation_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserManagerService {

    private final UserRepository userRepository;

    public void adduser(){
//        userRepository.findByN
    }

}
