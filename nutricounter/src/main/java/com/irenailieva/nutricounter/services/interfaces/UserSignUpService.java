package com.irenailieva.nutricounter.services.interfaces;

import com.irenailieva.nutricounter.entities.User;
import com.irenailieva.nutricounter.models.service.UserSignUpModel;
import org.springframework.validation.BindingResult;

public interface UserSignUpService {
    boolean validUserSignUpDetails(UserSignUpModel userSignUpModel, BindingResult bindingResult);
    User createUser(UserSignUpModel userSignUpModel);

    User setUserRoles(User user);

    String getErrorMessage();
    String getErrorField();
}
