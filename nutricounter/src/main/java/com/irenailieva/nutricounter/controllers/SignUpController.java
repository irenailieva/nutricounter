package com.irenailieva.nutricounter.controllers;

import com.irenailieva.nutricounter.controllers.base.BaseController;
import com.irenailieva.nutricounter.models.service.UserSignUpModel;
import com.irenailieva.nutricounter.services.interfaces.UserSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class SignUpController extends BaseController {

    private UserSignUpService userSignUpService;

    @Autowired
    public SignUpController(UserSignUpService userSignUpService) {
        this.userSignUpService = userSignUpService;
    }

    @GetMapping("/sign-up")
    @PreAuthorize("isAnonymous()")
    public ModelAndView signUp(@ModelAttribute UserSignUpModel userSignUpModel) {
        return super.view("sign-up");
    }

    @PostMapping("/sign-up")
    @PreAuthorize("isAnonymous()")
    public ModelAndView signUpConfirm(@Valid UserSignUpModel userSignUpModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()
                || !this.userSignUpService.validUserSignUpDetails(userSignUpModel, bindingResult)) {
            return super.view("sign-up");
        }

        this.userSignUpService.createUser(userSignUpModel);
        redirectAttributes.addFlashAttribute("displaySignUpSuccessAlert", true);
        return super.redirect("/login");
    }
}
