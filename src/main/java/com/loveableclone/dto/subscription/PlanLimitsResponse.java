package com.loveableclone.dto.subscription;

public record PlanLimitsResponse(
        String planName,
        Integer maxTokensPer,
        Integer maxProjects,
        Boolean unlimitedAi
) {
}
