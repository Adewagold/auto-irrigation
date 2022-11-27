package com.banque.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Date;
import java.util.List;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlotDto {
    private long id;
    private String longitude;
    private String latitude;
    private Date lastUpdatedDate;
    private Date nextIrrigationDate;
    private String crop;
    private List<PlotSlot> slots;
}
