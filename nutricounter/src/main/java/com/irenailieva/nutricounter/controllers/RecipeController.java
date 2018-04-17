package com.irenailieva.nutricounter.controllers;

import com.irenailieva.nutricounter.controllers.base.BaseController;
import com.irenailieva.nutricounter.entities.User;
import com.irenailieva.nutricounter.models.create.RecipeJSONModel;
import com.irenailieva.nutricounter.services.interfaces.RecipeService;
import com.irenailieva.nutricounter.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@RequestMapping("/recipes")
@RestController
public class RecipeController extends BaseController {

    private final RecipeService recipeService;
    private final UserService userService;

    @Autowired
    public RecipeController(RecipeService recipeService, UserService userService) {
        this.recipeService = recipeService;
        this.userService = userService;
    }

    @GetMapping("/add")
    public ModelAndView createRecipe(RecipeJSONModel recipeJSONModel) {
        return super.view("foods/add-recipe");
    }

    @ResponseBody
    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView createRecipeConfirm(RecipeJSONModel recipeJSONModel, Principal principal, RedirectAttributes redirectAttributes) {
        System.out.println("RECIPE BITCH");
        System.out.println("RECIPE BITCH");
        System.out.println("RECIPE BITCH");
        System.out.println("RECIPE BITCH");
        System.out.println("RECIPE BITCH");
        System.out.println("RECIPE BITCH");
        System.out.println("RECIPE BITCH");

        User user = this.userService.findByUsername(principal.getName());
        this.recipeService.createNewRecipe(recipeJSONModel, user);

        redirectAttributes.addFlashAttribute("displayRecipeCreateSuccess", true);
        return super.redirect("/diary");
    }
}
