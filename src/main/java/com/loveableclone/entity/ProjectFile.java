package com.loveableclone.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectFile{
    Long id;

    Project project;

    String path;

    String minioObjectKeY;//This is the storage where we will store large data

    Instant createdAt;
    Instant updatedAt;

    User createdBy;
    User UpdatedBy;//These two are for the auditing purpose in the file only

}
