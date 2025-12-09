package com.loveableclone.dto.subscription;

public record PlanLimitsResponse(
        String planName,
        int maxTokensPer,
        int maxProjects,
        boolean unlimitedAi
) {
}
