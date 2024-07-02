package Controller;

import Model.ClashRoyale;
import Model.User;
import View.ProfileMenuEnums;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileMenuController {
    private static User owner;

    public static void setOwner() {
        owner = ClashRoyale.getCurrentOwner();
    }

    public static User getOwner() {
        return owner;
    }

    public static String changePassword(Matcher matcher) {
        String oldPassword = matcher.group("oldPassword");
        String newPassword = matcher.group("newPassword");

        if(!owner.getPassword().equals(oldPassword))
            return "Incorrect password!";

        else if(!isPasswordFormatCorrect(newPassword))
            return "Incorrect format for new password!";

        owner.setPassword(newPassword);

        return "Password changed successfully!";
    }

    private static boolean isPasswordFormatCorrect(String password) {
        if(password.length() < 8 ||
                password.length() > 20 ||
                Pattern.compile("\\s").matcher(password).find() ||
                !Pattern.compile("[a-z]").matcher(password).find() ||
                !Pattern.compile("[A-Z]").matcher(password).find() ||
                !Pattern.compile("\\d").matcher(password).find() ||
                Pattern.compile("\\d.+").matcher(password).matches() ||
                !Pattern.compile(ProfileMenuEnums.getString(ProfileMenuEnums.SPECIAL_CHARACTERS)).matcher(password).find())
            return false;
        return true;
    }

    public static void showInfo() {
        System.out.println("username: " + owner.getUsername());
        System.out.println("password: " + owner.getPassword());
        System.out.println("level: " + owner.getLevel());
        System.out.println("experience: " + owner.getExperience());
        System.out.println("gold: " + owner.getGold());

        ArrayList<User> sortedListOfUsers = ClashRoyale.getSortedListOfUsers();

        int rank = sortedListOfUsers.indexOf(owner) + 1;

        System.out.println("rank: " + rank);
    }

    public static String removeFromBattleDeck(Matcher matcher) {
        String cardName = matcher.group("cardName");

        if(ProfileMenuEnums.getMatcher(cardName, ProfileMenuEnums.VALID_CARD_NAMES) == null)
            return "Invalid card name!";

        else if(!owner.isCardInBattleDeck(cardName))
            return "This card isn't in your battle deck!";

        else if(owner.getBattleDeck().size() == 1)
            return "Invalid action: your battle deck will be empty!";

        owner.removeCardFromDeck(cardName);

        return "Card " + cardName + " removed successfully!";
    }

    public static String addToBattleDeck(Matcher matcher) {
        String cardName = matcher.group("cardName");

        if(ProfileMenuEnums.getMatcher(cardName, ProfileMenuEnums.VALID_CARD_NAMES) == null)
            return "Invalid card name!";

        else if(!owner.isCardInUserCards(cardName))
            return "You don't have this card!";

        else if(owner.isCardInBattleDeck(cardName))
            return "This card is already in your battle deck!";

        else if(owner.getBattleDeck().size() == 3)
            return "Invalid action: your battle deck is full!";

        owner.addCardToBattleDeck(cardName);

        return "Card " + cardName + " added successfully!";
    }

    public static void showBattleDeck() {
        for(String cardName : owner.getSortedBattleDeck())
            System.out.println(cardName);
    }
}
