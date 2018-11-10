package blackjack;

import java.util.ArrayList;//Imports the ArrayList
import java.util.Collections; //Imports Collections library.

public class Deck {
	
	//Declares variable.
	private int cardCounter;
	
	private ArrayList<Card> deck;
	
	
	//Adds 52 Card objects into the ArrayList<Card>() named deck.
	public Deck(){
		deck = new ArrayList<Card>();
		for ( int value = 2; value <= 10; value++ ){
			Card card = new Card(Integer.toString(value), "Hearts", value);
			deck.add(card);
		}
		for (int value = 2; value <=10; value++){
			Card card = new Card(Integer.toString(value), "Diamonds", value);
			deck.add(card);
		}
		for (int value = 2; value <= 10; value++){
			Card card = new Card(Integer.toString(value), "Spades", value);
			deck.add(card);
		}
		for (int value = 2; value <= 10; value++){
			Card card = new Card(Integer.toString(value), "Clubs", value);
			deck.add(card);
		}
		deck.add(new Card("Jack","Hearts",10));
		deck.add(new Card("Queen","Hearts",10));
		deck.add(new Card("King","Hearts",10));
		deck.add(new Card("Ace","Hearts",11));
		deck.add(new Card("Jack","Diamond",10));
		deck.add(new Card("Queen","Diamond",10));
		deck.add(new Card("King","Diamond",10));
		deck.add(new Card("Ace","Diamond",11));
		deck.add(new Card("Jack","Spades",10));
		deck.add(new Card("Queen","Spades",10));
		deck.add(new Card("King","Spades",10));
		deck.add(new Card("Ace","Spades",11));
		deck.add(new Card("Jack","Clubs",10));
		deck.add(new Card("Queen","Clubs",10));
		deck.add(new Card("King","Clubs",10));
		deck.add(new Card("Ace","Clubs",11));
	}
	
	//Shuffles the deck.
	public void shuffleDeck() {
		Collections.shuffle(deck);
	}
	
	//Gets the current card's rank in the deck.
	public String getCurrentCardRank(){
		cardCounter = BlackJackGame.getDeckCounter();
		return deck.get(cardCounter).getCardRank();
	}
	
	//Gets the current card's suit in the deck.
	public String getCurrentCardSuit(){
		cardCounter = BlackJackGame.getDeckCounter();
		return deck.get(cardCounter).getCardSuit();
	}
	
	//Gets the current cards's value in the deck.
	public int getCurrentCardValue(){
		cardCounter = BlackJackGame.getDeckCounter();
		return deck.get(cardCounter).getCardValue();
	}
}
