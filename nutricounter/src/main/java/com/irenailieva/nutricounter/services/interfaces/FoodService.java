package com.irenailieva.nutricounter.services.interfaces;

import com.irenailieva.nutricounter.entities.*;
import com.irenailieva.nutricounter.models.create.FoodCreateModel;
import com.irenailieva.nutricounter.models.create.RecipeJSONModel;
import com.irenailieva.nutricounter.models.view.UserViewModel;

import java.util.List;

public interface FoodService {
    Edible findFoodById(long id);

    List<Edible> findFirst10Foods(String username);

    List<CustomFood> findAllUserCustomFoods(String username);
    List<Edible> findFoodsByUserAndName(String username, String foodName);

    void createGlobalFood(FoodCreateModel foodCreateModel, String username);
    void createCustomFood(FoodCreateModel foodCreateModel, String username);
    void createRecipe(RecipeJSONModel recipeJSONModel, String username);

    void setFoodCount(UserViewModel userViewModel, User user);
}
