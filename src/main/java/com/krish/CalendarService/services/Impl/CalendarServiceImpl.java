package com.krish.CalendarService.services.Impl;

import com.krish.CalendarService.helperUtils.CalendarUtils;
import com.krish.CalendarService.models.*;
import com.krish.CalendarService.repositories.CalendarRepository;
import com.krish.CalendarService.services.CalendarService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CalendarServiceImpl implements CalendarService {

    private final CalendarRepository calendarRepository;

    public CalendarServiceImpl(CalendarRepository repository) {
        this.calendarRepository = repository;
    }

    @Override
    public String createEvent(LocalDateTime startTime, LocalDateTime endTime,
                              String locationId, String owner, List<String> userIds, String title, EventType eventType) {
        EventDuration eventDuration = new EventDuration(startTime, endTime);
        List<UserResponse> userResponses = new ArrayList<>();
        userIds.forEach(userId -> {
            userResponses.add(new UserResponse(userId));
        });
        EventDetail eventDetail =
                new EventDetail(eventDuration, userResponses, owner, eventType, title, locationId);

        // TODO check all the users if there is any clash with locationId
        List<EventDuration> locationAvailability = calendarRepository.getLocationAvailabilty(locationId);
        Optional<EventDuration> foundDuration = locationAvailability
                .stream()
                .filter(duration -> CalendarUtils.hasDurationOverlap(duration, eventDuration))
                .findFirst();
        if (foundDuration.isPresent()) {
            System.out.println("overlap found with the given location id");
            return null;
        }
        return calendarRepository.saveEvent(eventDetail);
    }

    @Override
    public List<EventDetail> fetchEventsInDurationRange(String userId, LocalDateTime start, LocalDateTime end) {
        List<BasicEventInfo> userEvents = calendarRepository.getUserEvents(userId);
        List<EventDetail> allUserEvents = new ArrayList<>();
        userEvents.forEach(event -> {
            if (CalendarUtils.isBetween(event.getDuration(), start, end))
                allUserEvents.add(calendarRepository.getEventDetail(event.getEventId()));
        });
        return allUserEvents;
    }

    @Override
    public List<UserResponse> fetchUserResponseForEvent(String eventId) {
        return calendarRepository.getUserResponseByEventId(eventId);
    }
}
