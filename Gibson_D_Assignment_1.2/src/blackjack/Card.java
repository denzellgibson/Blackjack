package blackjack;

public class Card {

	//Declares variables.
	private String rank;
	private String suit;
	private int value;
	
	//Constructor.
	public Card(
			String cardRank,
			String cardSuit,
			int cardValue)
	{
		setCardRank(cardRank);
		setCardSuit(cardSuit);
		setCardValue(cardValue);
	}
	
	//Set card rank.
	public void setCardRank(String cardRank){
		rank = cardRank;
	}
	
	//Set card suite.
	public void setCardSuit(String cardSuit){
		suit = cardSuit;
	}
	
	//Set card value.
	public void setCardValue(int cardValue){
		value = cardValue;
	}
	
	//Returns rank.
	public String getCardRank(){
		return rank;
	}
	
	//Returns suite.
	public String getCardSuit(){
		return suit;
	}
	
	//Returns value.
	public int getCardValue(){
		return value;
	}
}
