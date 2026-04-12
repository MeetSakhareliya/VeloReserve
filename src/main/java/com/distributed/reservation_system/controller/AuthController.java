package com.distributed.reservation_system.controller;

import com.distributed.common.exception.AuthException;
import com.distributed.reservation_system.dto.LoginRequest;
import com.distributed.reservation_system.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.SecurityContext;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody  LoginRequest loginRequest){
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getUserId(),
                    loginRequest.getPassword()
            ));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = JwtUtil.createJWTToken(authentication);
            return ResponseEntity.ok(jwt);

        }catch (BadCredentialsException ex){
            throw new AuthException("The username or password you entered is incorrect.", ex);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new AuthException("Error while authentication",e);
        }
    }
}
