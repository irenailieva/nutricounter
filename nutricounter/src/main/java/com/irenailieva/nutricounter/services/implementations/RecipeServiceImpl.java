package com.irenailieva.nutricounter.services.implementations;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.irenailieva.nutricounter.entities.Ingredient;
import com.irenailieva.nutricounter.entities.Recipe;
import com.irenailieva.nutricounter.entities.User;
import com.irenailieva.nutricounter.models.create.RecipeJSONModel;
import com.irenailieva.nutricounter.models.service.FoodEntryModel;
import com.irenailieva.nutricounter.models.view.RecipeViewModel;
import com.irenailieva.nutricounter.repositories.RecipeRepository;
import com.irenailieva.nutricounter.services.interfaces.IngredientService;
import com.irenailieva.nutricounter.services.interfaces.RecipeService;
import com.irenailieva.nutricounter.util.UtilModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    private final IngredientService ingredientService;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository, IngredientService ingredientService) {
        this.recipeRepository = recipeRepository;
        this.ingredientService = ingredientService;
    }

    @Override
    public Recipe createNewRecipe(RecipeJSONModel recipeJSONModel, User user, String recipeImageUrl) {

        Recipe recipe = this.convertJSONToRecipe(recipeJSONModel);
        recipe.setUser(user);
        recipe.setRecipeImageUrl(recipeImageUrl);

        List<Ingredient> ingredientList = recipe.getIngredients();
        recipe.setIngredients(new ArrayList<>());
        this.recipeRepository.saveAndFlush(recipe);
        this.ingredientService.saveIngredientList(ingredientList, recipe);

        return recipe;
    }

    @Override
    public Recipe convertJSONToRecipe(RecipeJSONModel recipeJSONModel) {

        Gson gson = new Gson();
        String jsonRecipe = recipeJSONModel.getRecipeDataJSON();
        List<FoodEntryModel> foodEntryModelList = gson.fromJson(jsonRecipe, new TypeToken<List<FoodEntryModel>>(){}.getType());
        foodEntryModelList.remove(foodEntryModelList.size() - 1);

        String recipeName = foodEntryModelList.get(0).getValue();
        Recipe recipe = new Recipe();
        recipe.setName(recipeName);
        foodEntryModelList.remove(0);

        List<Ingredient> ingredientList = this.ingredientService.createIngredientList(foodEntryModelList);
        recipe.setIngredients(ingredientList);

        this.calculateRecipeNutrients(recipe);

        return recipe;
    }

    @Override
    public List<RecipeViewModel> getUserRecipes(User user) {

        List<Recipe> recipes = this.recipeRepository.findAllByUser(user);
        List<RecipeViewModel> recipeViewModels = new ArrayList<>();

        for (Recipe recipe : recipes) {
            RecipeViewModel recipeViewModel = UtilModelMapper.getInstance().map(recipe, RecipeViewModel.class);
            recipeViewModel.setIngredients(recipe.getIngredientsAsString());
            recipeViewModels.add(recipeViewModel);
        }

        return recipeViewModels;
    }

    @Override
    public void deleteRecipeById(long recipeId) {
        this.recipeRepository.deleteById(recipeId);
    }
}
