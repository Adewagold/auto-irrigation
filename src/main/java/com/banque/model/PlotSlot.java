package com.banque.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;

@Data
@Entity
public class PlotSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "plot_id")
    private long plotId;
    @Column(name = "irrigation_time")
    private Time irrigationTime;
    @Column(name = "water_required")
    private double waterRequired;
    private String status;
}
