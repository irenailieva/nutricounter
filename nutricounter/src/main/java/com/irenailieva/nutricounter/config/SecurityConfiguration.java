package com.irenailieva.nutricounter.config;

import com.irenailieva.nutricounter.security.MyAuthenticationSuccessHandler;
import com.irenailieva.nutricounter.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String[] PERMITTED_ROUTES = {"/", "/login", "/sign-up", "/styles/**", "/scripts/**", "/images/**"};

    private final MyAuthenticationSuccessHandler authenticationSuccessHandler;

    private final UserService userService;

    @Autowired
    public SecurityConfiguration(MyAuthenticationSuccessHandler authenticationSuccessHandler, UserService userService) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(PERMITTED_ROUTES).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/diary")
                .successHandler(this.authenticationSuccessHandler)
                .and().logout().logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
        .and().userDetailsService(this.userService)
                .rememberMe()
                .rememberMeParameter("remember")
                .key("48433e39-e610-4a2c-926c-f86d46f5360a")
                .rememberMeCookieName("rememberMeCookie")
                .tokenValiditySeconds(10000);
    }
}
