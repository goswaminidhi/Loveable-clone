package com.loveableclone.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.StandardException;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Plan {

    Long Id;
    String name;
    String stripePriceId;
    Integer maxProject;
    Integer maxTokenPerDay;
    Integer maxPreviews; //Application is store in pod and for preview we have to hit that
    //and we are allocating some resources which are active features.
    Boolean unlimitedAi; //If this is true than it will ignore max token per day the max Unlimited access to LLM.
    //features;
    Boolean active; //This is for the admin whether they want to make the plan active or not
}
