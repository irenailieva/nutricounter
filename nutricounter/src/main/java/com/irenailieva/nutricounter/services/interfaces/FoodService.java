package com.irenailieva.nutricounter.services.interfaces;

import com.irenailieva.nutricounter.entities.CustomFood;
import com.irenailieva.nutricounter.entities.Edible;
import com.irenailieva.nutricounter.entities.GlobalFood;
import com.irenailieva.nutricounter.entities.User;
import com.irenailieva.nutricounter.models.create.FoodCreateModel;
import com.irenailieva.nutricounter.models.view.UserViewModel;

import java.util.List;

public interface FoodService {
    Edible findFoodById(long id);

    List<Edible> findFirst10Foods(String username);

    List<Edible> findFoodsByUserAndName(String username, String foodName);

    GlobalFood createGlobalFood(FoodCreateModel foodCreateModel, String username);
    CustomFood createCustomFood(FoodCreateModel foodCreateModel, String username);

    void setFoodCount(UserViewModel userViewModel, User user);

    long countAllFoods();
}
