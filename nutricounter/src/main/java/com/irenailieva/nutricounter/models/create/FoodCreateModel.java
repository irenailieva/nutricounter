package com.irenailieva.nutricounter.models.create;

import com.irenailieva.nutricounter.util.WebConstants;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
public class FoodCreateModel {

    @NotNull(message = WebConstants.EMPTY_FOOD_NAME_MESSAGE)
    @NotEmpty(message = WebConstants.EMPTY_FOOD_NAME_MESSAGE)
    private String name;

    private double energy;

    private double water;

    private double carbohydrates;

    private double protein;

    private double fat;

    private double vitaminA;

    private double vitaminB6;

    private double vitaminC;

    private double calcium;

    private double iron;

    public void setName(String name) {
        this.name = name;
    }

    public void setEnergy(double energy) {
        this.energy = energy < 0 ? 0 : energy;
    }

    public void setWater(double water) {
        this.water = water < 0 ? 0 : water;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates < 0 ? 0 : carbohydrates;
    }

    public void setFat(double fat) {
        this.fat = fat < 0 ? 0 : fat;
    }

    public void setProtein(double protein) {
        this.protein = protein < 0 ? 0 : protein;
    }

    public void setVitaminA(double vitaminA) {
        this.vitaminA = vitaminA < 0 ? 0 : vitaminA;
    }

    public void setVitaminB6(double vitaminB6) {
        this.vitaminB6 = vitaminB6 < 0 ? 0 : vitaminB6;
    }

    public void setVitaminC(double vitaminC) {
        this.vitaminC = vitaminC < 0 ? 0 : vitaminC;
    }

    public void setCalcium(double calcium) {
        this.calcium = calcium < 0 ? 0 : calcium;
    }

    public void setIron(double iron) {
        this.iron = iron < 0 ? 0 : iron;
    }
}
