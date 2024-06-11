package com.aryan.publishersubscriber.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.aryan.publishersubscriber.models.Subscriber;

@Service
@Slf4j
public class NotificationService {

	private final Map<String, Set<Subscriber>> subscriptions = new HashMap<>();

	public String subscribe(String topicId, String subscriberId) {
		try {

			if (topicId == null || subscriberId == null) {
				throw new IllegalArgumentException("Topic ID or Subscriber ID is null");
			}

			Set<Subscriber> subscribers = subscriptions.computeIfAbsent(topicId, k -> new HashSet<>());
			Subscriber newSubscriber = new Subscriber(subscriberId);
			if (!subscribers.add(newSubscriber)) {
				log.info("Subscriber already subscribed");
				return "Subscriber already subscribed";
			}
			return "subscribed Successfully";
		} catch (Exception e) {
			return "Error while Subscribing";
		}
	}
}
