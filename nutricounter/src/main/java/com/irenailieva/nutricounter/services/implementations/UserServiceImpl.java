package com.irenailieva.nutricounter.services.implementations;

import com.irenailieva.nutricounter.entities.Gender;
import com.irenailieva.nutricounter.entities.Role;
import com.irenailieva.nutricounter.entities.User;
import com.irenailieva.nutricounter.models.view.ProfileEditModel;
import com.irenailieva.nutricounter.models.view.UserViewModel;
import com.irenailieva.nutricounter.repositories.UserRepository;
import com.irenailieva.nutricounter.services.interfaces.RoleService;
import com.irenailieva.nutricounter.services.interfaces.UserService;
import com.irenailieva.nutricounter.util.DateUtil;
import com.irenailieva.nutricounter.util.UtilModelMapper;
import com.irenailieva.nutricounter.util.WebConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public User findById(long id) {
        return this.userRepository.findFirstById(id);
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findFirstByUsername(username);
    }

    @Override
    public UserViewModel findUserProfile(String username) {
        User user = this.userRepository.findFirstByUsername(username);
        UserViewModel userViewModel = UtilModelMapper.getInstance().map(user, UserViewModel.class);

        List<String> roleNames = new ArrayList<>();
        for (Role role : user.getRoles()) {
            roleNames.add(role.getName());
        }

        userViewModel.setRoles(roleNames);

        return userViewModel;
    }

    @Override
    public List<String> findUsernames(String username) {
        List<String> usernames = this.userRepository.findUsernames(username);
        return usernames;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findFirstByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(WebConstants.INCORRECT_USER_LOGIN_DETAILS);
        }

        Set<SimpleGrantedAuthority> roles = user.getRoles()
                .stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.getName()))
                .collect(Collectors.toSet());

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            roles
        );

        return userDetails;
    }

    @Override
    public void editProfile(String username, ProfileEditModel profileEditModel) {
        User user = this.userRepository.findFirstByUsername(username);

        user.setGender(Gender.getEnumValue(profileEditModel.getGender()));
        user.setDateOfBirth(DateUtil.convertToLocalDate(profileEditModel.getDate()));
        user.setHeight(Integer.parseInt(profileEditModel.getHeight()));
        user.setWeight(Integer.parseInt(profileEditModel.getWeight()));

        this.userRepository.saveAndFlush(user);
    }

    @Override
    public boolean userIsAdmin(String username) {
        User user = this.userRepository.findFirstByUsername(username);
        Role adminRole = this.roleService.findByName(WebConstants.ADMIN_ROLE);
        return user.getRoles().contains(adminRole);
    }

    @Override
    public void grantAdminPrivileges(UserViewModel userViewModel) {
        Role adminRole = this.roleService.findByName(WebConstants.ADMIN_ROLE);
        User user = this.userRepository.findFirstByUsername(userViewModel.getUsername());
        user.addRole(adminRole);
        this.userRepository.saveAndFlush(user);
        userViewModel.getRoles().add(WebConstants.ADMIN_ROLE);
    }

    @Override
    public void revokeAdminPrivileges(UserViewModel userViewModel) {
        Role adminRole = this.roleService.findByName(WebConstants.ADMIN_ROLE);
        User user = this.userRepository.findFirstByUsername(userViewModel.getUsername());
        user.removeRole(adminRole);
        this.userRepository.saveAndFlush(user);
        userViewModel.getRoles().remove(WebConstants.ADMIN_ROLE);
    }
}
