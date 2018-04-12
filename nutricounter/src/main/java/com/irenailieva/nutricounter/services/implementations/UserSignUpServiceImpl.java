package com.irenailieva.nutricounter.services.implementations;

import com.irenailieva.nutricounter.entities.Gender;
import com.irenailieva.nutricounter.entities.Role;
import com.irenailieva.nutricounter.entities.User;
import com.irenailieva.nutricounter.models.UserSignUpModel;
import com.irenailieva.nutricounter.repositories.UserRepository;
import com.irenailieva.nutricounter.services.interfaces.DailyIntakeService;
import com.irenailieva.nutricounter.services.interfaces.RoleService;
import com.irenailieva.nutricounter.services.interfaces.UserSignUpService;
import com.irenailieva.nutricounter.util.DateUtil;
import com.irenailieva.nutricounter.util.UtilModelMapper;
import com.irenailieva.nutricounter.util.WebConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.time.DateTimeException;
import java.util.List;

@Service
public class UserSignUpServiceImpl implements UserSignUpService {

    //FIELDS
    private String errorMessage;
    private String errorField;

    //REPOSITORIES
    private final UserRepository userRepository;

    //SERVICES
    private final DailyIntakeService dailyIntakeService;
    private final RoleService roleService;

    //PASSWORD ENCODER
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserSignUpServiceImpl(UserRepository userRepository, DailyIntakeService dailyIntakeService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.dailyIntakeService = dailyIntakeService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean validUserSignUpDetails(UserSignUpModel userSignUpModel, BindingResult bindingResult) {

        //CHECKING IF PROVIDED DATE IS VALID
        String dateAsString = userSignUpModel.getDate();
        try {
            DateUtil.convertToLocalDate(dateAsString);
        }
        catch (DateTimeException e) {
            this.errorMessage = WebConstants.INVALID_DATE_MESSAGE;
            this.errorField = "date";
            bindingResult.rejectValue(errorField, errorField, errorMessage);
            return false;
        }

        //CHECKING IF PASSWORD AND CONFIRM PASSWORD FIELDS MATCH
        String password = userSignUpModel.getPassword();
        String confirmPassword = userSignUpModel.getConfirmPassword();
        if (!password.equals(confirmPassword)) {
            this.errorMessage = WebConstants.CONFIRM_PASSWORD_MISMATCH_MESSAGE;
            this.errorField = "confirmPassword";
            bindingResult.rejectValue(errorField, errorField, errorMessage);
            return false;
        }

        //CHECKING IF PROVIDED EMAIL ADDRESS IS ALREADY IN USE
        List<String> allEmailAddresses = this.userRepository.findAllUserEmails();
        if (allEmailAddresses.contains(userSignUpModel.getEmail())) {
            this.errorMessage = WebConstants.ALREADY_EXISTING_EMAIL_MESSAGE;
            this.errorField = "email";
            bindingResult.rejectValue(errorField, errorField, errorMessage);
            return false;
        }

        //CHECKING IF PROVIDED USERNAME ALREADY EXISTS
        List<String> allUsernames = this.userRepository.findAllUsernames();
        if (allUsernames.contains(userSignUpModel.getUsername())) {
            this.errorMessage = WebConstants.ALREADY_EXISTING_USERNAME_MESSAGE;
            this.errorField = "username";
            bindingResult.rejectValue(errorField, errorField, errorMessage);
            return false;
        }

        return true;
    }

    @Override
    public void createUser(UserSignUpModel userSignUpModel) {

        User user = UtilModelMapper.getInstance().map(userSignUpModel, User.class);
        user.setPassword(this.passwordEncoder.encode(userSignUpModel.getPassword()));
        user.setGender(Gender.getEnumValue(userSignUpModel.getGender()));
        user.setDateOfBirth(DateUtil.convertToLocalDate(userSignUpModel.getDate()));
        this.setUserRoles(user);

        this.userRepository.saveAndFlush(user);
        this.dailyIntakeService.createDailyIntakeFor(user);
    }

    @Override
    public void setUserRoles(User user) {

        long userCount = this.userRepository.count();

        if (userCount == 0L) {
            Role chiefAdminRole = this.roleService.findByName(WebConstants.CHIEF_ADMIN_ROLE);
            Role adminRole = this.roleService.findByName(WebConstants.ADMIN_ROLE);
            user.addRole(chiefAdminRole);
            user.addRole(adminRole);
        }
        Role role = this.roleService.findByName(WebConstants.USER_ROLE);
        user.addRole(role);
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }

    @Override
    public String getErrorField() {
        return this.errorField;
    }
}
