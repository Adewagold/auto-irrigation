package com.banque.service.response;

import com.banque.model.Plot;
import com.banque.model.PlotDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

import java.util.List;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlotResponse {
    private String status;
    private List<PlotDto> plots;
    private PlotDto plot;
    private String message;

    public PlotResponse(String status, List<PlotDto> plots) {
        this.status = status;
        this.plots = plots;
    }

    public PlotResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public PlotResponse(String status, PlotDto plot) {
        this.status = status;
        this.plot = plot;
    }
}
