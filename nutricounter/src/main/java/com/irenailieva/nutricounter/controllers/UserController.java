package com.irenailieva.nutricounter.controllers;

import com.irenailieva.nutricounter.controllers.base.BaseController;
import com.irenailieva.nutricounter.entities.Gender;
import com.irenailieva.nutricounter.entities.User;
import com.irenailieva.nutricounter.models.view.ProfileEditModel;
import com.irenailieva.nutricounter.models.view.UserViewModel;
import com.irenailieva.nutricounter.services.interfaces.FoodService;
import com.irenailieva.nutricounter.services.interfaces.UserService;
import com.irenailieva.nutricounter.util.UtilModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class UserController extends BaseController {

    private UserService userService;
    private FoodService foodService;

    @Autowired
    public UserController(UserService userService, FoodService foodService) {
        this.userService = userService;
        this.foodService = foodService;
    }

    @GetMapping("/edit-profile")
    public ModelAndView editProfile(@ModelAttribute ProfileEditModel profileEditModel, Principal principal) {
        User user = this.userService.findByUsername(principal.getName());
        ProfileEditModel profile = UtilModelMapper.getInstance().map(user, ProfileEditModel.class);
        profile.setGender(Gender.getNameValue(user.getGender()));
        return super.view("users/edit-profile", "profileEditModel", profile);
    }

    @PostMapping("/edit-profile")
    public ModelAndView editProfileConfirm(@Valid ProfileEditModel profileEditModel, BindingResult bindingResult, Principal principal, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return super.view("users/edit-profile");
        }
        this.userService.editProfile(principal.getName(), profileEditModel);
        redirectAttributes.addFlashAttribute("displayProfileEditAlert", true);
        return super.redirect("/diary");
    }

    @ResponseBody
    @GetMapping("/users/search")
    public ResponseEntity<List<String>> searchUsers(@RequestParam("searchWord") String searchWord) {
        List<String> foundUsers = this.userService.findUsernames(searchWord);
        return new ResponseEntity<>(foundUsers, HttpStatus.OK);
    }

    @GetMapping("/users/{username}")
    public ModelAndView userProfile(@PathVariable("username") String username) {
        User user = this.userService.findByUsername(username);
        UserViewModel userViewModel = this.userService.findUserProfile(username);
        this.foodService.setFoodCount(userViewModel, user);
        return super.view("users/user-profile", "user", userViewModel);
    }
}
