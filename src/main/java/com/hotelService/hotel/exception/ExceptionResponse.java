package com.hotelService.hotel.exception;


import org.springframework.http.HttpStatus;

import java.util.Date;

public class ExceptionResponse {
    private Date timeStamp;
    private String message;
    private String details;
    private HttpStatus httpStatus;

    public ExceptionResponse() {
    }

    public ExceptionResponse(Date timeStamp, String message, String details, HttpStatus httpStatus) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.details = details;
        this.httpStatus = httpStatus;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}