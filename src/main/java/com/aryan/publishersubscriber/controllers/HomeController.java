package com.aryan.publishersubscriber.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aryan.publishersubscriber.dto.SubscriberDto;
import com.aryan.publishersubscriber.services.NotificationService;

@RestController
@RequestMapping("/api")
public class HomeController {
	
	@Autowired
	NotificationService notificationService;
		
	@PostMapping("/subscribe")
	public ResponseEntity<?> subscribe(@RequestBody SubscriberDto subscriberDto){
		String result = notificationService.subscribe(subscriberDto.getTopicId(), subscriberDto.getSubscriberId());
		if ("Subscriber already subscribed".equals(result)) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
		} else if ("Error while Subscribing".equals(result)) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
		} else {
			return ResponseEntity.ok(result);
		}
	}
}
