package com.banque;

import com.banque.model.Plot;
import com.banque.model.PlotSlot;
import com.banque.repository.PlotRepository;
import com.banque.repository.PlotSlotRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalTime;
import java.util.Calendar;

@Service
@Slf4j
public class DataBootstrap implements CommandLineRunner{

    private final PlotRepository plotRepository;
    private final PlotSlotRepository plotSlotRepository;

    public DataBootstrap(PlotRepository plotRepository,PlotSlotRepository plotSlotRepository) {
        this.plotRepository = plotRepository;
        this.plotSlotRepository = plotSlotRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Saving demo data in the database");
        Plot plot = new Plot();
        plot.setCrop("Watermelon");
        plot.setLatitude("12345678");
        plot.setLongitude("12345679");
        plot.setLastUpdatedDate(new Date(Instant.now().toEpochMilli()));
        plotRepository.save(plot);
        log.info("Saved plot {}",plot);

        Plot plotII = new Plot();
        plotII.setCrop("Grape");
        plotII.setLatitude("23456782");
        plotII.setLongitude("43456793");
        plotII.setLastUpdatedDate(new Date(Instant.now().toEpochMilli()));
        plotRepository.save(plotII);
        log.info("Saved plot {}",plotII);

        log.info("Saving plot config for plot 1");
        var currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        var currentMinute = Calendar.getInstance().get(Calendar.MINUTE);
        PlotSlot plotSlot = new PlotSlot();
        plotSlot.setPlotId(plot.getId());
        plotSlot.setWaterRequired(44.5);
        plotSlot.setIrrigationTime(new Time(currentHour, currentMinute,0));
        plotSlot.setStatus("completed");
        plotSlotRepository.save(plotSlot);

        PlotSlot plotSlotII = new PlotSlot();
        plotSlotII.setPlotId(plotII.getId());
        plotSlotII.setWaterRequired(44.5);
        plotSlotII.setIrrigationTime(new Time(currentHour, currentMinute+3,0));
        plotSlotRepository.save(plotSlotII);
    }
}
