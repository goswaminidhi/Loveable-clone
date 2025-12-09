package com.loveableclone.Service;

import com.loveableclone.dto.subscription.CheckoutRequest;
import com.loveableclone.dto.subscription.CheckoutResponse;
import com.loveableclone.dto.subscription.PortalResponse;
import com.loveableclone.dto.subscription.SubscriptionResponse;

public interface SubscriptionService {

    SubscriptionResponse getCurrentSubscription(Long userId);

    CheckoutResponse createCheckoutSession(CheckoutRequest request, Long userId);

    PortalResponse openCustomerPortal(Long userId);
}
