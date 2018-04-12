package com.irenailieva.nutricounter.services.implementations;

import com.irenailieva.nutricounter.entities.Gender;
import com.irenailieva.nutricounter.entities.User;
import com.irenailieva.nutricounter.models.view.ProfileEditModel;
import com.irenailieva.nutricounter.repositories.UserRepository;
import com.irenailieva.nutricounter.services.interfaces.UserService;
import com.irenailieva.nutricounter.util.DateUtil;
import com.irenailieva.nutricounter.util.WebConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}
