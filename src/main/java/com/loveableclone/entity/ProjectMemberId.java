package com.loveableclone.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectMemberId {
    //This is for creating the foreign id for the project table
    Long projectId;
    Long userId;
}
