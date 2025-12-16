package com.loveableclone.entity;

import com.loveableclone.enums.SubscriptionStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Subscription {

    Long id;

    User user;//here we are not using id, instead using direct user hibernate will play the projectRole
    Plan plan;

    SubscriptionStatus status;

    String stripeCustomerId;//inside each subscription we are storing information about the customer
    //this is present at the stripe dashboard
    String stripeSubscriptionId;//User can see the subscription inside the dashboard

    Instant currentPeriodStart;//Subscription period start and if we renew our plan it will
    // be updated.
    Instant currentPeriodEnd;

    Boolean cancelAtPeriodEnd = false;

    Instant createdAt;
    Instant updatedAt;

}
