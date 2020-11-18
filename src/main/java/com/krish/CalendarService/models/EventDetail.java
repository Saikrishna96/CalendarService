package com.krish.CalendarService.models;

import lombok.Data;

import java.util.List;

@Data
public class EventDetail {
    EventDuration duration;
    List<User> users;
    String owner;
    EventType eventType;
    String title;
    String location;
}
