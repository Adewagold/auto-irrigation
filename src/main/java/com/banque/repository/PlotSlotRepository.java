package com.banque.repository;

import com.banque.model.PlotSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;

/**
 * PlotSlotRepository to read-write information about the plot.
 */
@Repository
public interface PlotSlotRepository extends JpaRepository<PlotSlot,Long> {
    /**
     * Find all plots bu id
     * @param plotId id of the slot to be queried
     * @return
     */
    List<PlotSlot> findAllByPlotId(Long plotId);

    /**
     * Filter the time range between a time range
     * @param startTime for that current job time in hour, minutes;
     * @param endTime for the current end time in hour, minutes;
     * @return
     */
    List<PlotSlot> findAllByIrrigationTimeBetween(Time startTime, Time endTime);
}
