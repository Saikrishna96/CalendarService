package com.krish.CalendarService.models;

import lombok.Data;

import java.util.List;

@Data
public class EventDetail {
    EventDuration duration;
    List<UserResponse> userResponses;
    String owner;
    EventType eventType;
    String title;
    String location;

    public EventDetail(EventDuration duration, List<UserResponse> userResponses, String owner, EventType eventType, String title, String location) {
        this.duration = duration;
        this.userResponses = userResponses;
        this.owner = owner;
        this.eventType = eventType;
        this.title = title;
        this.location = location;
    }
}
