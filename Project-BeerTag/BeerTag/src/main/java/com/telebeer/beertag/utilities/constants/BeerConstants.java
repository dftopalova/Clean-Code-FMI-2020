package com.telebeer.beertag.utilities.constants;

public class BeerConstants {

    //constraints
    public static final int MIN_NAME_LENGTH = 3;
    public static final int MAX_NAME_LENGTH = 15;
    public static final String BEER_NAME_CONSTRAINT_MESSAGE = "Beer name must be between 3 and 15 symbols!";
    public static final int MIN_ALCOHOL_BY_VOLUME_VALUE = 0;
    public static final int MAX_ALCOHOL_BY_VOLUME_VALUE = 60;
    public static final String ALCOHOL_BY_VOLUME_INVALID_MESSAGE = "Alcohol by volume must be between 0 and 60 %!";

    // messages
    public static final String BEER_SUCCESSFULLY_UPDATED =
            "{\"message\": \"Beer- %s successfully updated\"}";
    public static final String BEER_WITH_SAME_NAME_AND_STYLE_EXISTS =
            "{\"message\": \"Beer with name - %s and style %s already exists\"}";
    public static final String STYLE_SUCCESSFULLY_CREATED =
            "{\"message\": \"Beer style- %s successfully created\"}";

    // error messages
    public static final String ERR_NO_SUCH_SORTING_CRITERIA = "No such sorting criteria!";

}
