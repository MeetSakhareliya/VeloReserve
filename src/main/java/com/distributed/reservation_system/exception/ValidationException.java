package com.distributed.reservation_system.exception;

public class ValidationException extends RuntimeException{
    public ValidationException(String message){
        super(message);
    }

    public ValidationException(String message, Throwable cause){
        super(message,cause);
    }
}
