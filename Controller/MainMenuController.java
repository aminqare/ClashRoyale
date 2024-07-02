package Controller;

import Model.ClashRoyale;
import Model.User;
import View.MainMenuEnums;
import java.util.ArrayList;
import java.util.regex.Matcher;

public class MainMenuController {
    private static User owner;

    public static void setOwner() {
        owner = ClashRoyale.getCurrentOwner();
    }

    public static User getOwner() {
        return owner;
    }

    public static void showListOfUsers() {
        int index = 1;

        for(User user : ClashRoyale.getUsers()) {
            System.out.println("user " + index + ": " + user.getUsername());
            index++;
        }
    }

    public static void showScoreboard() {
        ArrayList<User> sortedListOfUsers = ClashRoyale.getSortedListOfUsers();

        for (int i = 0 ; i < Math.min(5, sortedListOfUsers.size()) ; i++)
            System.out.println((i + 1) + "- username: " + sortedListOfUsers.get(i).getUsername()
            + " level: " + sortedListOfUsers.get(i).getLevel()
            + " experience: " + sortedListOfUsers.get(i).getExperience());
    }

    public static String startNewGame(Matcher matcher) {
        String username = matcher.group("username");
        int turnsCount = Integer.parseInt(matcher.group("turnsCount"));

        if(turnsCount < 5 || turnsCount > 30)
            return "Invalid turns count!";

        else if(MainMenuEnums.getMatcher(username, MainMenuEnums.VALID_USERNAME) == null)
            return "Incorrect format for username!";

        else if(ClashRoyale.getUserByUsername(username) == null)
            return "Username doesn't exist!";

        ClashRoyale.setCurrentOpponent(ClashRoyale.getUserByUsername(username));

        return username;
    }
}
