package com.irenailieva.nutricounter.security;

import com.irenailieva.nutricounter.util.WebConstants;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class MySimpleUrlAuthenticationSuccessHandler implements MyAuthenticationSuccessHandler {

    private static final String DEFAULT_REDIRECT_URL = "/diary";

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        handle(httpServletRequest, httpServletResponse, authentication);
        clearAuthenticationAttributes(httpServletRequest);
    }

    protected void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        String targetUrl = determineTargetUrl(httpServletRequest);
        httpServletRequest.getSession().setAttribute(WebConstants.LOGIN_SUCCESS_ALERT_KEY, true);
        redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, targetUrl);
    }

    protected String determineTargetUrl(HttpServletRequest httpServletRequest) {

        SavedRequest savedRequest = (SavedRequest) httpServletRequest.getSession().getAttribute(WebConstants.SPRING_SECURITY_SAVED_REQUEST_KEY);

        if (savedRequest == null) {
            return DEFAULT_REDIRECT_URL;
        }

        return savedRequest.getRedirectUrl();
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
