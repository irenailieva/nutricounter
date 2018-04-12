package com.irenailieva.nutricounter.controllers;

import com.irenailieva.nutricounter.annotations.LoginFormGetHandler;
import com.irenailieva.nutricounter.controllers.base.BaseController;
import com.irenailieva.nutricounter.util.WebConstants;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController extends BaseController {

    @LoginFormGetHandler
    @GetMapping("/login")
    @PreAuthorize("isAnonymous()")
    public ModelAndView login(HttpServletRequest request, @RequestParam(required = false, name = "error") String error, @RequestParam(required = false, name = "logout") String loggedOutAlert) {

        ModelAndView modelAndView = super.view("login");
        if (error != null) {
            modelAndView.addObject("error", WebConstants.INCORRECT_USER_LOGIN_DETAILS);
        }
        if (loggedOutAlert != null) {
            modelAndView.addObject(WebConstants.LOGGED_OUT_ALERT_KEY, true);
        }
        return modelAndView;
    }
}
