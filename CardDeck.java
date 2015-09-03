package blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

public class CardDeck extends Cards {

	Stack<Cards> deck = new Stack<Cards>();

	CardDeck() {
		ArrayList<Cards> deckOfCards = new ArrayList<Cards>();
		deckOfCards = makeDeck();
		deck.addAll(deckOfCards);
		deck = shuffle(deck);

	}

	public Stack<Cards> getDeck() {
		return this.deck;
	}
	/*
	public Cards draw() {
		//deck = shuffle(deck);
		Cards card = deck.pop();
		 System.out.println(card.cardName);
		return card;

	}*/

	private Stack<Cards> shuffle(Stack<Cards> deck) {
		long seed = System.nanoTime();
		Collections.shuffle(deck, new Random(seed));
		Collections.shuffle(deck, new Random(seed));
		return deck;

	}

	private ArrayList<Cards> makeDeck() {
		ArrayList<Cards> deck = new ArrayList<Cards>();
		for (int i = 0; i < 4; i++) {
			for (int j = 1; j < 14; j++) {
				Cards card = new Cards();
				card.setCardType(j + 1);
				// System.out.println(card.getCardName());
				deck.add(card);
			}

		}

		return deck;

	}

}
