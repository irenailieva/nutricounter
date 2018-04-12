package com.irenailieva.nutricounter.services.interfaces;

import com.irenailieva.nutricounter.entities.DailyIntake;
import com.irenailieva.nutricounter.entities.User;

public interface DailyIntakeService {
    void createDailyIntakeFor(User user);

    DailyIntake findIntakeByUser(User user);
}
