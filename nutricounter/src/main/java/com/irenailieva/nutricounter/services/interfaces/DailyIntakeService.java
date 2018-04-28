package com.irenailieva.nutricounter.services.interfaces;

import com.irenailieva.nutricounter.entities.DailyIntake;
import com.irenailieva.nutricounter.entities.User;

public interface DailyIntakeService {
    DailyIntake createDailyIntakeFor(User user);

    DailyIntake findIntakeByUser(User user);

    void saveDailyIntake(DailyIntake dailyIntake);
}
