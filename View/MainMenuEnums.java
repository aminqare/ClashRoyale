package View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuEnums {
    LIST_OF_USERS ("list of users"),
    LOGOUT ("logout"),
    SHOW_SCOREBOARD ("scoreboard"),
    SHOW_CURRENT_MENU ("show current menu"),
    ENTER_PROFILE_MENU ("profile menu"),
    ENTER_SHOP_MENU ("shop menu"),
    START_NEW_GAME ("start game turns count (?<turnsCount>-?\\d+) username (?<username>.+)"),
    VALID_USERNAME ("[a-zA-Z]+");

    private String string;

    MainMenuEnums(String string) {
        this.string = string;
    }

    public static String getString(MainMenuEnums mainMenuEnums) {
        return mainMenuEnums.string;
    }

    public static Matcher getMatcher(String input, MainMenuEnums mainMenuEnums) {
        Matcher matcher = Pattern.compile(mainMenuEnums.string).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
