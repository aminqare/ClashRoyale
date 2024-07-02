package View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileMenuEnums {
    BACK ("back"),
    CHANGE_PASSWORD ("change password old password (?<oldPassword>.+) new password (?<newPassword>.+)"),
    SPECIAL_CHARACTERS ("[\\!\\@\\#\\$\\%\\^\\&\\*]"),
    INFO ("Info"),
    REMOVE_FROM_BATTLE_DECK ("remove from battle deck (?<cardName>.+)"),
    VALID_CARD_NAMES ("(Fireball)|(Archer)|(Dragon)|(Wizard)"),
    ADD_TO_BATTLE_DECK ("add to battle deck (?<cardName>.+)"),
    SHOW_BATTLE_DECK ("show battle deck"),
    SHOW_CURRENT_MENU ("show current menu");

    private String string;

    ProfileMenuEnums(String string) {
        this.string = string;
    }

    public static String getString(ProfileMenuEnums profileMenuEnums) {
        return profileMenuEnums.string;
    }

    public static Matcher getMatcher(String input, ProfileMenuEnums profileMenuEnums) {
        Matcher matcher = Pattern.compile(profileMenuEnums.string).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
