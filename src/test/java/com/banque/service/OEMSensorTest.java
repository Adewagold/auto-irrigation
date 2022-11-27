package com.banque.service;

import com.banque.repository.PlotSlotRepository;
import com.banque.service.sensor.Sensor;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Time;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class OEMSensorTest {
    @Mock
    Sensor sensor;
    @Autowired
    PlotSlotRepository plotSlotRepository;

    @Test
    void startIrrigation() {
        var currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        var currentMinute = Calendar.getInstance().get(Calendar.MINUTE);
        var currentQueryStartTime = new Time(currentHour,currentMinute,0);
        var currentQueryEndTime = new Time(currentHour,currentMinute,59);
        var plotsByTime = plotSlotRepository.findAllByIrrigationTimeBetween(currentQueryStartTime,currentQueryEndTime);
        assertTrue(plotsByTime.size()>0);
        Mockito.when(sensor.online()).thenReturn(true);
        Mockito.when(sensor.startIrrigation(plotsByTime.get(0))).thenReturn(true);
        var result  = sensor.startIrrigation(plotsByTime.get(0));
        assertTrue(result);
    }

    @Test
    void startIrrigationWhenDeviceIsOffline() {
        var currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        var currentMinute = Calendar.getInstance().get(Calendar.MINUTE);
        var currentQueryStartTime = new Time(currentHour,currentMinute,0);
        var currentQueryEndTime = new Time(currentHour,currentMinute,59);
        var plotsByTime = plotSlotRepository.findAllByIrrigationTimeBetween(currentQueryStartTime,currentQueryEndTime);
        assertTrue(plotsByTime.size()>0);
        Mockito.when(sensor.online()).thenReturn(false);
        var result  = sensor.startIrrigation(plotsByTime.get(0));
        verify(sensor,times(1)).startIrrigation(plotsByTime.get(0));
    }
}