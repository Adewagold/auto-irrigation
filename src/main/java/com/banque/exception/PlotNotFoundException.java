package com.banque.exception;

import lombok.Data;

@Data
public class PlotNotFoundException extends Throwable {
    private String status;
    public PlotNotFoundException(String status,String message) {
        super(message);
        this.status = status;
    }
}
