package com.aryan.publishersubscriber.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Subscriber {
	private String id;
	
	public void sendNotification() {
		System.out.println("Notification sent to subscriber with ID : "+id);
	}

}
