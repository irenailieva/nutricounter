package com.irenailieva.nutricounter.controllers;

import com.irenailieva.nutricounter.controllers.base.BaseController;
import com.irenailieva.nutricounter.entities.Edible;
import com.irenailieva.nutricounter.models.create.FoodCreateModel;
import com.irenailieva.nutricounter.services.interfaces.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/foods")
public class FoodController extends BaseController {

    private FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/add/custom")
    public ModelAndView addCustomFood(@ModelAttribute FoodCreateModel foodCreateModel) {
        return super.view("foods/add-custom");
    }

    @PostMapping("/add/custom")
    public ModelAndView addCustomFoodConfirm(@Valid FoodCreateModel foodCreateModel, BindingResult bindingResult, Principal principal, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return super.view("foods/add-custom");
        }
        this.foodService.createCustomFood(foodCreateModel, principal.getName());
        redirectAttributes.addFlashAttribute("displayCustomFoodCreateSuccess", true);
        return super.redirect("/diary");
    }

    @GetMapping("/add/global")
    @PreAuthorize("hasAnyRole('ADMIN', 'CHIEF ADMIN')")
    public ModelAndView addGlobalFood(@ModelAttribute FoodCreateModel foodCreateModel) {
        return super.view("foods/add-global");
    }

    @PostMapping("/add/global")
    @PreAuthorize("hasAnyRole('ADMIN', 'CHIEF ADMIN')")
    public ModelAndView addGlobalFoodConfirm(@Valid FoodCreateModel foodCreateModel, BindingResult bindingResult, Principal principal, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return super.view("foods/add-global");
        }
        this.foodService.createGlobalFood(foodCreateModel, principal.getName());
        redirectAttributes.addFlashAttribute("displayGlobalFoodCreateSuccess", true);
        return super.redirect("/diary");
    }

    @GetMapping("/first10")
    public ResponseEntity<List<Edible>> findFoods(@RequestParam("username") String username) {
        List<Edible> foods = this.foodService.findFirst10Foods(username);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/search")
    public ResponseEntity<List<Edible>> search(@RequestParam("searchWord") String searchWord, @RequestParam("username") String username) {
        List<Edible> foundFoods = this.foodService.findFoodsByUserAndName(username, searchWord);
        return new ResponseEntity<>(foundFoods, HttpStatus.OK);
    }
}
