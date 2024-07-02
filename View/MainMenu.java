package View;

import Controller.Game;
import Controller.MainMenuController;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu {

    public static void run(Scanner scanner) {
        String command;
        Matcher matcher;

        MainMenuController.setOwner();

        while (true) {
            command = scanner.nextLine();

            if(MainMenuEnums.getMatcher(command, MainMenuEnums.LOGOUT) != null) {
                System.out.println("User " + MainMenuController.getOwner().getUsername() + " logged out successfully!");
                return;
            }

            else if(MainMenuEnums.getMatcher(command, MainMenuEnums.LIST_OF_USERS) != null)
                MainMenuController.showListOfUsers();

            else if(MainMenuEnums.getMatcher(command, MainMenuEnums.SHOW_SCOREBOARD) != null)
                MainMenuController.showScoreboard();

            else if(MainMenuEnums.getMatcher(command, MainMenuEnums.ENTER_PROFILE_MENU) != null) {
                System.out.println("Entered profile menu!");
                ProfileMenu.run(scanner);
            }

            else if(MainMenuEnums.getMatcher(command, MainMenuEnums.ENTER_SHOP_MENU) != null) {
                System.out.println("Entered shop menu!");
                ShopMenu.run(scanner);
            }

            else if((matcher = MainMenuEnums.getMatcher(command, MainMenuEnums.START_NEW_GAME)) != null) {
                String result = MainMenuController.startNewGame(matcher);

                if(matcher.group("username").equals(result)) {
                    System.out.println("Battle started with user " + result);
                    new Game(Integer.parseInt(matcher.group("turnsCount"))).run(scanner);
                }
                else
                    System.out.println(result);
            }

            else if(MainMenuEnums.getMatcher(command, MainMenuEnums.SHOW_CURRENT_MENU) != null)
                System.out.println("Main Menu");

            else
                System.out.println("Invalid command!");
        }
    }
}
