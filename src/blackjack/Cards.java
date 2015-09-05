package blackjack;

import java.util.Random;

public class Cards {
	String cardName = "";

	Cards() {
		Random random = new Random();
		int cardType = random.nextInt(13) + 2;
		cardName = cardName(cardType);

	}

	public String getCardName() {
		return this.cardName;
	}

	protected String setCardType(int cardType) {
		this.cardName = cardName(cardType);
		return this.cardName;
	}

	private String cardName(int cardType) {
		switch(cardType)
		{
		case 2 : return "2";
		case 3 : return "3";
		case 4 : return "4";
		case 5 : return "5";
		case 6 : return "6";
		case 7 : return "7";
		case 8 : return "8";
		case 9 : return "9";
		case 10 : return "10";
		case 11 : return "Knight";
		case 12 : return "Dame";
		case 13 : return "King";
		case 14 : return "Ace";
		default : return "error";
		}
	}
}
