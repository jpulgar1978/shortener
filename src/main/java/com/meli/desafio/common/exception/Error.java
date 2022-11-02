package com.meli.desafio.common.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class Error {

    private int status;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss.SSS")
    private LocalDateTime dateTime;
    private String message;

    public Error(int status, String message) {
        this.status = status;
        this.dateTime = LocalDateTime.now();
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
