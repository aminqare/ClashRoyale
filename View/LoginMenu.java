package View;

import Controller.LoginMenuController;

import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu {

    public static void run(Scanner scanner) {
        String command;
        Matcher matcher;

        while(true) {
            command = scanner.nextLine();

            if(LoginMenuEnums.getMatcher(command, LoginMenuEnums.EXIT) != null) return;

            else if((matcher = LoginMenuEnums.getMatcher(command, LoginMenuEnums.REGISTER)) != null)
                System.out.println(LoginMenuController.register(matcher));

            else if((matcher = LoginMenuEnums.getMatcher(command, LoginMenuEnums.LOGIN)) != null) {
                String result = LoginMenuController.login(matcher);

                if(result.equals(matcher.group("username"))) {
                    System.out.println("User " + result + " logged in!");
                    MainMenu.run(scanner);
                }
                else
                    System.out.println(result);
            }

            else if(LoginMenuEnums.getMatcher(command, LoginMenuEnums.SHOW_CURRENT_MENU) != null)
                System.out.println("Register/Login Menu");

            else
                System.out.println("Invalid command!");
        }
    }
}
