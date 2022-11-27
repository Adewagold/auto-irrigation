package com.banque.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Data
public class PlotSlotRequest {
    private long plotId;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime irrigationTime;
    private double waterRequired;
    private String status;
}
