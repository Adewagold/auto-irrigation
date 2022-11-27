package com.banque.service;

import com.banque.exception.PlotNotFoundException;
import com.banque.request.PlotSlotRequest;
import com.banque.request.PlotRequest;
import com.banque.service.response.PlotResponse;

/**
 * Plot Service
 */
public interface PlotService {
    /**
     * Save a new plot
     * @param plotRequest containing http request of plot information
     * @return Plot response
     */
    PlotResponse savePlot(PlotRequest plotRequest);

    /**
     * Update existing plot information
     * @param plotId plot of id to be updated after validating that it exists
     * @param plotRequest plot update requests
     * @return a plot response
     * @throws PlotNotFoundException if no associated plot to the provided plot id is found.
     */
    PlotResponse updatePlot(Long plotId,PlotRequest plotRequest) throws PlotNotFoundException;

    /**
     * Get a single plot information by Id
     * @param id id of the port to be seared
     * @return a plot response
     * @throws PlotNotFoundException if no associated plot to the provided plot id is found.
     */
    PlotResponse getPlotById(Long id) throws PlotNotFoundException;

    /**
     * Get a list of all available plots and their configured details
     * @return a list of plots and plot slots
     */
    PlotResponse listAllPlots();

    /**
     * Configre a time slot for an existing plot for it to be irrigated automatically
     * @param plotId
     * @param plotConfigRequest contains the plot slot information
     * @return a plot response
     * @throws PlotNotFoundException if no associated plot to the provided plot id is found.
     */
    PlotResponse configurePlot(Long plotId, PlotSlotRequest plotConfigRequest) throws PlotNotFoundException;
}
