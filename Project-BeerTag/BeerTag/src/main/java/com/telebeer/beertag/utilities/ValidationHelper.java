package com.telebeer.beertag.utilities;

public class ValidationHelper {

    public static boolean isStringValid(String stringToValidate) {
        return stringToValidate != null && !stringToValidate.isEmpty();
    }

    public static boolean isStringLengthValid(String str, int min, int max) {
        return str.length() >= min && str.length() <= max;
    }

    public static boolean isNumberInRange(double num, int min, int max) {
        return num >= min && num <= max;
    }

}
