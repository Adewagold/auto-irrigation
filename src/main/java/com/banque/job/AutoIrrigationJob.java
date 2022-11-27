package com.banque.job;

import com.banque.model.PlotSlot;
import com.banque.repository.PlotRepository;
import com.banque.repository.PlotSlotRepository;
import com.banque.service.sensor.Sensor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;
import java.time.Instant;
import java.util.Calendar;

/**
 * Auto Irrigation Job
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class AutoIrrigationJob {
    private final Sensor sensor;
    private final PlotRepository plotRepository;
    private final PlotSlotRepository plotSlotRepository;

    /**
     * A job that runs every minute and query the slot records for intervals within that minute range
     */
    @Scheduled(cron = "0 * * * * *")
    public void triggerIrrigation(){
        log.info("Searching for plot records to trigger auto irrigation");
        var currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        var currentMinute = Calendar.getInstance().get(Calendar.MINUTE);
        var currentQueryStartTime = new Time(currentHour,currentMinute,0);
        var currentQueryEndTime = new Time(currentHour,currentMinute,59);
        var plotsByTime = plotSlotRepository.findAllByIrrigationTimeBetween(currentQueryStartTime,currentQueryEndTime);
        for (PlotSlot slot: plotsByTime) {
            if(sensor.startIrrigation(slot)){
                slot.setStatus("completed");
                plotSlotRepository.save(slot);
                var plot = plotRepository.findById(slot.getPlotId());
                plot.ifPresent(value -> value.setLastUpdatedDate(new Date(Instant.now().toEpochMilli())));
            }
        }
    }

}
