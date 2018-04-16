package com.irenailieva.nutricounter.services.interfaces;

import com.irenailieva.nutricounter.entities.User;
import com.irenailieva.nutricounter.models.view.ProfileEditModel;
import com.irenailieva.nutricounter.models.view.UserViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User findById(long id);

    User findByUsername(String username);

    UserViewModel findUserProfile(String username);

    List<String> findUsernames(String username);

    void editProfile(String username, ProfileEditModel profileEditModel);

    boolean userIsAdmin(String username);

    void grantAdminPrivileges(UserViewModel userViewModel);

    void revokeAdminPrivileges(UserViewModel userViewModel);
}
