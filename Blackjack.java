package blackjack;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Blackjack extends BlackjackRules {

	public Blackjack(int fromBefore) {
		BlackjackGUI blGUI = new BlackjackGUI();
		blGUI.table();
		int startingBet = fromBefore;
		int betted;
		betted = meny(startingBet);
		startingBet = startingBet - betted;
		playThrough(betted, startingBet, startDeck());
	}

	public Blackjack(int fromBefore, Stack<Cards> startDeck) {
		int startingBet = fromBefore;
		int betted;
		betted = meny(startingBet);
		startingBet = startingBet - betted;
		playThrough(betted, startingBet, startDeck);
	}

	private void playThrough(int betted, int startingBet, Stack<Cards> startDeck) {
		Stack<Cards> decks = startDeck;
		ArrayList<Cards> cardList = new ArrayList<Cards>();
		int dealerValue = 0;
		int tempBetted = betted;
		boolean give = true;
		boolean doubleDown = false;
		Cards card = draw(decks);
		Cards card2 = draw(decks);
		cardList.add(card);
		cardList.add(card2);
		while (betted > 0) {
			System.out.println("You got a " + card.getCardName() + " and a "
					+ card2.getCardName() + " total value " + value(cardList)); 
			if (value(cardList) == 21) {
				betted = rules(cardList, dealerValue, betted);
				System.out.println("you won " + betted);
				goAgain(startingBet, betted, decks);
			}
			
			
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			if (card.cardName.equals(card2.cardName))
			{
				System.out.println("Do you want to split? Y/N");
				String split = scanner.next().toLowerCase();
				if (split.equals("y"))
					split(cardList, betted, decks, startingBet);
				else
					continue;
			}
			
			while (give) {

				if ((value(cardList) == 10 || value(cardList) == 11)
						&& cardList.size() <= 2) {
					System.out
							.println("Do you want to stay or hit or double? ");
					doubleDown = true;
				} else
					System.out.println("Do you want to stay or hit? ");
				String hitOrStay = scanner.next().toLowerCase();

				if (hitOrStay.equals("hit")) {
					deal(cardList, decks);
					System.out.println("you now have " + value(cardList));
				} else if (hitOrStay.equals("stay")) {
					give = false;// check dealer;
				} else if (hitOrStay.equals("double") && doubleDown) {
					dealerValue = dealer(decks);
					deal(cardList, decks);
					System.out.println("you now have " + value(cardList));
					betted = rules(cardList, dealerValue, betted)*2;
					startingBet-=tempBetted;
					tempBetted *= 2;
					
					System.out.println(betted + " " + tempBetted);
					give = false;
				}
				if (value(cardList) >= 21)
					give = false;

			}
			if (!doubleDown) {
				dealerValue = dealer(decks);
				betted = rules(cardList, dealerValue, betted);
			}
			if (betted > tempBetted)
				System.out.println("you won " + tempBetted);
			else
				System.out.println("you lost " + tempBetted);
			goAgain(startingBet, betted, decks);

		}
	}

	private void goAgain(int startingBet, int betted, Stack<Cards> startDeck) {
		String answer = "";
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("do you want to continue? Y/N");
			answer = scanner.next().toUpperCase();
			if (answer.equals("Y"))
				new Blackjack(startingBet + betted, startDeck);
			else if (answer.equals("N")) {
				System.out.println("you quit with " + (betted + startingBet));
				System.exit(0);
			}
		} while (!answer.equals("Y") || !answer.equals("N"));
	}

	private ArrayList<Cards> deal(ArrayList<Cards> cardList,
			Stack<Cards> drawing) {
		Cards card = (draw(drawing));
		cardList.add(card);
		System.out.println("You got a " + card.getCardName());
		return cardList;
	}

	private int meny(int startingBet) {
		System.out.println("Hej och välkommen till Blackjack");
		System.out.println("Skriv in hur mycket du vill satsa: ");
		int bet = 0;
		boolean betNotOk = true;

		System.out.println("You have " + startingBet + " on the bank");
		if (startingBet == 0) {
			System.out.println("you're out of cash go home");
			System.exit(0);
		}

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		while (betNotOk) {
			if (scanner.hasNextInt()) {
				bet = scanner.nextInt();
				if (bet < startingBet + 1)
					betNotOk = false;
				else
					System.out.println("Du har sattsat mer än du har");
			} else {
				System.out
						.println("Felaktig inmatning, skriv igen hur mycket du vill sattsa ");
				scanner.next();
			}
		}
		System.out.println("du har satsat: " + bet + "kronor");

		return bet;

	}

	private int dealer(Stack<Cards> dealerCard) {
		ArrayList<Cards> dealerCards = new ArrayList<Cards>();
		Cards card = draw(dealerCard);
		System.out.println("dealer has a " + card.getCardName());
		dealerCards.add(card);
		int i = 0;
		do {
			i++;
			dealerCards.add(new Cards());
			System.out.println("dealer draws a "
					+ dealerCards.get(i).getCardName());

		} while (value(dealerCards) <= 16);
		return value(dealerCards);
	}

	private Stack<Cards> startDeck() {
		Stack<Cards> decks = deckStack();
		return decks;
	}
	
	 private void split(ArrayList<Cards> hand, int bet, Stack<Cards> decks, int startingBet ) { 
		ArrayList<Cards> list1 = new ArrayList<Cards>();
		ArrayList<Cards> list2 = new ArrayList<Cards>();
		int totalBet = 0;
		int tempBetted = startingBet*2;
		int [] handsValue = new int[2];
		int handValue1 = 0;
		int handValue2 = 0;
		boolean hands = true;
		int dealerValue = dealer(decks);
		 for (Cards card : hand)
		 {
			 if(hands)
			 {
				 list1.add(card);
				 list1 = splitPlay(list1, decks);
				 handValue1 = rules(list1, dealerValue, bet);
				 handsValue[0] = handValue1;
			 }
			 else
				 {
				 list2.add(card);
				 list2= splitPlay(list2, decks);
				 handValue2 = rules(list2, dealerValue, bet);
				 handsValue[1] = handValue2;
				 }
			 hands =hands ? false:true; 
		 }	 
			 	
			 	for (int i = 0; i < handsValue.length; i++) {
				totalBet += bet;
		 
				if (totalBet > tempBetted)
					System.out.println("you won " + tempBetted);
				else
					System.out.println("you lost " + tempBetted);
				}
				goAgain(startingBet, bet, decks);
		}
	 private ArrayList<Cards> splitPlay(ArrayList<Cards> tempCards,Stack<Cards>decks)
	 {
		 System.out.println("you have a" +tempCards.get(0).cardName);
		 boolean give = true;
		  @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		while (give) {					
					System.out.println("Do you want to stay or hit? ");
				String hitOrStay = scanner.next().toLowerCase();

				if (hitOrStay.equals("hit")) {
					tempCards = deal(tempCards, decks);
					System.out.println("you now have " + value(tempCards));
				} else if (hitOrStay.equals("stay")) {
					give = false;// check dealer;
				}
				if (value(tempCards) >= 21)
					give = false;

			}
		return tempCards;		  
	 }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int startingBet = 1000;
		
		Stack<Cards> deck = new Stack<Cards>();
		deck = deckStack();
		
		new Blackjack(startingBet);
	}

}
