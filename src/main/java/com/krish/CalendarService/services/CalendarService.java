package com.krish.CalendarService.services;

import com.krish.CalendarService.models.EventDetail;
import com.krish.CalendarService.models.EventType;
import com.krish.CalendarService.models.UserResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface CalendarService {
    String createEvent(LocalDateTime startTime, LocalDateTime endTime, String location,
                       String owner, List<String> userIds, String title, EventType eventType);

    List<EventDetail> fetchEventsInDurationRange(String userId, LocalDateTime start, LocalDateTime end);

    List<UserResponse> fetchUserResponseForEvent(String eventId);

}
