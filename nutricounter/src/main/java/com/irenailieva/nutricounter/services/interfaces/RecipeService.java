package com.irenailieva.nutricounter.services.interfaces;

import com.irenailieva.nutricounter.entities.Ingredient;
import com.irenailieva.nutricounter.entities.Recipe;
import com.irenailieva.nutricounter.entities.User;
import com.irenailieva.nutricounter.models.create.RecipeJSONModel;
import com.irenailieva.nutricounter.models.view.RecipeViewModel;

import java.util.List;

public interface RecipeService {
    Recipe createNewRecipe(RecipeJSONModel recipeJSONModel, User user, String recipeImageName);

    Recipe convertJSONToRecipe(RecipeJSONModel recipeJSONModel);

    default void calculateRecipeNutrients(Recipe recipe) {

        //energy, water, carbs, protein, fat, vitA, vitB6, vitC, calcium, iron

        double energy = 0;
        double water = 0;
        double carbs = 0;
        double protein = 0;
        double fat = 0;
        double vitA = 0;
        double vitB6 = 0;
        double vitC = 0;
        double calcium = 0;
        double iron = 0;

        for (Ingredient ingredient : recipe.getIngredients()) {
            energy += ingredient.getEdible().getEnergy() * ingredient.getGrams() / 100.0;
            water += ingredient.getEdible().getWater() * ingredient.getGrams() / 100.0;
            carbs += ingredient.getEdible().getCarbohydrates() * ingredient.getGrams() / 100.0;
            protein += ingredient.getEdible().getProtein() * ingredient.getGrams() / 100.0;
            fat += ingredient.getEdible().getFat() * ingredient.getGrams() / 100.0;
            vitA += ingredient.getEdible().getVitaminA() * ingredient.getGrams() / 100.0;
            vitB6 += ingredient.getEdible().getVitaminB6() * ingredient.getGrams() / 100.0;
            vitC += ingredient.getEdible().getVitaminC() * ingredient.getGrams() / 100.0;
            calcium += ingredient.getEdible().getCalcium() * ingredient.getGrams() / 100.0;
            iron += ingredient.getEdible().getIron() * ingredient.getGrams() / 100.0;
        }

        recipe.setEnergy(energy);
        recipe.setWater(water);
        recipe.setCarbohydrates(carbs);
        recipe.setProtein(protein);
        recipe.setFat(fat);
        recipe.setVitaminA(vitA);
        recipe.setVitaminB6(vitB6);
        recipe.setVitaminC(vitC);
        recipe.setCalcium(calcium);
        recipe.setIron(iron);
    }

    List<RecipeViewModel> getUserRecipes(User user);

    void deleteRecipeById(long recipeId);
}
