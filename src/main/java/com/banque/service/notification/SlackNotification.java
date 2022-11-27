package com.banque.service.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * This is a basic implementation of the notification interface
 * This is assumed that methods to connect to slack via client is implemented within this class
 */
@Service
@Slf4j
public class SlackNotification implements Notification{
    /**
     * Send Notification to slack
     * @param destination the channel or id of who should get the notification
     * @param title contains the title of the notification or alert
     * @param message is the detailed information about the message that is being sent
     */
    @Override
    public void sendNotification(String destination, String title, String message) {
        log.info("Sending alert via client");
        log.info("Alert details [destination:{}, title:{}, message:{}]",destination,title, message);
    }

}
