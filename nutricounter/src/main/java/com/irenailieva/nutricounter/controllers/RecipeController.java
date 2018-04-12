package com.irenailieva.nutricounter.controllers;

import com.irenailieva.nutricounter.controllers.base.BaseController;
import com.irenailieva.nutricounter.models.create.RecipeCreateModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/recipes")
@RestController
public class RecipeController extends BaseController {

    @GetMapping("/add")
    public ModelAndView createRecipe(RecipeCreateModel recipeCreateModel) {
        return super.view("foods/add-recipe");
    }

    @PostMapping("/add")
    public ModelAndView createRecipeConfirm(RecipeCreateModel recipeCreateModel) {
        System.out.println("VLIZAM");
        return null;
    }
}
