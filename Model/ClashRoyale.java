package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ClashRoyale {
    private static ArrayList<User> users = new ArrayList<>();
    private static User currentOwner;
    private static User currentOpponent;
    private static final HashMap<String, Integer> priceOfCard = new HashMap<String, Integer>() {{

        put("Archer", 80);
        put("Dragon", 160);
        put("Wizard", 140);
        put("Fireball", 80);

    }};

    public static int getPriceOfCard(String cardName) {
        return priceOfCard.get(cardName);
    }

    public static User getCurrentOpponent() {
        return currentOpponent;
    }

    public static void setCurrentOpponent(User currentOpponent) {
        ClashRoyale.currentOpponent = currentOpponent;
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static User getUserByUsername(String username) {
        for(User user : users)
            if(user.getUsername().equals(username))
                return user;
        return null;
    }

    public static User getCurrentOwner() {
        return currentOwner;
    }

    public static void setCurrentOwner(User currentOwner) {
        ClashRoyale.currentOwner = currentOwner;
    }

    public static ArrayList<User> getSortedListOfUsers() {
        ArrayList<User> tempList = new ArrayList<>();
        tempList.addAll(users);

        for(int i = 0 ; i < tempList.size() - 1 ; i++)
            for(int j = 0 ; j < tempList.size() - 1 - i ; j++)
                if(!userComparator(tempList.get(j), tempList.get(j + 1)))
                    Collections.swap(tempList, j, j+1);

        return tempList;
    }

    // if user1 has a higher rank returns true
    public static boolean userComparator(User user1, User user2) {

        if(user1.getLevel() != user2.getLevel())
            return user1.getLevel() > user2.getLevel();

        else if(user1.getExperience() != user2.getExperience())
            return user1.getExperience() > user2.getExperience();

        else
            return user2.getUsername().compareTo(user1.getUsername()) > 0;
    }
}
