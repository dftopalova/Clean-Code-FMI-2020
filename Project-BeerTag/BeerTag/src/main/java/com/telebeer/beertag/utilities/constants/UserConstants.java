package com.telebeer.beertag.utilities.constants;

public class UserConstants {

    //constraints
    public static final int FIRST_NAME_MIN_LENGTH = 3;
    public static final int FIRST_NAME_MAX_LENGTH = 20;
    public static final String FIRST_NAME_LENGTH_CONSTRAINT_MESSAGE = "First name must be between 3 and 20 symbols.";
    public static final int LAST_NAME_MIN_LENGTH = 3;
    public static final int LAST_NAME_MAX_LENGTH = 20;
    public static final String LAST_NAME_CONSTRAINT_MESSAGE = "Last name must be between 3 and 20 symbols.";
    public static final int USERNAME_MIN_LENGTH = 3;
    public static final int USERNAME_MAX_LENGTH = 20;
    public static final String USERNAME_LENGTH_CONSTRAINT_MESSAGE = "Username must be between 3 and 20 symbols.";
    public static final int PASSWORD_MIN_LENGTH = 3;
    public static final String PASSWORD_LENGTH_CONSTRAINT_MESSAGE = "Password must be at least 3 symbols.";

    // messages
    public static final String USER_SUCCESSFULLY_CREATED = "{\"message\": \"User with username - %s is successfully created\"} ";
    public static final String USER_SUCCESSFULLY_UPDATED = "{\"message\": \"User with username - %s is successfully updated\"} ";
    public static final String USER_WITH_USERNAME_EXISTS = "{\"message\": \"User with such a username - %s already exists\"}";

    // error messages
    public static final String BEER_NOT_FOUND = "This beer does not exist in the list!";
    public static final String USER_WITH_ID_DOES_NOT_EXIST = "User with id %d does not exist";
    public static final String USERS_WITH_FIRST_NAME_DOES_NOT_EXIST = "Users with first name %s does not exist";
    public static final String USERS_WITH_LAST_NAME_DOES_NOT_EXIST = "Users with last name %s does not exist";
    public static final String NO_ENTERED_FILTER_CRITERIA = "There is no entered any filter criteria";
    public static final String NO_RESULTS_RETURNED = "No results returned";
    public static final String NO_SUCH_SORTING_CRITERIA = "No such sorting criteria";
    public static final String EMPTY_USERNAME_OR_PASSWORD = "Username/password can't be empty!";
    public static final String USER_WITH_SAME_USERNAME_ALREADY_EXISTS = "User with same username already exists!";

}
