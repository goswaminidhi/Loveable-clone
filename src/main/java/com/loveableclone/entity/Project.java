package com.loveableclone.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "projects",
        indexes = {
                @Index(name = "idx_projects_updated_at_desc", columnList = "updated_at DESC,deleted_at"), //Seaching
                // updated at first than deleted at will be searched
                @Index(name = "idx_projects_deleted_at_updated_at_desc", columnList = "deleted_at,updated_at DESC"),
                @Index(name = "idx_projects_deleted", columnList = "deleted_at")
        })
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

//    @ManyToOne//One user can have many project.Many is used for project and one used for user
//    @JoinColumn(name = "owner_id", nullable = false)
//    User owner; //Here we don't need owner because owner is also the member of the project.So, we can get it from
//    project member

    Boolean isPublic = false;

    @CreationTimestamp
    Instant createdAt;

    @UpdateTimestamp
    Instant updatedAt;

    Instant deletedAt;//soft delete
}
