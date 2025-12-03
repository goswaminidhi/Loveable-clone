package com.loveableclone.entity;


import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    Long id;

    String email;
    String passwordHash;
    String name;
    String avtarUrl; //For profile picture

    Instant createdAt;
    Instant updatedAt;
    Instant deletedAt;
}
