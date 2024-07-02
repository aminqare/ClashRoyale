package Model;

import java.util.ArrayList;

public class GamePlayer {
    private User user;
    private int middleCastleHitpoint;
    private int leftCastleHitpoint;
    private int rightCastleHitpoint;
    private int castleDamage;
    private int cardsToPlayCount;
    private int troopsToPlayCount;
    private ArrayList<Troop>[] leftLineTroops;
    private ArrayList<Troop>[] middleLineTroops;
    private ArrayList<Troop>[] rightLineTroops;


    public GamePlayer(User user) {
        this.user = user;
        middleCastleHitpoint = 3400 * user.getLevel();
        rightCastleHitpoint = 2200 * user.getLevel();
        leftCastleHitpoint = 2200 * user.getLevel();
        castleDamage = 450 * user.getLevel();
        leftLineTroops = (ArrayList<Troop>[]) new ArrayList[15];
        for(int i = 0 ; i < 15 ; i++) {
            leftLineTroops[i] = new ArrayList<>();
        }

        middleLineTroops = (ArrayList<Troop>[]) new ArrayList[15];
        for(int i = 0 ; i < 15 ; i++) {
            middleLineTroops[i] = new ArrayList<>();
        }

        rightLineTroops = (ArrayList<Troop>[]) new ArrayList[15];
        for(int i = 0 ; i < 15 ; i++) {
            rightLineTroops[i] = new ArrayList<>();
        }

    }

    public User getUser() {
        return user;
    }

    public int getMiddleCastleHitpoint() {
        return middleCastleHitpoint;
    }

    public int getLeftCastleHitpoint() {
        return leftCastleHitpoint;
    }

    public int getRightCastleHitpoint() {
        return rightCastleHitpoint;
    }

    public int getCastleDamage() {
        return castleDamage;
    }

    public void changeMiddleCastleHitpoint(int amount) {
        middleCastleHitpoint += amount;
    }

    public void changeLeftCastleHitpoint(int amount) {
        leftCastleHitpoint += amount;
    }

    public void changeRightCastleHitpoint(int amount) {
        rightCastleHitpoint += amount;
    }

    public int getCardsToPlayCount() {
        return cardsToPlayCount;
    }

    public void setCardsToPlayCount(int cardsToPlayCount) {
        this.cardsToPlayCount = cardsToPlayCount;
    }

    public int getTroopsToPlayCount() {
        return troopsToPlayCount;
    }

    public void setTroopsToPlayCount(int troopsToPlayCount) {
        this.troopsToPlayCount = troopsToPlayCount;
    }

    public void changeCardsToPlayCount(int amount) {
        cardsToPlayCount += amount;
    }

    public void changeTroopsToPlayCount(int amount) {
        troopsToPlayCount += amount;
    }

    public void setMiddleCastleHitpoint(int middleCastleHitpoint) {
        this.middleCastleHitpoint = middleCastleHitpoint;
    }

    public void setRightCastleHitpoint(int rightCastleHitpoint) {
        this.rightCastleHitpoint = rightCastleHitpoint;
    }

    public void setLeftCastleHitpoint(int leftCastleHitpoint) {
        this.leftCastleHitpoint = leftCastleHitpoint;
    }

    public ArrayList<Troop>[] getLeftLineTroops() {
        return leftLineTroops;
    }

    public ArrayList<Troop>[] getMiddleLineTroops() {
        return middleLineTroops;
    }

    public ArrayList<Troop>[] getRightLineTroops() {
        return rightLineTroops;
    }

    public void addToMiddleLineTroops(Troop troop, int rowNumber) {
        middleLineTroops[rowNumber - 1].add(troop);
    }

    public void addToLeftLineTroops(Troop troop, int rowNumber) {
        leftLineTroops[rowNumber - 1].add(troop);
    }

    public void addToRightLineTroops(Troop troop, int rowNumber) {
        rightLineTroops[rowNumber - 1].add(troop);
    }

    public void removeFromMiddleLineTroops(Troop troop, int rowNumber) {
        middleLineTroops[rowNumber - 1].remove(troop);
    }

    public void removeFromLeftLineTroops(Troop troop, int rowNumber) {
        leftLineTroops[rowNumber - 1].remove(troop);
    }

    public void removeFromRightLineTroops(Troop troop, int rowNumber) {
        rightLineTroops[rowNumber - 1].remove(troop);
    }

    public int getTotalHitpoint() {
        int totalHitpoint = 0;

        if(middleCastleHitpoint != -1)
            totalHitpoint += middleCastleHitpoint;

        if(leftCastleHitpoint != -1)
            totalHitpoint += leftCastleHitpoint;

        if(rightCastleHitpoint != -1)
            totalHitpoint += rightCastleHitpoint;

        return totalHitpoint;
    }

    public void updateLevelAndExperience(int amount) {
        user.changeExperience(amount);

        while(user.getExperience() > user.getExperienceNeededForUpdate()) {
            user.changeExperience(-user.getExperienceNeededForUpdate());
            user.changeLevel(1);
        }

    }

    public void updateGold(GamePlayer opponent) {
        int totalAmount = 0;
        if(opponent.getMiddleCastleHitpoint() == -1)
            totalAmount += 20;

        if(opponent.getLeftCastleHitpoint() == -1)
            totalAmount += 20;

        if(opponent.getRightCastleHitpoint() == -1)
            totalAmount += 20;

        user.changeGold(totalAmount);
    }
}
