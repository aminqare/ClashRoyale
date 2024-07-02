package Model;

public class FireballSpell extends GameCard {
    private static int damage = 1400;

    public FireballSpell(GamePlayer player) {
        super(player);
        this.setName("Fireball");
        this.setPrice(80);
    }

    public static int getDamage() {
        return damage;
    }
}
