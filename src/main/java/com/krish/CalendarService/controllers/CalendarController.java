package com.krish.CalendarService.controllers;

import com.krish.CalendarService.models.EventDetail;
import com.krish.CalendarService.models.EventType;
import com.krish.CalendarService.models.UserResponse;
import com.krish.CalendarService.services.CalendarService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("calendar")
public class CalendarController {

    private final CalendarService calendarService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @PostMapping("create")
    public String createEvent(@RequestParam("start") String start, @RequestParam("end") String end,
                              String locationId, String owner, List<String> userIds, String title, EventType eventType) {
        LocalDateTime startTime = LocalDateTime.parse(start, formatter);
        LocalDateTime endTime = LocalDateTime.parse(end, formatter);
        return calendarService.createEvent(startTime, endTime, locationId, owner, userIds, title, eventType);
    }

    @GetMapping("fetch-user-events")
    public List<EventDetail> fetchEventsByUserId(@RequestParam("userId") String userId, @RequestParam("start") String start, @RequestParam("end") String end) {
        LocalDateTime startTime = LocalDateTime.parse(start, formatter);
        LocalDateTime endTime = LocalDateTime.parse(end, formatter);
        return calendarService.fetchEventsInDurationRange(userId, startTime, endTime);
    }

    @GetMapping("fetch-user-responses")
    public List<UserResponse> fetchUserResponsesByEventId(@RequestParam("eventId") String eventId) {
        return calendarService.fetchUserResponseForEvent(eventId);
    }

    // TODO need an api to update the user response once user accepts / declines the event
}
