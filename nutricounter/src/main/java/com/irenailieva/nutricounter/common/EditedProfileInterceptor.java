package com.irenailieva.nutricounter.common;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class EditedProfileInterceptor extends HandlerInterceptorAdapter {

    private static final String DIARY_VIEW_NAME = "diary";

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        if (!modelAndView.getViewName().equals(DIARY_VIEW_NAME)) {
            return;
        }
    }
}
