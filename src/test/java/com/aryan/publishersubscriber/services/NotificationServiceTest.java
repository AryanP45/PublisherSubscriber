package com.aryan.publishersubscriber.services;

import com.aryan.publishersubscriber.models.Subscriber;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class NotificationServiceTest {

    @InjectMocks
    private NotificationService notificationService;

    @Mock
    private Subscriber subscriberMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testSubscribeSuccess() {
        String topicId = "topic1";
        String subscriberId = "subscriber1";
        String result = notificationService.subscribe(topicId, subscriberId);
        assertEquals("subscribed Successfully", result);
    }

    @Test
    public void testSubscribeAlreadySubscribed() {
        String topicId = "topic1";
        String subscriberId = "subscriber1";
        notificationService.subscribe(topicId, subscriberId);
        String result = notificationService.subscribe(topicId, subscriberId);
        assertEquals("Subscriber already subscribed", result);
    }

    @Test
    public void testSubscribeNullTopicId() {
        String subscriberId = "subscriber1";
        String result = notificationService.subscribe(null, subscriberId);
        assertEquals("Error while Subscribing", result);
    }

    @Test
    public void testSubscribeNullSubscriberId() {
        String topicId = "topic1";
        String result = notificationService.subscribe(topicId, null);
        assertEquals("Error while Subscribing", result);
    }

    @Test
    public void testNotifySuccess() {
        String topicId = "topic1";
        String subscriberId = "subscriber1";
        notificationService.subscribe(topicId, subscriberId);

        String result = notificationService.notify(topicId);
        assertEquals("Notified 1 Subscribers", result);
    }

    @Test
    public void testNotifyNoSubscribers() {
        String topicId = "topic1";
        String result = notificationService.notify(topicId);
        assertEquals("Topic Not Found", result);
    }

    @Test
    public void testNotifyNullTopicId() {
        String result = notificationService.notify(null);
        assertEquals("Failed", result);
    }

    @Test
    public void testUnsubscribeSuccess() {
        String topicId = "topic1";
        String subscriberId = "subscriber1";
        notificationService.subscribe(topicId, subscriberId);

        String result = notificationService.unsubscribe(topicId, subscriberId);
        assertEquals("Unsubscribed", result);
    }

    @Test
    public void testUnsubscribeTopicNotFound() {
        String topicId = "topic1";
        String subscriberId = "subscriber1";
        String result = notificationService.unsubscribe(topicId, subscriberId);
        assertEquals("Topic not found", result);
    }

    @Test
    public void testUnsubscribeSubscriberNotFound() {
        String topicId = "topic1";
        String subscriberId = "subscriber1";
        notificationService.subscribe(topicId, "anotherSubscriber");

        String result = notificationService.unsubscribe(topicId, subscriberId);
        assertEquals("Subscriber not found", result);
    }

    @Test
    public void testUnsubscribeNullTopicId() {
        String subscriberId = "subscriber1";
        String result = notificationService.unsubscribe(null, subscriberId);
        assertEquals("Cannot Unsubscribed", result);
    }

    @Test
    public void testUnsubscribeNullSubscriberId() {
        String topicId = "topic1";
        String result = notificationService.unsubscribe(topicId, null);
        assertEquals("Cannot Unsubscribed", result);
    }

}