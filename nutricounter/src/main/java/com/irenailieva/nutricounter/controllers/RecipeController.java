package com.irenailieva.nutricounter.controllers;

import com.irenailieva.nutricounter.cloud.CloudImageExtractor;
import com.irenailieva.nutricounter.cloud.CloudImageUploader;
import com.irenailieva.nutricounter.controllers.base.BaseController;
import com.irenailieva.nutricounter.entities.User;
import com.irenailieva.nutricounter.exceptions.RecipeCreationException;
import com.irenailieva.nutricounter.models.create.RecipeJSONModel;
import com.irenailieva.nutricounter.models.view.RecipeViewModel;
import com.irenailieva.nutricounter.services.interfaces.RecipeService;
import com.irenailieva.nutricounter.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/recipes")
public class RecipeController extends BaseController {

    private final CloudImageExtractor cloudImageExtractor;
    private final CloudImageUploader cloudImageUploader;

    private final RecipeService recipeService;
    private final UserService userService;

    @Autowired
    public RecipeController(CloudImageExtractor cloudImageExtractor, CloudImageUploader cloudImageUploader, RecipeService recipeService, UserService userService) {
        this.cloudImageExtractor = cloudImageExtractor;
        this.cloudImageUploader = cloudImageUploader;
        this.recipeService = recipeService;
        this.userService = userService;
    }

    @GetMapping("/add")
    public ModelAndView createRecipe(@ModelAttribute RecipeJSONModel recipeJSONModel) {
        return super.view("foods/add-recipe");
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView createRecipeConfirm(@Valid RecipeJSONModel recipeJSONModel, BindingResult bindingResult, @RequestParam("file") MultipartFile file, Principal principal, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return super.view("foods/add-recipe");
        }

        String recipeImageUrl;
        try {
            recipeImageUrl = this.cloudImageUploader.uploadFile(file);
        } catch (IOException e) {
            throw new RecipeCreationException();
        }

        User user = this.userService.findByUsername(principal.getName());
        this.recipeService.createNewRecipe(recipeJSONModel, user, recipeImageUrl);

        redirectAttributes.addFlashAttribute("displayRecipeCreateSuccess", true);
        return super.redirect("/recipes/my-recipes");
    }

    @GetMapping("/my-recipes")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getUserRecipes(Principal principal) {

        User user = this.userService.findByUsername(principal.getName());
        List<RecipeViewModel> recipeViewModels = this.recipeService.getUserRecipes(user);

        return super.view("foods/user-recipes", "recipes", recipeViewModels);
    }

    @PostMapping("/delete/{recipeId}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView deleteRecipe(@PathVariable("recipeId") long recipeId, RedirectAttributes redirectAttributes) {

        this.recipeService.deleteRecipeById(recipeId);

        redirectAttributes.addFlashAttribute("displayRecipeDeleteSuccess", true);
        return super.redirect("/recipes/my-recipes");
    }
}
