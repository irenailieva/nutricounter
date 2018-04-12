package com.irenailieva.nutricounter.models;

import com.irenailieva.nutricounter.util.WebConstants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class UserLoginModel {

    @NotNull
    @NotEmpty(message = WebConstants.EMPTY_USERNAME_MESSAGE)
    private String username;

    @NotNull
    @NotEmpty(message = WebConstants.EMPTY_PASSWORD_MESSAGE)
    private String password;
}

