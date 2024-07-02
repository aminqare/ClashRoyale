package Controller;

import Model.ClashRoyale;
import Model.User;
import View.LoginMenuEnums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginMenuController {

    public static String register(Matcher matcher) {
        String username = matcher.group("username");
        String password = matcher.group("password");

        if(LoginMenuEnums.getMatcher(username, LoginMenuEnums.VALID_USERNAME) == null)
            return "Incorrect format for username!";

        else if(!isPasswordFormatCorrect(password))
            return "Incorrect format for password!";

        else if(ClashRoyale.getUserByUsername(username) != null)
            return "Username already exists!";

        ClashRoyale.addUser(new User(username, password));

        return "User " + username + " created successfully!";
    }

    private static boolean isPasswordFormatCorrect(String password) {
        if(password.length() < 8 ||
            password.length() > 20 ||
            Pattern.compile("\\s").matcher(password).find() ||
            !Pattern.compile("[a-z]").matcher(password).find() ||
            !Pattern.compile("[A-Z]").matcher(password).find() ||
            !Pattern.compile("\\d").matcher(password).find() ||
            Pattern.compile("\\d.+").matcher(password).matches() ||
            !Pattern.compile(LoginMenuEnums.getString(LoginMenuEnums.SPECIAL_CHARACTERS)).matcher(password).find())
            return false;
        return true;
    }

    public static String login(Matcher matcher) {
        String username = matcher.group("username");
        String password = matcher.group("password");

        if(LoginMenuEnums.getMatcher(username, LoginMenuEnums.VALID_USERNAME) == null)
            return "Incorrect format for username!";

        else if(!isPasswordFormatCorrect(password))
            return "Incorrect format for password!";

        User owner = ClashRoyale.getUserByUsername(username);

        if(owner == null)
            return "Username doesn't exist!";

        else if(!owner.getPassword().equals(password))
            return "Password is incorrect!";

        ClashRoyale.setCurrentOwner(owner);

        return username;
    }
}
