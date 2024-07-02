package Model;

public class Troop extends GameCard{
    private int maxHitpoint;
    private int hitpoint;
    private int damage;

    public Troop(GamePlayer player, String troopName) {
        super(player);
        this.setName(troopName);

        if(troopName.equals("Archer")) {
            this.setPrice(80);
            this.setMaxHitpoint(1900);
            this.setHitpoint(1900);
            this.setDamage(800);
        }

        else if(troopName.equals("Wizard")) {
            this.setPrice(140);
            this.setMaxHitpoint(3300);
            this.setHitpoint(3300);
            this.setDamage(1400);
        }

        else if(troopName.equals("Dragon")) {
            this.setPrice(160);
            this.setMaxHitpoint(3200);
            this.setHitpoint(3200);
            this.setDamage(1100);
        }
    }

    public int getHitpoint() {
        return hitpoint;
    }

    public void setHitpoint(int hitpoint) {
        this.hitpoint = hitpoint;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getMaxHitpoint() {
        return maxHitpoint;
    }

    public void setMaxHitpoint(int maxHitpoint) {
        this.maxHitpoint = maxHitpoint;
    }

    public void changeHitpoint(int amount) {
        hitpoint += amount;
    }
}
