package com.banque.service.response;

/**
 * Notification interface to handle systems notifications and alerts
 */
public interface Notification {
    void sendNotification(String destination, String title, String message);
}
