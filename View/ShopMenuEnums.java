package View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ShopMenuEnums {
    BACK ("back"),
    BUY_CARD ("buy card (?<cardName>.+)"),
    VALID_CARD_NAMES ("(Fireball)|(Archer)|(Dragon)|(Wizard)"),
    SELL_CARD ("sell card (?<cardName>.+)"),
    SHOW_CURRENT_MENU ("show current menu");

    private String string;

    ShopMenuEnums(String string) {
        this.string = string;
    }

    public static String getString(ShopMenuEnums shopMenuEnums) {
        return shopMenuEnums.string;
    }

    public static Matcher getMatcher(String input, ShopMenuEnums shopMenuEnums) {
        Matcher matcher = Pattern.compile(shopMenuEnums.string).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
