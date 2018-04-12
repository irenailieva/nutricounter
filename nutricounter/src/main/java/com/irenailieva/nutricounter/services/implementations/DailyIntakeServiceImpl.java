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
    public void createDailyIntakeFor(User user) {
        DailyIntake dailyIntake = new DailyIntake();
        dailyIntake.setUser(user);
        this.setDailyValues(dailyIntake, user);
        this.dailyIntakeRepository.saveAndFlush(dailyIntake);
    }

    private void setDailyValues(DailyIntake dailyIntake, User user) {

        dailyIntake.setEnergy(DailyIntakeCalculator.calculateEnergy(user));
        dailyIntake.setWater(DailyIntakeCalculator.calculateWater(user));
        dailyIntake.setCarbohydrates(DailyIntakeCalculator.calculateCarbs(dailyIntake.getEnergy()));
        dailyIntake.setFat(DailyIntakeCalculator.calculateFat(dailyIntake.getEnergy()));
        dailyIntake.setProtein(DailyIntakeCalculator.calculateProtein(user));
        dailyIntake.setVitaminA(DailyIntakeCalculator.calculateVitaminA());
        dailyIntake.setVitaminB6(DailyIntakeCalculator.calculateVitaminB6());
        dailyIntake.setVitaminC(DailyIntakeCalculator.calculateVitaminC());
        dailyIntake.setIron(DailyIntakeCalculator.calculateIron(user));
        dailyIntake.setCalcium(DailyIntakeCalculator.calculateCalcium(user));
    }

    @Override
    public DailyIntake findIntakeByUser(User user) {
        return this.dailyIntakeRepository.findByUserId(user.getId());
    }
}
