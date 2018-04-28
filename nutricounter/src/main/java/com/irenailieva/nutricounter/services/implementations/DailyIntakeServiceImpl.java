package com.irenailieva.nutricounter.services.implementations;

import com.irenailieva.nutricounter.entities.DailyIntake;
import com.irenailieva.nutricounter.entities.User;
import com.irenailieva.nutricounter.repositories.DailyIntakeRepository;
import com.irenailieva.nutricounter.services.interfaces.DailyIntakeService;
import com.irenailieva.nutricounter.util.DailyIntakeCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class DailyIntakeServiceImpl implements DailyIntakeService {

    private DailyIntakeRepository dailyIntakeRepository;

    @Autowired
    public DailyIntakeServiceImpl(DailyIntakeRepository dailyIntakeRepository) {
        this.dailyIntakeRepository = dailyIntakeRepository;
    }

    @Async
    @Override
    public Future<DailyIntake> createDailyIntakeFor(User user) {
        DailyIntake dailyIntake = new DailyIntake();
        dailyIntake.setUser(user);
        DailyIntakeCalculator.setDailyIntakeValues(user, dailyIntake);
        this.dailyIntakeRepository.saveAndFlush(dailyIntake);
        return new AsyncResult<>(dailyIntake);
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
