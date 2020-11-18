package com.krish.CalendarService.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventDuration {
    LocalDateTime startTime;
    LocalDateTime endTime;

    EventDuration(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

}
