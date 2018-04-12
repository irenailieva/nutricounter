package com.irenailieva.nutricounter.util;

import com.irenailieva.nutricounter.entities.User;

import java.util.Calendar;

public class DailyIntakeCalculator {

//    Men: BMR=66.47+ (13.75 x W) + (5.0 x H) - (6.75 x A)
//    Women: BMR=665.09 + (9.56 x W) + (1.84 x H) - (4.67 x A)

    private static final double LIGHT_EXERCISE_MULTIPLIER = 1.375;

    private static final double FEMALE_DAILY_WATER_INTAKE = 2.2;
    private static final double MALE_DAILY_WATER_INTAKE = 2.9;

    private static final double VIT_A_DAILY_INTAKE = 3000;
    private static final double VIT_B6_DAILY_INTAKE = 1.3;
    private static final double VIT_C_DAILY_INTAKE = 75;

    public static double calculateEnergy(User user) {
        double energy = 0.0;

        int userAge = Calendar.getInstance().get(Calendar.YEAR) - user.getDateOfBirth().getYear();

        switch (user.getGender()) {
            case MALE:
                energy = 66.47 + (13.75 * user.getWeight()) + (5 * user.getHeight()) - (6.75 * userAge);
                break;
            case FEMALE:
                energy = 665.09 + (9.56 * user.getWeight()) + (1.84 * user.getHeight()) - (4.67 * userAge);
                break;
        }
        return energy * LIGHT_EXERCISE_MULTIPLIER;
    }

    public static double calculateWater(User user) {
        switch(user.getGender()) {
            case FEMALE: return FEMALE_DAILY_WATER_INTAKE;
            default: return MALE_DAILY_WATER_INTAKE;
        }
    }

    public static double calculateCarbs(double energy) {
        return energy * 70.0 / 100.0 / 4;
    }

    public static double calculateFat(double energy) {
        return energy * 15.0 / 100.0 / 9;
    }

    public static double calculateProtein(User user) {
        return user.getWeight() * 0.8;
    }

    public static double calculateVitaminA() {
        return VIT_A_DAILY_INTAKE;
    }

    public static double calculateVitaminB6() {
        return VIT_B6_DAILY_INTAKE;
    }

    public static double calculateVitaminC() {
        return VIT_C_DAILY_INTAKE;
    }

    public static double calculateCalcium(User user) {
        int userAge = Calendar.getInstance().get(Calendar.YEAR) - user.getDateOfBirth().getYear();
        if (userAge <= 3) {
            return 700;
        }
        else if (userAge <= 8) {
            return 1000;
        }
        else if (userAge <= 18) {
            return 1300;
        }
        else if (userAge <= 70) {
            return 1000;
        }
        else {
            return 1200;
        }
    }

    public static double calculateIron(User user) {
        int userAge = Calendar.getInstance().get(Calendar.YEAR) - user.getDateOfBirth().getYear();
        if (userAge <= 3) {
            return 7;
        }
        else if (userAge <= 8) {
            return 10;
        }
        else if (userAge <= 13) {
            return 8;
        }
        else if (userAge <= 18) {
            switch (user.getGender()) {
                case FEMALE: return 15;
                default: return 11;
            }
        }
        else if (userAge <= 50) {
            switch (user.getGender()) {
                case FEMALE: return 18;
                default: return 8;
            }
        }
        else {
            return 8;
        }
    }
}
