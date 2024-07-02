package Controller;

import Model.ClashRoyale;
import Model.User;
import View.MainMenuEnums;
import View.ShopMenuEnums;

import java.util.regex.Matcher;

public class ShopMenuController {
    private static User owner;

    public static void setOwner() {
        owner = ClashRoyale.getCurrentOwner();
    }

    public static String buyCard(Matcher matcher) {
        String cardName = matcher.group("cardName");

        if(ShopMenuEnums.getMatcher(cardName, ShopMenuEnums.VALID_CARD_NAMES) == null)
            return "Invalid card name!";

        else if(ClashRoyale.getPriceOfCard(cardName) > owner.getGold())
            return "Not enough gold to buy " + cardName + "!";

        else if(owner.isCardInUserCards(cardName))
            return "You have this card!";

        owner.changeGold(-ClashRoyale.getPriceOfCard(cardName));

        owner.addCardToUserCards(cardName);

        return "Card " + cardName + " bought successfully!";
    }

    public static String sellCard(Matcher matcher) {
        String cardName = matcher.group("cardName");

        if(ShopMenuEnums.getMatcher(cardName, ShopMenuEnums.VALID_CARD_NAMES) == null)
            return "Invalid card name!";

        else if(!owner.isCardInUserCards(cardName))
            return "You don't have this card!";

        else if(owner.isCardInBattleDeck(cardName))
            return "You cannot sell a card from your battle deck!";

        owner.changeGold((int) Math.floor(0.75 * ClashRoyale.getPriceOfCard(cardName)));

        owner.removeCardFromUserCards(cardName);

        return "Card " + cardName + " sold successfully!";
    }
}
