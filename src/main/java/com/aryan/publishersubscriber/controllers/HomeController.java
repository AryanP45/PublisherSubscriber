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

	@GetMapping()
	public ResponseEntity<?> landingPage(){
		String Response = """
				<!DOCTYPE html>
				<head>
				    <title>Publisher-Subscriber Notification System</title>
				    <style>
				        body {
				            font-family: Arial, sans-serif;
				            margin: 50px;
				        }
				    </style>
				    <script>
				        setTimeout(function(){
				            window.location.href = 'https://github.com/AryanP45/PublisherSubscriber?tab=readme-ov-file#api-documentation';
				        }, 3000);
				    </script>
				</head>
				<body>
				    <h1>Welcome</h1>
				    <p>This project implements a publisher-subscriber notification system using Java and Spring Boot. It allows subscribers to subscribe to topics, receive notifications for subscribed topics, and unsubscribe from topics.</p>
				    <p> - Aryan Patil </p>
				    <p>You will be redirected to API docs in 3 seconds...</p>
				</body>
				</html>
				""";
		return ResponseEntity.ok(Response);
	}
		
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

	@GetMapping("/notify")
	public ResponseEntity<?> notify(@RequestParam String topicId){
		String result = notificationService.notify(topicId);
		if ("No Subscribers has Subscribed this topic".equals(result) || "Topic Not Found".equals(result)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
		} else if ("Failed".equals(result)) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
		} else {
			return ResponseEntity.ok(result);
		}
	}

	@PostMapping("/unsubscribe")
	public ResponseEntity<?> unsubscribe(@RequestBody SubscriberDto subscriberDto){
		String result = notificationService.unsubscribe(subscriberDto.getTopicId(), subscriberDto.getSubscriberId());
		if ("Topic not found".equals(result) || "Subscriber not found".equals(result)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
		} else if ("Cannot Unsubscribed".equals(result)) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
		} else {
			return ResponseEntity.ok(result);
		}
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAll(){
		return ResponseEntity.ok(notificationService.getAll());
	}

}
