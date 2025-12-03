package com.loveableclone.entity;

import java.time.Instant;

public class UsageLog {
    //It will help us to manage the subscription and plan

    Long id;

    Project project;

    User user;

    String action;

    Integer durationMs;
    Integer tokenUsed;

    String metaData;//Can store json of {model_used, prompt_used}

    Instant createdAt;
}
