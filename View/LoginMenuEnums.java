package View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginMenuEnums {
    REGISTER ("register username (?<username>.+) password (?<password>.+)"),
    VALID_USERNAME ("[a-zA-Z]+"),
    SPECIAL_CHARACTERS ("[\\!\\@\\#\\$\\%\\^\\&\\*]"),
    LOGIN ("login username (?<username>.+) password (?<password>.+)"),
    EXIT ("Exit"),
    SHOW_CURRENT_MENU ("show current menu");

    private String string;

    LoginMenuEnums(String string) {
        this.string = string;
    }

    public static String getString(LoginMenuEnums loginMenuEnums) {
        return loginMenuEnums.string;
    }

    public static Matcher getMatcher(String input, LoginMenuEnums loginMenuEnums) {
        Matcher matcher = Pattern.compile(loginMenuEnums.string).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
