package blackjack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;


public abstract class BlackjackRules {

	protected int rules(ArrayList<Cards> cardList, int dealerValue, int betted) {
		if (cardList.size() == 2 && value(cardList) == 21) {
			System.out.println("Blackjack");
			return betted *= 1.5;
		}
		if (value(cardList) == dealerValue && value(cardList) <= 21) {
			System.out.println("Both you and the dealer got " + dealerValue
					+ " you lost");
			return betted = 0;
		}
		if (value(cardList) > 21) {
			System.out.println("you got bust");
			return betted = 0;
		}
		if (dealerValue > 21) {
			System.out
					.println("You got " + value(cardList)
							+ " and the dealer got " + dealerValue
							+ " dealer was bust");
			return betted += betted * 1;
		}
		if (value(cardList) > dealerValue && dealerValue < 21) {
			System.out.println("You got " + value(cardList)
					+ " and the dealer got " + dealerValue);
			return betted += betted * 1;
		} else if (value(cardList) < dealerValue) {
			System.out.println("You got " + value(cardList)
					+ " and the dealer got " + dealerValue);
			return betted * 0;
		}
		return betted;
	}

	protected int value(ArrayList<Cards> cardList) {
		int cardsValue = 0;
		for (Cards cards : cardList) {
			cardsValue += checkValue(cards.getCardName(), cardsValue);
		}
		return cardsValue;
	}

	protected int checkValue(String name, int cardsValue) {

		switch (name)
		{
		case "2" : return 2;
		case "3" : return 3;
		case "4" : return 4;
		case "5" : return 5;
		case "6" : return 6;
		case "7" : return 7;
		case "8" : return 8;
		case "9" : return 9;
		case "10" : return 10;
		case "Knight" : return 10;
		case "Dame" : return 10;
		case "King" : return 10;
		case "Ace" : return ace(cardsValue);
		default : return 0;
		}
		
	}

	protected int ace(int cardsValue) {
		return ((cardsValue + 11) > 21) ? 1 : 11;

	}

	protected static Stack<Cards> deckStack() {
		ArrayList<CardDeck> deck = new ArrayList<CardDeck>();
		ArrayList<Cards> cards = new ArrayList<Cards>();
		Stack<Cards> deckStack = new Stack<Cards>();
		for (int i = 0; i < 3; i++) {
			deck.add(new CardDeck());

			for (CardDeck cardDeck : deck) {
				for (Cards card : cardDeck.deck)
					deckStack.add(card);

			}
			//for (Cards card : deckStack)
				//System.out.println(deckStack.size());
		}
		return deckStack;
	}

	protected Cards draw(Stack<Cards> deck) {
		Cards card = deck.pop();
		 System.out.println(card.cardName);
		return card;

	}
	/*
	
			for (CardDeck cardDeck : deck) {
				for (Cards card : cardDeck.deck)
					deckStack.add(cardDeck.draw());

			}*/
	 

	 

}
