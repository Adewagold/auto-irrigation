package com.banque.controller;

import com.banque.exception.PlotNotFoundException;
import com.banque.request.PlotRequest;
import com.banque.request.PlotSlotRequest;
import com.banque.service.response.PlotResponse;
import com.banque.service.plot.PlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/plot/")
public class PlotController {
    private final PlotService plotService;

    @Autowired
    public PlotController(PlotService plotService) {
        this.plotService = plotService;
    }

    @GetMapping(value = "all",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPlots(){
        return new ResponseEntity<>(plotService.listAllPlots(),OK);
    }

    @PostMapping(value = "create")
    public ResponseEntity<PlotResponse> createNewPlot(@RequestBody PlotRequest plotRequest){
        return new ResponseEntity<>(plotService.savePlot(plotRequest),CREATED);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<PlotResponse> createNewPlot(@PathVariable Long id) throws PlotNotFoundException {
        return new ResponseEntity<>(plotService.getPlotById(id),OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<PlotResponse> updatePlot(@PathVariable Long id, @RequestBody PlotRequest plotRequest) throws PlotNotFoundException {
        return new ResponseEntity<>(plotService.updatePlot(id,plotRequest),OK);
    }

    @PostMapping("create-slot/{plotId}")
    public ResponseEntity<PlotResponse> createSlot(@PathVariable Long plotId, @RequestBody PlotSlotRequest plotSlotRequest) throws PlotNotFoundException {
        return new ResponseEntity<>(plotService.configurePlot(plotId,plotSlotRequest),OK);
    }
}
