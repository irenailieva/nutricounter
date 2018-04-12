package com.irenailieva.nutricounter.common;

import com.irenailieva.nutricounter.annotations.LoginFormGetHandler;
import com.irenailieva.nutricounter.util.WebConstants;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class LoginFormInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        if (!method.isAnnotationPresent(LoginFormGetHandler.class)) {
            return;
        }

        SavedRequest savedRequest = (SavedRequest) request.getSession().getAttribute(WebConstants.SPRING_SECURITY_SAVED_REQUEST_KEY);

        if (savedRequest != null) {
            modelAndView.getModelMap().addAttribute(WebConstants.LOGIN_WARNING_ALERT_KEY, true);
        }

        String error = request.getParameter("error");
        String logout = request.getParameter("logout");

        if (error != null) {
            modelAndView.addObject("error", WebConstants.INCORRECT_USER_LOGIN_DETAILS);
        }
        if (logout != null) {
            modelAndView.addObject(WebConstants.LOGGED_OUT_ALERT_KEY, true);
        }
    }
}
