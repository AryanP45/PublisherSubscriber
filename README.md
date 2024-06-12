### Problem Statement 1: Implement Publisher Subscriber Notification System

This project implements a publisher-subscriber notification system using Java and Spring Boot. It allows subscribers to subscribe to topics, receive notifications for subscribed topics, and unsubscribe from topics.

Live Link: https://publishersubscriber.onrender.com  
> ***Note : First request to above URL can take longer time upto 50 sec , as i am using free instance to host it automatically shuts down after inactivity.***

#### Setup

1. Clone the repository:

```bash
git clone https://github.com/AryanP45/PublisherSubscriber.git
```

2. Open the project in your preferred Java IDE. (IntelliJ IDEA)

3. Build the project using Maven.

#### API Documentation

### Postman Collection: [Link to API Collection](https://gold-shuttle-97219.postman.co/workspace/PublisherSubscriber~2caa37b4-fce6-45e9-b63e-d20b1e657db1/collection/30016809-09fc4a40-f107-4953-ae01-149d9b40de1f?action=share&creator=30016809)

The system provides the following APIs:

##### 1. Subscribe

This API allows subscribers to subscribe to a topic.  
**Note**: Creates a new topic if it doesn't exist.

- **Endpoint**: `POST /api/subscribe`  
  Try with live link: https://publishersubscriber.onrender.com/api/subscribe
- **Request Body**:
  ```json
  {
    "topicId": "string",
    "subscriberId": "string"
  }
  ```
- **Responses**:
  - 200 OK: Successfully subscribed.
  - 409 Conflict: Subscriber already subscribed to the topic.
  - 500 Internal Server Error: Error occurred while subscribing.

##### 2. Notify

This API sends notifications to all subscribers of a particular topic.

- **Endpoint**: `GET /api/notify`  
  Try with live link: https://publishersubscriber.onrender.com/api/notify
- **Query Parameters**:
  - `topicId`: The ID of the topic to notify subscribers.
- **Responses**:
  - 200 OK: Successfully notified subscribers.
  - 404 Not Found: No subscribers have subscribed to the topic or topic not found.
  - 500 Internal Server Error: Error occurred while notifying.

##### 3. Unsubscribe

This API allows subscribers to unsubscribe from a topic.

- **Endpoint**: `POST /api/unsubscribe`  
  Try with live link: https://publishersubscriber.onrender.com/api/unsubscribe
- **Request Body**:
  ```json
  {
    "topicId": "string",
    "subscriberId": "string"
  }
  ```
- **Responses**:
  - 200 OK: Successfully unsubscribed.
  - 404 Not Found: Topic or subscriber not found.
  - 500 Internal Server Error: Error occurred while unsubscribing.

#### Driver Function

A driver function has been implemented to demonstrate the flow of the application. Below is a simple example:

```java
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
```
output: 
```java
Subscribe Result: subscribed Successfully
Notification sent to subscriber with ID : subscriber1
Notify Result: Notified 1 Subscribers
Unsubscribe Result: Unsubscribed
```

#### Edge Cases

- Null topic ID or subscriber ID is handled in all APIs.
- API responses provide appropriate error messages for edge cases such as subscriber already subscribed, topic not found, etc.
- Unit tests have been implemented to cover various scenarios, including edge cases.

#### Contributors

- Aryan Patil (@aryanp45)


If any query, contact: aryanitinpatil@gmail.com