package com.banque.service;

import com.banque.model.PlotSlot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OEMSensor implements Sensor{
    private static final boolean SENSOR_STATUS = false;
    private static final int MAX_RETRY_TIMES = 3;

    @Override
    public boolean online() {
        return SENSOR_STATUS;
    }

    @Override
    @Retryable(value = RuntimeException.class, maxAttempts = MAX_RETRY_TIMES, backoff = @Backoff(delay = 1))
    public boolean startIrrigation(PlotSlot plotSlot) {
        if(!this.online()){
            throw new RuntimeException("Device is offline");
        }
        log.info("Activating automatic irrigation system for plot {} with {} amount of water", plotSlot.getPlotId(), plotSlot.getWaterRequired());
        return true;
    }

    @Recover
    @Override
    public boolean recovery(RuntimeException e, PlotSlot plotSlot){
        log.info("Sensor called for {} times. Sending alert to support team.",MAX_RETRY_TIMES);
        return false;
    }
}
