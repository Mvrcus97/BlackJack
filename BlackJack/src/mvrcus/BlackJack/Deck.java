package mvrcus.BlackJack;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Deck {
	Card[] deck;
	int cardPointer;
	
	// Constructor
	public Deck(int n) {
		cardPointer = 0;
		initializeDeck(n);
		for( Card c : deck) {
			System.out.println(c.getType() + " " + c.getNum());
		}
	}
	
	
	
	
	private void initializeDeck(int n) {
		deck = new Card[52*n];
		String[] types = {"clubs", "diamonds", "hearts", "spades"};
		for(int counter = 0; counter<4*n; counter++) {
			for(int i=0; i<13; i++) {
				deck[counter*13 + i] = new Card(i+2, types[counter%4]);
			}
		}//end double for
		//Let's shuffle it a couple of times..
		for(int i = 0; i <5; i++) {
			shuffleDeck();
		}
	}
	
	


	//Fisher–Yates shuffle
	private void shuffleDeck() {
	    Random rnd = ThreadLocalRandom.current();
	    for (int i = deck.length-1; i > 0; i--){
	      int index = rnd.nextInt(i + 1);
	      // Simple swap
	      Card tmp = deck[index];
	      deck[index] = deck[i];
	      deck[i] = tmp;
	    }
	}//end shuffleDeck


	public boolean canPlay() {
		return cardPointer > 10? true:false;
	}
	
	public Card pullCard(){
		Card tmp = deck[cardPointer];
		this.cardPointer ++; 
		return tmp;
	}
	




	/* Inner class Card */
	public class Card{
		private int num;
		private String type;
		
		public Card(int num, String type) {
			this.num = num;
			this.type = type;
		}
		
		public int getNum() {return this.num;}
		
		public String getType() {return this.type;}
	}// end Card. 

	
}// end Deck
