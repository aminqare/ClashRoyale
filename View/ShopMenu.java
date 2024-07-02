package View;

import Controller.ShopMenuController;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ShopMenu {

    public static void run(Scanner scanner) {
        String command;
        Matcher matcher;

        ShopMenuController.setOwner();

        while (true) {
            command = scanner.nextLine();

            if(ShopMenuEnums.getMatcher(command, ShopMenuEnums.BACK) != null) {
                System.out.println("Entered main menu!");
                return;
            }

            else if((matcher = ShopMenuEnums.getMatcher(command, ShopMenuEnums.BUY_CARD)) != null)
                System.out.println(ShopMenuController.buyCard(matcher));

            else if((matcher = ShopMenuEnums.getMatcher(command, ShopMenuEnums.SELL_CARD)) != null)
                System.out.println(ShopMenuController.sellCard(matcher));

            else if(ShopMenuEnums.getMatcher(command, ShopMenuEnums.SHOW_CURRENT_MENU) != null)
                System.out.println("Shop Menu");

            else
                System.out.println("Invalid command!");
        }
    }
}
