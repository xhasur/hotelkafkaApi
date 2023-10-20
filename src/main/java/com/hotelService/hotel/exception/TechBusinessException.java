package com.hotelService.hotel.exception;


import org.springframework.http.HttpStatus;

public class TechBusinessException extends RuntimeException {

    private HttpStatus status;

    public TechBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public TechBusinessException(String message) {
        super(message);
    }

    public TechBusinessException(String message, HttpStatus httpStatus) {
        super(message);
        this.status = httpStatus;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
