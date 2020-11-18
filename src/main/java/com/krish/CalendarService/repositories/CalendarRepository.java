package com.krish.CalendarService.repositories;

import com.krish.CalendarService.models.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CalendarRepository {
    // TODO Can create different classes for each map under separation of concern
    private final Map<String, EventDetail> eventsMap;
    private final Map<String, List<BasicEventInfo>> userEventMap;
    private final Map<String, List<EventDuration>> locationAvailabilty;

    public CalendarRepository() {
        this.eventsMap = new HashMap<>();
        this.userEventMap = new HashMap<>();
        this.locationAvailabilty = new HashMap<>();
    }

    public String saveEvent(EventDetail eventDetail) {
        String eventId = UUID.randomUUID().toString();
        System.out.println("eventId : " + eventId);
        eventsMap.put(eventId, eventDetail);
        List<EventDuration> locationDurationList = locationAvailabilty.getOrDefault(eventDetail.getLocation(), null);
        if (locationAvailabilty.containsKey(eventDetail.getLocation())) {
            locationAvailabilty.get(eventDetail.getLocation()).add(eventDetail.getDuration());
        } else {
            List<EventDuration> list = new ArrayList<>();
            list.add(eventDetail.getDuration());
            locationAvailabilty.put(eventDetail.getLocation(), list);
        }
        List<UserResponse> userResponseList = eventDetail.getUserResponses();
        userResponseList.forEach(userResponse -> {
            String userId = userResponse.getUserId();
            BasicEventInfo eventInfo = new BasicEventInfo(eventDetail.getDuration(), eventId);
            if (userEventMap.containsKey(userId)) {
                userEventMap.get(userId).add(eventInfo);
            } else {
                List<BasicEventInfo> list = new ArrayList<>();
                list.add(eventInfo);
                userEventMap.put(userId, list);
            }
        });
        return eventId;
    }

    public List<BasicEventInfo> getUserEvents(String userId) {
        return userEventMap.getOrDefault(userId, null);
    }

    public EventDetail getEventDetail(String eventId){
        return eventsMap.get(eventId);
    }

    public List<UserResponse> getUserResponseByEventId(String eventId){
        EventDetail eventDetail = eventsMap.getOrDefault(eventId, null);
        if (eventDetail != null)
            return eventDetail.getUserResponses();
        return null;
    }

    public List<EventDuration> getLocationAvailabilty(String locationId) {
        return locationAvailabilty.getOrDefault(locationId, null);
    }


}
