package com.irenailieva.nutricounter.controllers.base;

import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController {

    protected ModelAndView view(String viewName) {
        ModelAndView modelAndView = new ModelAndView("base-layout");
        modelAndView.addObject("view", viewName);
        return modelAndView;
    }

    protected ModelAndView view(String viewName, String attributeName, Object attribute) {
        ModelAndView modelAndView = new ModelAndView("base-layout");
        modelAndView.addObject("view", viewName);
        modelAndView.addObject(attributeName, attribute);
        return modelAndView;
    }

    protected ModelAndView view(String viewName, String attributeName, Object attribute, String attributeName2, Object attribute2) {
        ModelAndView modelAndView = new ModelAndView("base-layout");
        modelAndView.addObject("view", viewName);
        modelAndView.addObject(attributeName, attribute);
        modelAndView.addObject(attributeName2, attribute2);
        return modelAndView;
    }

    protected ModelAndView redirect(String url) {
        return new ModelAndView("redirect:" + url);
    }

    protected ModelAndView redirect(String url, String attributeName, Object attribute) {
        ModelAndView modelAndView = new ModelAndView("redirect:" + url);
        modelAndView.addObject(attributeName, attribute);
        return modelAndView;
    }
}
