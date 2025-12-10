package com.loveableclone.Service.Impl;

import com.loveableclone.Service.PlanService;
import com.loveableclone.dto.subscription.PlanResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {
    @Override
    public List<PlanResponse> getAllActivePlans() {
        return List.of();
    }
}
