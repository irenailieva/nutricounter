package com.irenailieva.nutricounter.services.implementations;

import com.irenailieva.nutricounter.entities.DailyIntake;
import com.irenailieva.nutricounter.entities.User;
import com.irenailieva.nutricounter.repositories.DailyIntakeRepository;
import com.irenailieva.nutricounter.services.interfaces.DailyIntakeService;
import com.irenailieva.nutricounter.util.DailyIntakeCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DailyIntakeServiceImpl implements DailyIntakeService {

    private DailyIntakeRepository dailyIntakeRepository;

    @Autowired
    public DailyIntakeServiceImpl(DailyIntakeRepository dailyIntakeRepository) {
        this.dailyIntakeRepository = dailyIntakeRepository;
    }

    @Override
    public DailyIntake createDailyIntakeFor(User user) {
        DailyIntake dailyIntake = new DailyIntake();
        dailyIntake.setUser(user);
        DailyIntakeCalculator.setDailyIntakeValues(user, dailyIntake);
        this.dailyIntakeRepository.saveAndFlush(dailyIntake);
        return dailyIntake;
    }

    @Override
    public DailyIntake findIntakeByUser(User user) {
        return this.dailyIntakeRepository.findByUserId(user.getId());
    }

    @Override
    public void saveDailyIntake(DailyIntake dailyIntake) {
        this.dailyIntakeRepository.saveAndFlush(dailyIntake);
    }
}
