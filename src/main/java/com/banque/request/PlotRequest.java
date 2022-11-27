package com.banque.request;

import jakarta.persistence.Column;
import lombok.Data;

import java.sql.Date;
@Data
public class PlotRequest {
    private String longitude;
    private String latitude;
    private Date lastUpdatedDate;
    private Date nextIrrigationDate;
    private String crop;
}
