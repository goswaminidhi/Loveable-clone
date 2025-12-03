package com.loveableclone.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatSession {
    //One user can have multiple chat sessions
    //but for one project there is only one chat

    Project project;
    User user;

    String title;
    Instant createdAt;
    Instant updatedAt;
    Instant deletedAt;//soft delete
}
