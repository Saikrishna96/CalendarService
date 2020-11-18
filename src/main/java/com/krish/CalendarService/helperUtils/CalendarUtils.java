package com.krish.CalendarService.helperUtils;

import com.krish.CalendarService.models.EventDuration;

import java.time.LocalDateTime;

public class CalendarUtils {
    public static boolean hasDurationOverlap(EventDuration d1, EventDuration d2) {
        return !(d1.getEndTime().isBefore(d2.getStartTime()) || d1.getStartTime().isAfter(d2.getEndTime()));
    }

    public static boolean isBetween(EventDuration eventDuration, LocalDateTime start, LocalDateTime end){
        return eventDuration.getStartTime().isAfter(start) && eventDuration.getEndTime().isBefore(end);
    }
}
