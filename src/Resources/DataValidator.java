package Resources;

import java.util.regex.Pattern;

public class DataValidator {
    public static boolean isInteger(String value) {
        if(value == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    public static boolean isYesOrNo(String value) {
        if(value == null) {
            return false;
        } else if(isInteger(value)){
            return false;
        } else if(value.length() > 3) {
            return false;
        } else if(value.equalsIgnoreCase("y") || value.equalsIgnoreCase("yes") || value.equalsIgnoreCase("n") || value.equalsIgnoreCase("no")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmailValid(String email) {
        String pattern = "^(.+)@(\\S+)$";
        return Pattern.compile(pattern)
                .matcher(email)
                .matches();
    }
}
