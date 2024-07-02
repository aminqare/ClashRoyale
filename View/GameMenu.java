package View;

import Controller.Game;
import Model.GamePlayer;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu {

    public static void run(Scanner scanner, GamePlayer player, Game game) {
        String command;
        Matcher matcher;

        while(true) {
            command = scanner.nextLine();

            if(GameMenuEnums.getMatcher(command, GameMenuEnums.NEXT_TURN) != null) return;

            else if(GameMenuEnums.getMatcher(command, GameMenuEnums.SHOW_OPPONENT_HITPOINTS) != null)
                game.showOpponentHitpoints(player);

            else if((matcher = GameMenuEnums.getMatcher(command, GameMenuEnums.SHOW_LINE_INFO)) != null)
                game.showLineInfo(player, matcher);

            else if(GameMenuEnums.getMatcher(command, GameMenuEnums.CARDS_TO_PLAY_COUNT) != null)
                System.out.println(game.showCardsToPlayCount(player));

            else if(GameMenuEnums.getMatcher(command, GameMenuEnums.TROOPS_TO_PLAY_COUNT) != null)
                System.out.println(game.showTroopsToPlayCount(player));

            else if((matcher = GameMenuEnums.getMatcher(command, GameMenuEnums.MOVE_TROOP)) != null)
                System.out.println(game.moveTroop(player, matcher));

            else if((matcher = GameMenuEnums.getMatcher(command, GameMenuEnums.DEPLOY_TROOP)) != null)
                System.out.println(game.deployTroop(player, matcher));

            else if((matcher = GameMenuEnums.getMatcher(command, GameMenuEnums.DEPLOY_FIREBALL_SPELL)) != null)
                System.out.println(game.deployFireballSpell(player, matcher));

            else if(GameMenuEnums.getMatcher(command, GameMenuEnums.SHOW_CURRENT_MENU) != null)
                System.out.println("Game Menu");

            else
                System.out.println("Invalid command!");
        }
    }
}
