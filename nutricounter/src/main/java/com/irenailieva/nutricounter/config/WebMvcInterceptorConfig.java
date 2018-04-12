package com.irenailieva.nutricounter.config;

import com.irenailieva.nutricounter.common.LoggedInSuccessInterceptor;
import com.irenailieva.nutricounter.common.LoginFormInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcInterceptorConfig implements WebMvcConfigurer {

    private LoginFormInterceptor loginFormInterceptor;
    private LoggedInSuccessInterceptor loggedInSuccessInterceptor;

    @Autowired
    public WebMvcInterceptorConfig(LoginFormInterceptor loginFormInterceptor, LoggedInSuccessInterceptor loggedInSuccessInterceptor) {
        this.loginFormInterceptor = loginFormInterceptor;
        this.loggedInSuccessInterceptor = loggedInSuccessInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.loginFormInterceptor);
        registry.addInterceptor(this.loggedInSuccessInterceptor);
    }
}
