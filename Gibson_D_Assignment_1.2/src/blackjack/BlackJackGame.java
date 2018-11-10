package blackjack;

import java.util.Scanner;
import java.util.InputMismatchException;

public class BlackJackGame {
	
	//Instantiates a new Deck(), and two new Hands().
	static Deck gameDeck = new Deck();
	static Hand playerHand = new Hand();
	static Hand dealerHand = new Hand();
	
	//Declares variables.
	private static int playerValue, dealerValue, deckCounter;
	private static boolean isPlaying, playerTurn;

	public static void main(String[] args){
		
		//Declares variables.
		int userAction;
		boolean isRunning;
		Scanner input = new Scanner(System.in);
		
		System.out.println("Welcome to Blackjack!");
		//The main menu. Will loop back when game is over.
		while (isRunning = true){
			try{
				System.out.println("Enter the number that corresponds with the desired action");
				System.out.println("1: Start game!");
				System.out.println("2: Exit Program");
				
				userAction = input.nextInt();
				//Starts the game if user enters '1' as their desired action.
				if (userAction == 1){
					startGame();
				}
				//Ends the program if the user enters '2' as their desired action.
				else if (userAction == 2){
					exitProgram();
					isRunning = false;
				}
				//Tells the user to enter a valid number if the number entered isn't '' or '2'.
				else{
					System.out.println("Please enter a valid number!");
					
				}
			}
			//Catches for anything that isn't an integer.
			catch (InputMismatchException ex){
				System.out.println("Please Try Again ("+"Invalid Input: An Integer is Required");
				input.nextLine();
			}
		}
	}
	
	//Returns the current card in the deck.
	public static int getDeckCounter(){
		return deckCounter;
	}
	
	//On the player's turn, this method is used to perform calculations to check for 21 and actions the user can take if he hasn't lost in the initial draw.
	public static void playerAction(){
		
		//Displays cards and value currently in hand.
		playerValue = playerHand.getHandValue();
		System.out.println("You drew:");
		playerHand.displayHand();
		System.out.println("Hand Value: "+playerValue);
		//Checks if has blackjack.
		if (playerValue == 21){
			System.out.println("You got Blackjack! Congratulations!");
			isPlaying = false;
		}
		//Check if user busts.
		else if (playerValue > 21){
			//Checks and changes aces to the value of one.
			playerHand.changeAceValue();
			playerValue = playerHand.getHandValue();
			//Checks again for bust.
			if (playerValue > 21){
				//User loses.
				System.out.println("Sorry, You busted.");
				isPlaying = false;
			}
		}
		//If player hasn't got blackjack or bust, will ask if player like to stand or hit.
		else{
			int userAction;
			Scanner input = new Scanner(System.in);
			try{
				System.out.println("Enter the number that corresponds with the desired action");
				System.out.println("1: Will you Draw?");
				System.out.println("2: Will you Hit?");
				
				//Gets player input.
				userAction = input.nextInt();
				//If player input is '1', player will drawCard().
				if (userAction == 1){
					drawCard();
				}
				//If player input is '2', player will stand and end their turn.
				else if (userAction == 2){
					playerTurn = false;
				}
				//Tells the player to enter a valid integer if input is not '1' or '2'.
				else{
					System.out.println("Please enter a valid integer!");
				}
			}
			//Catches for input that isn't an integer.
			catch (InputMismatchException ex){
				System.out.println("Please Try Again ("+"Invalid Input: An Integer is Required");
				input.nextLine();
			}
		}
	}
	
	//The dealer's turn; checks for blackjack, bust, and when to draw.
	public static void dealerAction(){
		//Displays value and cards in hand.
		dealerValue = dealerHand.getHandValue();
		System.out.println("Dealer drew:");
		dealerHand.displayHand();
		System.out.println("Hand Value: "+dealerValue);
		System.out.println();
		//Checks if dealer has blackjack. Tells player they lost when condition is true and displays both hands.
		if (dealerValue == 21){
			System.out.println("Sorry, Dealer got Blackjack");
			System.out.println();
			System.out.println("You drew:");
			playerHand.displayHand();
			playerHand.displayHandValue();
			System.out.println();
			
			System.out.println("Dealer drew:");
			dealerHand.displayHand();
			dealerHand.displayHandValue();
			System.out.println();
			isPlaying = false;
		}
		
		//Checks if user busted, if has aces, and tells player that they won.
		else if (dealerValue > 21){
			//Changes ace value to one if their is an ace.
			dealerHand.changeAceValue();
			dealerValue = dealerHand.getHandValue();
			//Checks if the dealer is still over 21. Tells player they one if condition is true and displays both hands.
			if (dealerValue > 21){
				System.out.println("Dealer busted. You have won!");
				System.out.println();
				System.out.println("You drew:");
				playerHand.displayHand();
				playerHand.displayHandValue();
				System.out.println();
				
				System.out.println("Dealer drew:");
				dealerHand.displayHand();
				dealerHand.displayHandValue();
				System.out.println();
				isPlaying = false;
			}
		}
		
		//Checks if dealer's hand value is less than player's hand value, and draws a card if true.
		else if (dealerValue <= playerValue){
			dealerDrawCard();
		}
		
		//Checks if dealer has higher hand value than player and under 21. Tells player they lost if true and displays both hands.
		else if (dealerValue > playerValue && dealerValue < 21){
			System.out.println("Sorry, Dealer has won");
			System.out.println();
			System.out.println("You drew:");
			playerHand.displayHand();
			playerHand.displayHandValue();
			System.out.println();
			
			System.out.println("Dealer drew:");
			dealerHand.displayHand();
			dealerHand.displayHandValue();
			System.out.println();
			isPlaying = false;
		}
	}
	
	//Starts up the game, clears each hand, initializes variables, shuffles deck, and adds two cards to each hand. Player turn starts first.
	public static void startGame(){
		
		//Initializes variables.
		playerHand.clearHand();
		dealerHand.clearHand();
		deckCounter = 0;
		playerValue = 0;
		dealerValue = 0;
		isPlaying = true;
		playerTurn = true;
		gameDeck.shuffleDeck();
		System.out.println("Player's Turn!");
		
		//Adds and displays two cards.
		initialDraw();
		dealerInitialDraw();
		
		//Will loop until player wins or loses.
		while (isPlaying == true){
			if (playerTurn == true){
				playerAction();
			}
			else{
				dealerAction();
			}
		}
	}
	
	//Adds the current card from deck to player's hand.
	public static void drawCard(){
		playerHand.addCard(gameDeck.getCurrentCardRank(),gameDeck.getCurrentCardSuit(),gameDeck.getCurrentCardValue());
		deckCounter += 1;
	}
	
	//Does the drawCard() method twice.
	public static void initialDraw(){
		drawCard();
		drawCard();
	}
	
	//Same as drawCard() but for dealer's hand.
	public static void dealerDrawCard(){
		dealerHand.addCard(gameDeck.getCurrentCardRank(),gameDeck.getCurrentCardSuit(),gameDeck.getCurrentCardValue());
		deckCounter += 1;
	}
	
	//Does dealerDrawCard() twice.
	public static void dealerInitialDraw(){
		dealerDrawCard();
		dealerDrawCard();
	}
	
	//Exits the program.
	public static void exitProgram(){
		System.exit(0);
	}
}
