package com.irenailieva.nutricounter.util;

public class WebConstants {

    //Validation error messages
    public static final String EMPTY_EMAIL_MESSAGE = "Email cannot be empty.";
    public static final String INVALID_EMAIL_MESSAGE = "Please enter a valid email address.";

    public static final String INCORRECT_USER_LOGIN_DETAILS = "Incorrect username or password.";

    public static final String EMPTY_USERNAME_MESSAGE = "Username cannot be empty.";
    public static final String INVALID_USERNAME_MESSAGE = "Username can only contain letters, digits and '.'";
    public static final String EMPTY_PASSWORD_MESSAGE = "Password cannot be empty.";
    public static final String EMPTY_CONFIRM_PASSWORD_MESSAGE = "Confirm password cannot be empty.";

    public static final String EMPTY_GENDER_MESSAGE = "Sex cannot be empty.";

    public static final String INVALID_DATE_FORMAT_MESSAGE = "Date must be in the following format: mm/dd/yyyy";
    public static final String INVALID_DATE_MESSAGE = "Please enter a valid date.";

    public static final String INVALID_HEIGHT_MESSAGE = "Please enter a valid height.";
    public static final String INVALID_WEIGHT_MESSAGE = "Please enter a valid weight.";

    public static final String CONFIRM_PASSWORD_MISMATCH_MESSAGE = "Password didn't match confirm password.";
    public static final String ALREADY_EXISTING_EMAIL_MESSAGE = "There's already an account using this email address.";
    public static final String ALREADY_EXISTING_USERNAME_MESSAGE = "Sorry, that username is already taken.";

    public static final String EMPTY_FOOD_NAME_MESSAGE = "Food name cannot be empty!";
    public static final String EMPTY_RECIPE_NAME_MESSAGE = "Recipe name cannot be empty!";

    //Regex validation
    public static final String VALID_DATE_REGEX = "[0-9]{1,2}-[0-9]{1,2}-[0-9]{4}";
    public static final String VALID_USERNAME_REGEX = "[0-9a-zA-Z.]+";
    public static final String VALID_HEIGHT_AND_WEIGHT_REGEX = "[0-9]+";

    //Roles
    public static final String USER_ROLE = "USER";
    public static final String ADMIN_ROLE = "ADMIN";
    public static final String CHIEF_ADMIN_ROLE = "CHIEF ADMIN";

    //Attributes
    public static final String SPRING_SECURITY_SAVED_REQUEST_KEY = "SPRING_SECURITY_SAVED_REQUEST";
    public static final String LOGIN_SUCCESS_ALERT_KEY = "displayLoginSuccessAlert";
    public static final String LOGIN_WARNING_ALERT_KEY = "displayAlertAtLogin";
    public static final String LOGGED_OUT_ALERT_KEY = "loggedOutAlert";
}
