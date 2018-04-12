package com.irenailieva.nutricounter.services.interfaces;

import com.irenailieva.nutricounter.entities.User;
import com.irenailieva.nutricounter.models.UserSignUpModel;
import org.springframework.validation.BindingResult;

public interface UserSignUpService {
    boolean validUserSignUpDetails(UserSignUpModel userSignUpModel, BindingResult bindingResult);
    void createUser(UserSignUpModel userSignUpModel);

    void setUserRoles(User user);

    String getErrorMessage();
    String getErrorField();
}
