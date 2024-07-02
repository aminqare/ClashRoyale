package Controller;

import Model.*;
import View.GameMenu;
import View.GameMenuEnums;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class Game {
    private GamePlayer host;
    private GamePlayer guest;
    private int turnsCount;
    private ArrayList<GameCard>[] leftLineCards;
    private ArrayList<GameCard>[] middleLineCards;
    private ArrayList<GameCard>[] rightLineCards;

    public Game(int turnsCount) {
        host = new GamePlayer(ClashRoyale.getCurrentOwner());
        guest = new GamePlayer(ClashRoyale.getCurrentOpponent());
        this.turnsCount = turnsCount;
        leftLineCards = (ArrayList<GameCard>[]) new ArrayList[15];
        for(int i = 0 ; i < 15 ; i++) {
            leftLineCards[i] = new ArrayList<>();
        }

        middleLineCards = (ArrayList<GameCard>[]) new ArrayList[15];
        for(int i = 0 ; i < 15 ; i++) {
            middleLineCards[i] = new ArrayList<>();
        }

        rightLineCards = (ArrayList<GameCard>[]) new ArrayList[15];
        for(int i = 0 ; i < 15 ; i++) {
            rightLineCards[i] = new ArrayList<>();
        }
    }

    public void run(Scanner scanner) {
        for(int turnCounter = 0 ; turnCounter < turnsCount ; turnCounter++) {

            host.setCardsToPlayCount(1);
            guest.setCardsToPlayCount(1);

            host.setTroopsToPlayCount(3);
            guest.setTroopsToPlayCount(3);

            if(turnCounter > 0)
                System.out.println("Player " + host.getUser().getUsername() + " is now playing!");
            GameMenu.run(scanner, host, this);

            System.out.println("Player " + guest.getUser().getUsername() + " is now playing!");
            GameMenu.run(scanner, guest, this);

            applyChanges();
            if(isGameOver())
                break;
        }

        resultOfGame();
    }

    public void applyChanges() {
        applyTroopsFights();

        applyTroopsAndTowersFights();

        checkForTerminatedTroopsAndTowers(host);
        checkForTerminatedTroopsAndTowers(guest);
    }

    public void applyTroopsFights() {
        for(int i = 0 ; i < 15 ; i++) {

            for (Troop hostTroop : host.getMiddleLineTroops()[i])
                for (Troop guestTroop : guest.getMiddleLineTroops()[i]) {
                    if (hostTroop.getDamage() > guestTroop.getDamage())
                        guestTroop.changeHitpoint(guestTroop.getDamage() - hostTroop.getDamage());
                    else
                        hostTroop.changeHitpoint(hostTroop.getDamage() - guestTroop.getDamage());
                }

            for (Troop hostTroop : host.getLeftLineTroops()[i])
                for (Troop guestTroop : guest.getLeftLineTroops()[i]) {
                    if (hostTroop.getDamage() > guestTroop.getDamage())
                        guestTroop.changeHitpoint(guestTroop.getDamage() - hostTroop.getDamage());
                    else
                        hostTroop.changeHitpoint(hostTroop.getDamage() - guestTroop.getDamage());
                }

            for (Troop hostTroop : host.getRightLineTroops()[i])
                for (Troop guestTroop : guest.getRightLineTroops()[i]) {
                    if (hostTroop.getDamage() > guestTroop.getDamage())
                        guestTroop.changeHitpoint(guestTroop.getDamage() - hostTroop.getDamage());
                    else
                        hostTroop.changeHitpoint(hostTroop.getDamage() - guestTroop.getDamage());
                }
        }
    }

    public void applyTroopsAndTowersFights() {

        if(host.getMiddleCastleHitpoint() != -1)
            for (Troop guestTroop : guest.getMiddleLineTroops()[0]) {
                guestTroop.changeHitpoint(-host.getCastleDamage());

                host.changeMiddleCastleHitpoint(-guestTroop.getDamage());
            }

        if(host.getLeftCastleHitpoint() != -1)
            for(Troop guestTroop : guest.getLeftLineTroops()[0]) {
                guestTroop.changeHitpoint(-host.getCastleDamage());

                host.changeLeftCastleHitpoint(-guestTroop.getDamage());
            }

        if(host.getRightCastleHitpoint() != -1)
            for (Troop guestTroop : guest.getRightLineTroops()[0]) {
                guestTroop.changeHitpoint(-host.getCastleDamage());

                host.changeRightCastleHitpoint(-guestTroop.getDamage());
            }

        if(guest.getMiddleCastleHitpoint() != -1)
            for (Troop hostTroop : host.getMiddleLineTroops()[14]) {
                hostTroop.changeHitpoint(-guest.getCastleDamage());

                guest.changeMiddleCastleHitpoint(-hostTroop.getDamage());
            }

        if(guest.getLeftCastleHitpoint() != -1)
            for(Troop hostTroop : host.getLeftLineTroops()[14]) {
                hostTroop.changeHitpoint(-guest.getCastleDamage());

                guest.changeLeftCastleHitpoint(-hostTroop.getDamage());
            }

        if(guest.getRightCastleHitpoint() != -1)
            for (Troop hostTroop : host.getRightLineTroops()[14]) {
                hostTroop.changeHitpoint(-guest.getCastleDamage());

                guest.changeRightCastleHitpoint(-hostTroop.getDamage());
            }
    }

    public void checkForTerminatedTroopsAndTowers(GamePlayer player) {
        if(player.getMiddleCastleHitpoint() < 1)
            player.setMiddleCastleHitpoint(-1);

        if(player.getLeftCastleHitpoint() < 1)
            player.setLeftCastleHitpoint(-1);

        if(player.getRightCastleHitpoint() < 1)
            player.setRightCastleHitpoint(-1);

        for(int i = 0 ; i < 15 ; i++) {

            for(int j = player.getMiddleLineTroops()[i].size() - 1 ; j >= 0 ; j--) {
                Troop troop = player.getMiddleLineTroops()[i].get(j);

                if (troop.getHitpoint() < 1) {
                    player.removeFromMiddleLineTroops(troop, i + 1);
                    middleLineCards[i].remove(troop);
                }
            }

            for(int j = player.getLeftLineTroops()[i].size() - 1 ; j >= 0 ; j--) {
                Troop troop = player.getLeftLineTroops()[i].get(j);

                if (troop.getHitpoint() < 1) {
                    player.removeFromLeftLineTroops(troop, i + 1);
                    leftLineCards[i].remove(troop);
                }
            }

            for(int j = player.getRightLineTroops()[i].size() - 1 ; j >= 0 ; j--) {
                Troop troop = player.getRightLineTroops()[i].get(j);

                if (troop.getHitpoint() < 1) {
                    player.removeFromRightLineTroops(troop, i + 1);
                    rightLineCards[i].remove(troop);
                }
            }
        }
    }

    public boolean isGameOver() {
        return ((host.getMiddleCastleHitpoint() == -1) &&
                (host.getLeftCastleHitpoint() == -1) &&
                (host.getRightCastleHitpoint() == -1)) ||
                ((guest.getMiddleCastleHitpoint() == -1) &&
                        (guest.getLeftCastleHitpoint() == -1) &&
                        (guest.getRightCastleHitpoint() == -1));
    }

    public void showOpponentHitpoints(GamePlayer player) {
        GamePlayer opponent = player.equals(host) ? guest : host;

        System.out.println("middle castle: " + opponent.getMiddleCastleHitpoint());
        System.out.println("left castle: " + opponent.getLeftCastleHitpoint());
        System.out.println("right castle: " + opponent.getRightCastleHitpoint());
    }

    public void showLineInfo(GamePlayer player, Matcher matcher) {
        String lineDirection = matcher.group("lineDirection");

        if(lineDirection.equals("middle")) {
            System.out.println("middle line:");
            for(int i = 0 ; i < 15 ; i++)
                for(GameCard card : middleLineCards[i])
                    System.out.println("row " + (i + 1) +
                            ": " + card.getName() +
                            ": " + card.getPlayer().getUser().getUsername());
        }

        else if(lineDirection.equals("left")) {
            System.out.println("left line:");
            for(int i = 0 ; i < 15 ; i++)
                for(GameCard card : leftLineCards[i])
                    System.out.println("row " + (i + 1) +
                            ": " + card.getName() +
                            ": " + card.getPlayer().getUser().getUsername());
        }

        else if(lineDirection.equals("right")) {
            System.out.println("right line:");
            for(int i = 0 ; i < 15 ; i++)
                for(GameCard card : rightLineCards[i])
                    System.out.println("row " + (i + 1) +
                            ": " + card.getName() +
                            ": " + card.getPlayer().getUser().getUsername());
        }

        else
            System.out.println("Incorrect line direction!");
    }

    public String showCardsToPlayCount (GamePlayer player) {
        return "You can play " + player.getCardsToPlayCount() + " cards more!";
    }

    public String showTroopsToPlayCount(GamePlayer player) {
        return "You have " + player.getTroopsToPlayCount() + " moves left!";
    }

    public String moveTroop(GamePlayer player, Matcher matcher) {
        String lineDirection = matcher.group("lineDirection");
        int oldRowNumber = Integer.parseInt(matcher.group("rowNumber"));
        String direction = matcher.group("direction");
        int newRowNumber;

        if(GameMenuEnums.getMatcher(lineDirection, GameMenuEnums.VALID_LINE_DIRECTIONS) == null)
            return "Incorrect line direction!";

        else if(oldRowNumber < 1 || oldRowNumber > 15)
            return "Invalid row number!";

        if(direction.equals("upward")) newRowNumber = oldRowNumber + 1;

        else if(direction.equals("downward")) newRowNumber = oldRowNumber - 1;

        else
            return "you can only move troops upward or downward!";

        if(player.getTroopsToPlayCount() == 0)
            return "You are out of moves!";

        if(lineDirection.equals("middle")) {
            for(GameCard card : middleLineCards[oldRowNumber - 1])
                if((card instanceof Troop) && card.getPlayer().equals(player)) {

                    if(newRowNumber < 1 || newRowNumber > 15)
                        return "Invalid move!";

                    middleLineCards[oldRowNumber - 1].remove(card);
                    middleLineCards[newRowNumber - 1].add(card);

                    player.removeFromMiddleLineTroops((Troop) card, oldRowNumber);
                    player.addToMiddleLineTroops((Troop) card, newRowNumber);

                    player.changeTroopsToPlayCount(-1);

                    return card.getName() +
                            " moved successfully to row " + newRowNumber +
                            " in line " + lineDirection;
                }
        }

        else if(lineDirection.equals("left")) {
            for (GameCard card : leftLineCards[oldRowNumber - 1])
                if ((card instanceof Troop) && card.getPlayer().equals(player)) {

                    if (newRowNumber < 1 || newRowNumber > 15)
                        return "Invalid move!";

                    leftLineCards[oldRowNumber - 1].remove(card);
                    leftLineCards[newRowNumber - 1].add(card);

                    player.removeFromLeftLineTroops((Troop) card, oldRowNumber);
                    player.addToLeftLineTroops((Troop) card, newRowNumber);

                    player.changeTroopsToPlayCount(-1);

                    return card.getName() +
                            " moved successfully to row " + newRowNumber +
                            " in line " + lineDirection;
                }
        }

        else {
            for (GameCard card : rightLineCards[oldRowNumber - 1])
                if ((card instanceof Troop) && card.getPlayer().equals(player)) {

                    if (newRowNumber < 1 || newRowNumber > 15)
                        return "Invalid move!";

                    rightLineCards[oldRowNumber - 1].remove(card);
                    rightLineCards[newRowNumber - 1].add(card);

                    player.removeFromRightLineTroops((Troop) card, oldRowNumber);
                    player.addToRightLineTroops((Troop) card, newRowNumber);

                    player.changeTroopsToPlayCount(-1);

                    return card.getName() +
                            " moved successfully to row " + newRowNumber +
                            " in line " + lineDirection;
                }
        }

        return "You don't have any troops in this place!";
    }

    public String deployTroop(GamePlayer player, Matcher matcher) {
        String troopName = matcher.group("troopName");
        String lineDirection = matcher.group("lineDirection");
        int rowNumber = Integer.parseInt(matcher.group("rowNumber"));

        if(GameMenuEnums.getMatcher(troopName, GameMenuEnums.VALID_TROOP_NAMES) == null)
            return "Invalid troop name!";

        else if(!player.getUser().isCardInBattleDeck(troopName))
            return "You don't have " + troopName + " card in your battle deck!";

        else if(GameMenuEnums.getMatcher(lineDirection, GameMenuEnums.VALID_LINE_DIRECTIONS) == null)
            return "Incorrect line direction!";

        else if(rowNumber < 1 || rowNumber > 15)
            return "Invalid row number!";

        else if((player.equals(host) && rowNumber > 4) ||
                (player.equals(guest) && rowNumber < 12))
            return "Deploy your troops near your castles!";

        else if(player.getCardsToPlayCount() == 0)
            return "You have deployed a troop or spell this turn!";

        Troop troop = new Troop(player, troopName);

        if(lineDirection.equals("middle")) {
            middleLineCards[rowNumber - 1].add(troop);
            player.addToMiddleLineTroops(troop, rowNumber);
        }

        else if(lineDirection.equals("left")) {
            leftLineCards[rowNumber - 1].add(troop);
            player.addToLeftLineTroops(troop, rowNumber);
        }

        else {
            rightLineCards[rowNumber - 1].add(troop);
            player.addToRightLineTroops(troop, rowNumber);
        }

        player.changeCardsToPlayCount(-1);

        return "You have deployed " + troopName + " successfully!";
    }

    public String deployFireballSpell(GamePlayer player, Matcher matcher) {
        String lineDirection = matcher.group("lineDirection");

        if(GameMenuEnums.getMatcher(lineDirection, GameMenuEnums.VALID_LINE_DIRECTIONS) == null)
            return "Incorrect line direction!";

        else if(!player.getUser().isCardInBattleDeck("Fireball"))
            return "You don't have Fireball card in your battle deck!";

        else if(player.getCardsToPlayCount() == 0)
            return "You have deployed a troop or spell this turn!";

        GamePlayer opponent = player.equals(host) ? guest : host;

        if(lineDirection.equals("middle")) {
            if(opponent.getMiddleCastleHitpoint() == -1)
                return "This castle is already destroyed!";

            opponent.changeMiddleCastleHitpoint(-FireballSpell.getDamage());

            if(opponent.getMiddleCastleHitpoint() < 1)
                opponent.setMiddleCastleHitpoint(-1);
        }

        else if(lineDirection.equals("left")) {
            if(opponent.getLeftCastleHitpoint() == -1)
                return "This castle is already destroyed!";

            opponent.changeLeftCastleHitpoint(-FireballSpell.getDamage());

            if(opponent.getLeftCastleHitpoint() < 1)
                opponent.setLeftCastleHitpoint(-1);
        }

        else if(lineDirection.equals("right")) {
            if(opponent.getRightCastleHitpoint() == -1)
                return "This castle is already destroyed!";

            opponent.changeRightCastleHitpoint(-FireballSpell.getDamage());

            if(opponent.getRightCastleHitpoint() < 1)
                opponent.setRightCastleHitpoint(-1);
        }

        player.changeCardsToPlayCount(-1);

        return "You have deployed Fireball successfully!";
    }

    public void resultOfGame() {
        int hostTotalHitpoint = host.getTotalHitpoint();
        int guestTotalHitpoint = guest.getTotalHitpoint();

        if(hostTotalHitpoint > guestTotalHitpoint)
            System.out.println("Game has ended. Winner: " + host.getUser().getUsername());

        else if(hostTotalHitpoint < guestTotalHitpoint)
            System.out.println("Game has ended. Winner: " + guest.getUser().getUsername());

        else
            System.out.println("Game has ended. Result: Tie");

        host.updateLevelAndExperience(hostTotalHitpoint);
        guest.updateLevelAndExperience(guestTotalHitpoint);

        host.updateGold(guest);
        guest.updateGold(host);
    }
}
