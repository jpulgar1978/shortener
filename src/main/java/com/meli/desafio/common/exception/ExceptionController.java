package com.meli.desafio.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    private static final Logger log = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler({ExceptionBase.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Error handleExceptionBase(ExceptionBase ex) {
        ex.printStackTrace();
        log.error(ex.getMessage());
        return new Error(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage());
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
    public Error handleException(Exception ex) {
        ex.printStackTrace();
        log.error(ex.getMessage());
        return new Error(HttpStatus.PRECONDITION_FAILED.value(),
                ex.getMessage());
    }
}