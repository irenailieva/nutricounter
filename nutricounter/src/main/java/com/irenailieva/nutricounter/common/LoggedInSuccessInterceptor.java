package com.irenailieva.nutricounter.common;

import com.irenailieva.nutricounter.annotations.LoginFormGetHandler;
import com.irenailieva.nutricounter.util.WebConstants;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class LoggedInSuccessInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        if (method.isAnnotationPresent(LoginFormGetHandler.class)) {
            return;
        }

        if (request.getSession().getAttribute(WebConstants.LOGIN_SUCCESS_ALERT_KEY) != null) {
            modelAndView.getModelMap().addAttribute(WebConstants.LOGIN_SUCCESS_ALERT_KEY, true);
        }
        request.getSession().removeAttribute(WebConstants.LOGIN_SUCCESS_ALERT_KEY);
    }


}
