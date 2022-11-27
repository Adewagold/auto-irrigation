package com.banque.controller.handler;

import com.banque.controller.response.ErrorResponse;
import com.banque.exception.PlotNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    /**
     * Custom error for cases when a plot is not found during query
     * @param plotNotFoundException
     * @return
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PlotNotFoundException.class)
    public ErrorResponse errorResponse(PlotNotFoundException plotNotFoundException){
        return new ErrorResponse(plotNotFoundException.getStatus(),plotNotFoundException.getMessage());
    }
}
