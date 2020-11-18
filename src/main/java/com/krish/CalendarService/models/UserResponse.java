package com.krish.CalendarService.models;

import lombok.Data;

@Data
public class UserResponse {
    String userId;
    EventResponse eventResponse;

    public UserResponse(String userId) {
        this.userId = userId;
        this.eventResponse = EventResponse.NEUTRAL;
    }
}
