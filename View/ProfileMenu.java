package View;

import Controller.ProfileMenuController;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileMenu {

    public static void run(Scanner scanner) {
        String command;
        Matcher matcher;

        ProfileMenuController.setOwner();

        while (true) {
            command = scanner.nextLine();

            if (ProfileMenuEnums.getMatcher(command, ProfileMenuEnums.BACK) != null) {
                System.out.println("Entered main menu!");
                return;
            } else if ((matcher = ProfileMenuEnums.getMatcher(command, ProfileMenuEnums.CHANGE_PASSWORD)) != null)
                System.out.println(ProfileMenuController.changePassword(matcher));

            else if (ProfileMenuEnums.getMatcher(command, ProfileMenuEnums.INFO) != null)
                ProfileMenuController.showInfo();

            else if ((matcher = ProfileMenuEnums.getMatcher(command, ProfileMenuEnums.REMOVE_FROM_BATTLE_DECK)) != null)
                System.out.println(ProfileMenuController.removeFromBattleDeck(matcher));

            else if ((matcher = ProfileMenuEnums.getMatcher(command, ProfileMenuEnums.ADD_TO_BATTLE_DECK)) != null)
                System.out.println(ProfileMenuController.addToBattleDeck(matcher));

            else if (ProfileMenuEnums.getMatcher(command, ProfileMenuEnums.SHOW_BATTLE_DECK) != null)
                ProfileMenuController.showBattleDeck();

            else if (ProfileMenuEnums.getMatcher(command, ProfileMenuEnums.SHOW_CURRENT_MENU) != null)
                System.out.println("Profile Menu");

            else
                System.out.println("Invalid command!");
        }
    }
}
