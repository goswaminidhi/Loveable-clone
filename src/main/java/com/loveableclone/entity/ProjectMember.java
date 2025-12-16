package com.loveableclone.entity;

import com.loveableclone.enums.ProjectRole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "project_members")
public class ProjectMember {
    /*
    It is created for the purpose of the two tables i.e. User table and Project table.
    This kind of table is called mapping table or join table
    There are two parameters which combines to form the primary key.
     */

    @EmbeddedId //Composite primary key = more than one column together forms the primary key
    ProjectMemberId id;

    @ManyToOne
    @MapsId("projectId")
    Project project;

    @ManyToOne
    @MapsId("userId")
    User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    ProjectRole projectRole;//This is for when we are inviting user then at which projectRole?editor or viewer.

    Instant invitedAt;
    Instant acceptedAt;


}
