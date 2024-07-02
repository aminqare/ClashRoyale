package View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuEnums {
    NEXT_TURN ("next turn"),
    SHOW_OPPONENT_HITPOINTS ("show the hitpoints left of my opponent"),
    SHOW_LINE_INFO ("show line info (?<lineDirection>.+)"),
    CARDS_TO_PLAY_COUNT ("number of cards to play"),
    TROOPS_TO_PLAY_COUNT ("number of moves left"),
    MOVE_TROOP ("move troop in line (?<lineDirection>.+) and row (?<rowNumber>-?\\d+) (?<direction>.+)"),
    VALID_LINE_DIRECTIONS ("(middle)|(left)|(right)"),
    VALID_TROOP_NAMES ("(Wizard)|(Archer)|(Dragon)"),
    DEPLOY_TROOP ("deploy troop (?<troopName>.+) in line (?<lineDirection>.+) and row (?<rowNumber>-?\\d+)"),
    SHOW_CURRENT_MENU ("show current menu"),
    DEPLOY_FIREBALL_SPELL ("deploy spell Fireball in line (?<lineDirection>.+)");

    private String string;

    GameMenuEnums(String string) {
        this.string = string;
    }

    public static String getString(GameMenuEnums gameMenuEnums) {
        return gameMenuEnums.string;
    }

    public static Matcher getMatcher(String input, GameMenuEnums gameMenuEnums) {
        Matcher matcher = Pattern.compile(gameMenuEnums.string).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
