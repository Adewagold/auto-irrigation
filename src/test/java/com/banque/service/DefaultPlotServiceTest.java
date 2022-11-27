package com.banque.service;

import com.banque.exception.PlotNotFoundException;
import com.banque.model.Plot;
import com.banque.model.PlotSlot;
import com.banque.repository.PlotRepository;
import com.banque.repository.PlotSlotRepository;
import com.banque.request.PlotRequest;
import com.banque.request.PlotSlotRequest;
import com.banque.service.plot.PlotService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalTime;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Because the application use H2 db by default, there's no need to use any test container as our database
 */
@SpringBootTest
class DefaultPlotServiceTest {
    @Autowired
    private PlotRepository plotRepository;
    @Autowired
    private PlotSlotRepository plotSlotRepository;

    @Autowired
    private PlotService plotService;

    @Test
    void savePlot() {
        var plot = setPlotDetails("Banana");
        var record = plotService.savePlot(plot);
        assertNotNull(record);
        assertTrue(record.getPlot().getId() > 0);
    }

    @Test
    void updatePlot() throws PlotNotFoundException {
        var plotRequest = setPlotDetails("Banana");
        var savePlot = plotService.savePlot(plotRequest);
        var plotId = savePlot.getPlot().getId();
        var plot = plotRepository.findById(plotId);
        assertTrue(plot.isPresent());
        assertEquals(plot.get().getCrop(), "Banana");
        plotRequest.setCrop("Mango");
        plotRequest.setLatitude("40333");
        plotRequest.setLongitude("50333");
        var record = plotService.updatePlot(plotId, plotRequest);
        assertNotNull(record);
        assertEquals(record.getPlot().getCrop(), "Mango");
        assertEquals(record.getPlot().getLatitude(), "40333");
        assertEquals(record.getPlot().getLongitude(), "50333");
        assertTrue(record.getPlot().getId() > 0);
    }

    @Test
    void getPlotById() throws PlotNotFoundException {
        var response = plotService.getPlotById(2L);
        assertNotNull(response);
        assertEquals("success",response.getStatus());

    }

    @Test
    void listAllPlots() {
        var response = plotService.listAllPlots();
        assertTrue(response.getPlots().size()>0);
        var plot = response.getPlots().get(0);
        assertTrue(plot.getSlots().size()>0);
    }

    @Test
    void configurePlot() throws PlotNotFoundException {
        PlotSlotRequest plotSlotRequest = new PlotSlotRequest();
        plotSlotRequest.setPlotId(2);
        plotSlotRequest.setWaterRequired(44.5);
        plotSlotRequest.setIrrigationTime(LocalTime.now());
        var response = plotService.configurePlot(2L, plotSlotRequest);
        assertNotNull(response);
    }

    private PlotRequest setPlotDetails(String crop){
        PlotRequest plot = new PlotRequest();
        plot.setCrop(crop);
        plot.setLatitude("12345958");
        plot.setLongitude("22345958");
        plot.setLastUpdatedDate(new Date(Instant.now().toEpochMilli()));
        return plot;
    }

}