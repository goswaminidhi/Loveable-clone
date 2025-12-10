package com.loveableclone.Service.Impl;

import com.loveableclone.Service.SubscriptionService;
import com.loveableclone.dto.subscription.CheckoutRequest;
import com.loveableclone.dto.subscription.CheckoutResponse;
import com.loveableclone.dto.subscription.PortalResponse;
import com.loveableclone.dto.subscription.SubscriptionResponse;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Override
    public SubscriptionResponse getCurrentSubscription(Long userId) {
        return null;
    }

    @Override
    public CheckoutResponse createCheckoutSession(CheckoutRequest request, Long userId) {
        return null;
    }

    @Override
    public PortalResponse openCustomerPortal(Long userId) {
        return null;
    }
}
