package com.banque.service;

import com.banque.exception.PlotNotFoundException;
import com.banque.model.Plot;
import com.banque.model.PlotDto;
import com.banque.model.PlotSlot;
import com.banque.repository.PlotRepository;
import com.banque.repository.PlotSlotRepository;
import com.banque.request.PlotSlotRequest;
import com.banque.request.PlotRequest;
import com.banque.service.response.PlotResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultPlotService implements PlotService{
    private final PlotRepository plotRepository;
    private final PlotSlotRepository plotSlotRepository;

    @Override
    public PlotResponse savePlot(PlotRequest plotRequest) {
        log.info("Adding a new plot");
        Plot plot = new Plot();
        plot.setLastUpdatedDate(plotRequest.getLastUpdatedDate());
        plot.setLatitude(plotRequest.getLatitude());
        plot.setLongitude(plotRequest.getLongitude());
        plot.setCrop(plotRequest.getCrop());
        return new PlotResponse("success",getPlotAndSlots(plotRepository.save(plot),null));
    }

    @Override
    public PlotResponse updatePlot(Long id, PlotRequest plotRequest) throws PlotNotFoundException {
        Plot plot = findPlotById(id);
        if(plotRequest.getLatitude()!=null || !plotRequest.getLatitude().isEmpty())plot.setLatitude(plotRequest.getLatitude());
        if(plotRequest.getLongitude()!=null || !plotRequest.getLongitude().isEmpty())plot.setLongitude(plotRequest.getLongitude());
        if(plotRequest.getCrop()!=null || !plotRequest.getCrop().isEmpty())plot.setCrop(plotRequest.getCrop());
        return new PlotResponse("success", getPlotAndSlots(plot,null));
    }

    @Override
    public PlotResponse getPlotById(Long id) throws PlotNotFoundException {
        log.info("Retrieving plot by id:{}", id);
        Plot plot = findPlotById(id);
        List<PlotSlot> slots = plotSlotRepository.findAllByPlotId(plot.getId());
        PlotDto plotDto = getPlotAndSlots(plot,slots);
        return new PlotResponse("success", plotDto);
    }

    private PlotDto getPlotAndSlots(Plot plot, List<PlotSlot> slots) {
        PlotDto plotDto = new PlotDto();
        if (slots!=null){ plotDto.setSlots(slots);}
        plotDto.setCrop(plot.getCrop());
        plotDto.setLatitude(plot.getLatitude());
        plotDto.setLongitude(plot.getLongitude());
        plotDto.setId(plot.getId());
        plotDto.setNextIrrigationDate(plot.getNextIrrigationDate());
        plotDto.setLastUpdatedDate(plot.getLastUpdatedDate());
        return plotDto;
    }

    @Override
    public PlotResponse listAllPlots() {
        log.info("Retrieving all plots");
        List<PlotDto> plots = plotRepository.findAll().stream().map(plot -> {
            List<PlotSlot> slots = plotSlotRepository.findAllByPlotId(plot.getId());
            return getPlotAndSlots(plot,slots);
        }).collect(Collectors.toList());
        return new PlotResponse("success", plots);
    }

    @Override
    public PlotResponse configurePlot(Long plotId, PlotSlotRequest plotSlotRequest) throws PlotNotFoundException {
        findPlotById(plotId);
        PlotSlot plotSlot = new PlotSlot();
        plotSlot.setPlotId(plotId);
        plotSlot.setWaterRequired(plotSlotRequest.getWaterRequired());
        plotSlot.setIrrigationTime(Time.valueOf(plotSlotRequest.getIrrigationTime()));
        plotSlotRepository.save(plotSlot);
        return new PlotResponse("success",String.format("Time slot saved for plot %s at time %s",plotId,plotSlotRequest.getIrrigationTime().toString()));
    }

    private Plot findPlotById(Long id) throws PlotNotFoundException {
        Optional<Plot> plot = plotRepository.findById(id);
        if(plot.isEmpty()){
            throw new PlotNotFoundException("not-found",String.format("Plot with id %s not found",id));
        }
        return plot.get();
    }
}
