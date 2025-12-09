package com.loveableclone.Service;

import com.loveableclone.dto.subscription.PlanLimitsResponse;
import com.loveableclone.dto.subscription.UsageTodayResponse;

public interface UsageService {
    UsageTodayResponse getTodayUsageOfUser(Long userId);

    PlanLimitsResponse getCurrentSubscriptionLimitsOfUser(Long userId);
}
