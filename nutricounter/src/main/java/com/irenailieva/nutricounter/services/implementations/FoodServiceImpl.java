package com.irenailieva.nutricounter.services.implementations;

import com.irenailieva.nutricounter.entities.*;
import com.irenailieva.nutricounter.models.create.FoodCreateModel;
import com.irenailieva.nutricounter.models.view.UserViewModel;
import com.irenailieva.nutricounter.repositories.CustomFoodRepository;
import com.irenailieva.nutricounter.repositories.EdibleRepository;
import com.irenailieva.nutricounter.repositories.GlobalFoodRepository;
import com.irenailieva.nutricounter.repositories.RecipeRepository;
import com.irenailieva.nutricounter.services.interfaces.FoodService;
import com.irenailieva.nutricounter.services.interfaces.UserService;
import com.irenailieva.nutricounter.util.UtilModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

    private EdibleRepository edibleRepository;
    private GlobalFoodRepository globalFoodRepository;
    private CustomFoodRepository customFoodRepository;
    private RecipeRepository recipeRepository;

    private UserService userService;

    @Autowired
    public FoodServiceImpl(EdibleRepository edibleRepository, GlobalFoodRepository globalFoodRepository, CustomFoodRepository customFoodRepository, RecipeRepository recipeRepository, UserService userService) {
        this.edibleRepository = edibleRepository;
        this.globalFoodRepository = globalFoodRepository;
        this.customFoodRepository = customFoodRepository;
        this.recipeRepository = recipeRepository;
        this.userService = userService;
    }

    @Override
    public GlobalFood createGlobalFood(FoodCreateModel foodCreateModel, String username) {
        GlobalFood globalFood = UtilModelMapper.getInstance().map(foodCreateModel, GlobalFood.class);
        User user = this.userService.findByUsername(username);
        globalFood.setUser(user);
        this.edibleRepository.saveAndFlush(globalFood);

        return globalFood;
    }

    @Override
    public CustomFood createCustomFood(FoodCreateModel foodCreateModel, String username) {
        CustomFood customFood = UtilModelMapper.getInstance().map(foodCreateModel, CustomFood.class);
        User user = this.userService.findByUsername(username);
        customFood.setUser(user);
        this.edibleRepository.saveAndFlush(customFood);

        return customFood;
    }

    @Override
    public Edible findFoodById(long id) {
        return this.edibleRepository.findById(id).orElse(null);
    }

    @Override
    public List<Edible> findFoodsByUserAndName(String username, String foodName) {

        User user = this.userService.findByUsername(username);

        List<GlobalFood> globalFoodsByName = this.globalFoodRepository.findAllByName(foodName);
        List<CustomFood> customFoodsByNameAndUser = this.customFoodRepository.findAllByUserAndName(user, foodName);
        List<Recipe> recipesByNameAndUser = this.recipeRepository.findAllByUserAndName(user, foodName);

        List<Edible> foodSearchResult = new ArrayList<>(globalFoodsByName);
        foodSearchResult.addAll(customFoodsByNameAndUser);
        foodSearchResult.addAll(recipesByNameAndUser);

        return foodSearchResult;
    }

    @Override
    public List<Edible> findFirst10Foods(String username) {

        User user = this.userService.findByUsername(username);

        List<GlobalFood> globalFoodsByName = this.globalFoodRepository.findAll();
        List<CustomFood> customFoodsByNameAndUser = this.customFoodRepository.findAllByUser(user);
        List<Recipe> recipesByNameAndUser = this.recipeRepository.findAllByUser(user);

        List<Edible> foodSearchResult = new ArrayList<>(globalFoodsByName);
        foodSearchResult.addAll(customFoodsByNameAndUser);
        foodSearchResult.addAll(recipesByNameAndUser);

        if (foodSearchResult.size() < 10) {
            return foodSearchResult;
        }

        return foodSearchResult.subList(0, 10);
    }

    @Override
    public void setFoodCount(UserViewModel userViewModel, User user) {
        userViewModel.setCustomFoodCount(this.customFoodRepository.countAllByUser(user));
        userViewModel.setGlobalFoodCount(this.globalFoodRepository.countAllByUser(user));
        userViewModel.setRecipeCount(this.recipeRepository.countAllByUser(user));
    }

    @Override
    public long countAllFoods() {
        return this.edibleRepository.count();
    }
}
