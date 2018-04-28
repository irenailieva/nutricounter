package com.irenailieva.nutricounter.services.interfaces;

import com.irenailieva.nutricounter.entities.DailyIntake;
import com.irenailieva.nutricounter.entities.User;

import java.util.concurrent.Future;

public interface DailyIntakeService {
    Future<DailyIntake> createDailyIntakeFor(User user);

    DailyIntake findIntakeByUser(User user);

    void saveDailyIntake(DailyIntake dailyIntake);
}
