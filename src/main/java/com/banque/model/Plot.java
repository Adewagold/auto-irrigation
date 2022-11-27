package com.banque.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;

/**
 * Plot of land
 */
@Entity
@Data
public class Plot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "longitude")
    private String longitude;
    @Column(name = "latitude")
    private String latitude;
    @Column(name = "lastUpdatedDate")
    private Date lastUpdatedDate;
    @Column(name = "nextIrrigationDate")
    private Date nextIrrigationDate;
    private String crop;
}
