package com.irenailieva.nutricounter.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Scheduled(fixedRate = 3000)
    public void doStuff() {
        //System.out.println("Hello");
    }
}