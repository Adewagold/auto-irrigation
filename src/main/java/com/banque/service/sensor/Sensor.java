package com.banque.service;

import com.banque.model.PlotSlot;

/**
 * Sensor interface
 */
public interface Sensor {
    /**
     * Device power status
     * @return true if the device is online or false if it's offline
     */
    boolean online();

    /**
     * Triggers the irrigation of a specific plot due to the slot configuration
     * @param plotSlot is the time set to irrigate the plot of land
     * @return true if operation is successful or throw a RuntimeException if device is offline.
     */
    boolean startIrrigation(PlotSlot plotSlot);

    /**
     * After a device is triggered for a number of configured times without a success, this method is triggered to alert support
     * @param e excetion thrown
     * @param plotSlot plot that was attempted to be watered.
     */
    boolean recovery(RuntimeException e, PlotSlot plotSlot);
}
