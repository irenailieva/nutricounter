package com.irenailieva.nutricounter.controllers;

import com.irenailieva.nutricounter.controllers.base.BaseController;
import com.irenailieva.nutricounter.entities.User;
import com.irenailieva.nutricounter.models.create.RecipeJSONModel;
import com.irenailieva.nutricounter.services.interfaces.RecipeService;
import com.irenailieva.nutricounter.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/recipes")
public class RecipeController extends BaseController {

    private final RecipeService recipeService;
    private final UserService userService;

    @Autowired
    public RecipeController(RecipeService recipeService, UserService userService) {
        this.recipeService = recipeService;
        this.userService = userService;
    }

    @GetMapping("/add")
    public ModelAndView createRecipe(@ModelAttribute RecipeJSONModel recipeJSONModel) {
        return super.view("foods/add-recipe");
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView createRecipeConfirm(@Valid RecipeJSONModel recipeJSONModel, BindingResult bindingResult, Principal principal, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return super.view("foods/add-recipe");
        }

        User user = this.userService.findByUsername(principal.getName());
        this.recipeService.createNewRecipe(recipeJSONModel, user);

        redirectAttributes.addFlashAttribute("displayRecipeCreateSuccess", true);
        return super.redirect("/diary");
    }
}
