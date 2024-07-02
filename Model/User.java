package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class User {
    private String username;
    private String password;
    private int experience;
    private int level;
    private int gold;
    private ArrayList<String> cards;
    private ArrayList<String> battleDeck;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.gold = 80;
        this.experience = 0;
        this.level = 1;
        this.cards = new ArrayList<>(Arrays.asList("Archer", "Fireball"));
        this.battleDeck = new ArrayList<>(Arrays.asList("Archer", "Fireball"));
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getExperience() {
        return experience;
    }

    public int getLevel() {
        return level;
    }

    public int getGold() {
        return gold;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isCardInUserCards(String cardName) {
        return cards.contains(cardName);
    }

    public boolean isCardInBattleDeck(String cardName) {
        return battleDeck.contains(cardName);
    }


    public ArrayList<String > getCards() {
        return cards;
    }

    public ArrayList<String > getBattleDeck() {
        return battleDeck;
    }

    public void removeCardFromDeck(String cardName) {
        battleDeck.remove(cardName);
    }

    public void addCardToBattleDeck(String cardName) {
        battleDeck.add(cardName);
    }

    public void removeCardFromUserCards(String cardName) {
        cards.remove(cardName);
    }

    public void addCardToUserCards(String cardName) {
        cards.add(cardName);
    }

    public ArrayList<String> getSortedBattleDeck() {
        for(int i = 0 ; i < battleDeck.size() - 1 ; i++)
            for(int j = 0 ; j < battleDeck.size() - i - 1 ; j++)
                if(battleDeck.get(j).compareTo(battleDeck.get(j + 1)) > 0)
                    Collections.swap(battleDeck, j, j + 1);

        return battleDeck;
    }

    public void changeGold(int amount) {
        gold += amount;
    }

    public void changeExperience(int amount) {
        experience += amount;
    }

    public void changeLevel(int amount) {
        level += amount;
    }

    public int getExperienceNeededForUpdate() {
        return 150 * level * level;
    }
}
