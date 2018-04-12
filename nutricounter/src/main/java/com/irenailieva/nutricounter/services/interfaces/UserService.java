package com.irenailieva.nutricounter.services.interfaces;

import com.irenailieva.nutricounter.entities.User;
import com.irenailieva.nutricounter.models.view.ProfileEditModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findById(long id);

    User findByUsername(String username);

    void editProfile(String username, ProfileEditModel profileEditModel);
}
