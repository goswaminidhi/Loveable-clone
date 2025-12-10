package com.loveableclone.Service.Impl;

import com.loveableclone.Service.UsageService;
import com.loveableclone.dto.subscription.PlanLimitsResponse;
import com.loveableclone.dto.subscription.UsageTodayResponse;
import org.springframework.stereotype.Service;

@Service
public class UsageServiceImpl implements UsageService {
    @Override
    public UsageTodayResponse getTodayUsageOfUser(Long userId) {
        return null;
    }

    @Override
    public PlanLimitsResponse getCurrentSubscriptionLimitsOfUser(Long userId) {
        return null;
    }
}
