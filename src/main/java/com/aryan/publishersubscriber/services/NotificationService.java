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

	public String notify(String topicId) {
		try {

			if (topicId == null) {
				throw new IllegalArgumentException("Topic ID is null");
			}

			Set<Subscriber> subscribers = subscriptions.get(topicId);
			int count=0;
			if (subscribers != null) {
				if(subscribers.isEmpty())
				{
					log.info("No Subscribers has Subscribed this topic");
					return "No Subscribers has Subscribed this topic";
				}

				for (Subscriber subscriber : subscribers) {
					// Implement specific notification logic based on your requirements (e.g., email, SMS, etc.)
					subscriber.sendNotification();
					count++;
				}
			}else{
				log.info("Topic ID not found");
				return "Topic Not Found";
			}
			return "Notified "+count+" Subscribers";
		}catch (Exception e){
			// catch error
			return "Failed";
		}
	}
	
	public String unsubscribe(String topicId, String subscriberId) {
		try {

			if (topicId == null || subscriberId == null) {
				throw new IllegalArgumentException("Topic ID or Subscriber ID is null");
			}

			Set<Subscriber> subscribers = subscriptions.get(topicId);
			if (subscribers == null) {
				log.info("Topic not found");
				return "Topic not found";
			}

			Subscriber subscriber = new Subscriber(subscriberId);
			if (!subscribers.remove(subscriber)) {
				log.info("Subscriber not found");
				return "Subscriber not found";
			}

			return "Unsubscribed";
		} catch (Exception e) {
			return "Cannot Unsubscribed";
		}
	}

}
