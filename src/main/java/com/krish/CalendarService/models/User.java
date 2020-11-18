package com.krish.CalendarService.models;

import lombok.Data;

@Data
public class User {
    String userId;
    String name;
    String email;

    User(String userId, String name, String email) {
        this.email = email;
        this.userId = userId;
        this.name = name;
    }
}
