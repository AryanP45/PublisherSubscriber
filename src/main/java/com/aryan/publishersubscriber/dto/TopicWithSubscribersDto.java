package com.aryan.publishersubscriber.dto;

import com.aryan.publishersubscriber.models.Subscriber;
import lombok.Data;

import java.util.Set;

@Data
public class TopicWithSubscribersDto {
    String topicId;
    Set<Subscriber> subscribers;
}
