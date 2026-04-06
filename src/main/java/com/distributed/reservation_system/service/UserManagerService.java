package com.distributed.reservation_system.service;

import com.distributed.common.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserManagerService {

    private final UserRepository userRepository;



}
