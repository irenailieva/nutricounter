package com.irenailieva.nutricounter.models;

import com.irenailieva.nutricounter.util.WebConstants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@Getter
@Setter
public class UserSignUpModel {

    @NotNull
    @NotEmpty(message = WebConstants.EMPTY_EMAIL_MESSAGE)
    @Email(message = WebConstants.INVALID_EMAIL_MESSAGE)
    private String email;

    @NotNull
    @NotEmpty(message = WebConstants.EMPTY_USERNAME_MESSAGE)
    @Pattern(regexp = "[0-9a-zA-Z.]+", message = WebConstants.INVALID_USERNAME_MESSAGE)
    private String username;

    @NotNull
    @NotEmpty(message = WebConstants.EMPTY_PASSWORD_MESSAGE)
    private String password;

    @NotNull
    @NotEmpty(message = WebConstants.EMPTY_CONFIRM_PASSWORD_MESSAGE)
    private String confirmPassword;

    @NotNull
    @NotEmpty(message = WebConstants.EMPTY_GENDER_MESSAGE)
    private String gender;

    @NotNull
    @Pattern(regexp = "[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}", message = WebConstants.INVALID_DATE_FORMAT_MESSAGE)
    private String date;

    @Pattern(regexp = "[0-9]+", message = WebConstants.INVALID_HEIGHT_MESSAGE)
    private String height;

    @Pattern(regexp = "[0-9]+", message = WebConstants.INVALID_WEIGHT_MESSAGE)
    private String weight;
}

