package com.aryan.publishersubscriber;

import com.aryan.publishersubscriber.services.NotificationService;

public class Driver {
    public static void main(String[] args) {
        NotificationService notificationService = new NotificationService();

        // Subscribe
        String subscribeResult = notificationService.subscribe("topic1", "subscriber1");
        System.out.println("Subscribe Result: " + subscribeResult);

        // Notify
        String notifyResult = notificationService.notify("topic1");
        System.out.println("Notify Result: " + notifyResult);

        // Unsubscribe
        String unsubscribeResult = notificationService.unsubscribe("topic1", "subscriber1");
        System.out.println("Unsubscribe Result: " + unsubscribeResult);
    }
}
