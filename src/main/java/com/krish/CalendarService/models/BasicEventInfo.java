package com.krish.CalendarService.models;

import lombok.Data;

@Data
public class BasicEventInfo {
    EventDuration duration;
    String eventId;

    public BasicEventInfo(EventDuration duration, String eventId) {
        this.duration = duration;
        this.eventId = eventId;
    }
}
