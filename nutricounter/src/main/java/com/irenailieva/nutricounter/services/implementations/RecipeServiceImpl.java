package com.irenailieva.nutricounter.services.implementations;

import com.irenailieva.nutricounter.models.create.RecipeCreateModel;
import com.irenailieva.nutricounter.repositories.RecipeRepository;
import com.irenailieva.nutricounter.services.interfaces.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public void createNewRecipe(RecipeCreateModel recipeCreateModel) {

    }
}
