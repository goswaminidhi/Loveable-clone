package com.loveableclone.entity;

import com.loveableclone.enums.PreviewStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Preview {
    //We should have the knowledge of where this project is running,this kind of information
    // is described here

    Long id;

    Project project;

    String namespace;//Used to keep certain thing separate from each other
    String podName;//Smallest unit in kubernet
    String previewUrl;

    PreviewStatus status;

    Instant startedAT;
    Instant terminatedAT;

    Instant createdAT;

}
