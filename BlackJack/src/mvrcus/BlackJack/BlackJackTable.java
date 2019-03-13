package mvrcus.BlackJack;

import java.util.ArrayList;

public class BlackJackTable {
	Deck deck;
	int players;
	ArrayList<CardHand> cardHands;
	DealerHand dealerHand;	
	
	
	public BlackJackTable(int players, int decks) {
		this.players = players;
		this.deck = new Deck(decks);
		this.cardHands = new ArrayList<CardHand>();
		
		beginRound();
		System.out.println("\nBEGINNING ROUND...");		
	}

	/* Begin round by dealing one card per player, followed by one
	 * card to dealer. Deal one extra card per player and finally one more (hidden) 
	 * card for the dealer. 
	 */
	private void beginRound() {
		//Create a cardHand per player. 
		for(int i = 0; i<players; i++) {
			cardHands.add(new CardHand(deck.pullCard()));
		}
		dealerHand = new DealerHand(deck.pullCard());
		for( CardHand hand : cardHands) {
			hand.addCard(deck.pullCard());
		}
		dealerHand.addCard(deck.pullCard());
	}//end beginRound
	
	
	public void hitPlayer(int id) {cardHands.get(id).addCard(deck.pullCard());}
	public void hitDealer() {dealerHand.addCard(deck.pullCard());}
	public int getPlayerSum(int id) {return cardHands.get(id).getSum();}
	public int getDealerSum() {return dealerHand.getSum();}
	public void showDealer() {dealerHand.showHand();}
	
	public String toString() {
		StringBuilder round = new StringBuilder();
		for(int i = 0; i < players; i++) { 
			round.append("Player ").append(i+1).append(": ").append(cardHands.get(i).toString());
			round.append(" sum is: ").append(cardHands.get(i).getSum());
			round.append("\n");
		}
		round.append("Dealer hand: ").append(dealerHand.getSum());
		return round.toString();
	}
	
	
	public CardHand getCardHand(int n) { 
		if( n >= cardHands.size()) {
			System.out.println("Error getCardHand()");
			return null;
		}
		return this.cardHands.get(n);
		
	}
	
	public DealerHand getDealerHand() {
		return this.dealerHand; 
	}
	
	
	
	
	
	
	

}// end BlackJackTable
