package blackjack;

import java.util.ArrayList;//Imports the ArrayList

public class Hand {
	
	private int valueCounter;
	private int handValue;
	
	private ArrayList<Card> hand;
	
	public Hand(){
		hand = new ArrayList<Card>();
	}
	
	//Returns hand's total value of cards.
	public int getHandValue(){
		valueCounter = 0;
		for (int i = 0; i < hand.size(); i++){
			valueCounter += hand.get(i).getCardValue();
		}
		return valueCounter;
	}
	
	//Adds a card to hand.
	public void addCard(
			String rank,
			String suit,
			int value){
		hand.add(new Card(rank, suit, value));
	}
	
	//Displays total value of cards in hand.
	public void displayHandValue(){
		handValue = getHandValue();
		System.out.println("Hand Value: "+handValue);
	}
	
	//Displays all cards' rank and suit.
	public void displayHand(){
		for (int i = 0; i < hand.size(); i++){
			System.out.println(hand.get(i).getCardRank() +" of " +hand.get(i).getCardSuit());
		}
	}
	
	//Clears hand of cards.
	public void clearHand(){
		hand.clear();
	}
	
	//Changes ace to value of one if bust.
	public void changeAceValue(){
		boolean hasAce = false;
		handValue = getHandValue();
		if (handValue > 21){
			for (int i = 0; i < hand.size(); i++){
				if (hand.get(i).getCardRank() == "Ace" && hasAce == false){
					hasAce = true;
					hand.get(i).setCardValue(1);
				}
			}
		}
	}
}
