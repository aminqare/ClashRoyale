package Model;

public class GameCard {
    private String name;
    private static int price;
    private GamePlayer player;

    public GameCard(GamePlayer player) {
        this.player = player;
    }

    public final String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public GamePlayer getPlayer() {
        return player;
    }
}
