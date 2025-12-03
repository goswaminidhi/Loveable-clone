package com.loveableclone.entity;

import com.loveableclone.enums.ProjectRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectMember {
    /*
    It is created for the purpose of the two tables i.e. User table and Project table.
    This kind of table is called mapping table or join table
    There are two parameters which combines to form the primary key.
     */

    ProjectMemberId id;

    Project project;

    User user;

    ProjectRole projectRole;//This is for when we are inviting user then at which role?editor or viewer.

    Instant invitedAt;
    Instant acceptedAt;


}
