package com.distributed.reservation_system.exception;

import com.distributed.reservation_system.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .error("Business Exception")
                .message(ex.getMessage())
                .time(Timestamp.valueOf(LocalDateTime.now())).build();
        return new ResponseEntity<>(errorResponse,HttpStatusCode.valueOf(409)); //todo: status code need to revisit;

    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException ex){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .error("Validation Exception")
                .message(ex.getMessage())
                .time(Timestamp.valueOf(LocalDateTime.now())).build();
        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(400));
    }

    @ExceptionHandler(SystemException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(SystemException ex){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .error("System Exception")
                .message(ex.getMessage())
                .time(Timestamp.valueOf(LocalDateTime.now())).build();
        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(500));
    }
}
