package com.loveableclone.dto.subscription;

public record PlanResponse(
        Long Id,
        String name,
        Integer maxProject,
        Integer maxTokenPerDay,
        Boolean unlimitedAi,
        String price
) {
}
