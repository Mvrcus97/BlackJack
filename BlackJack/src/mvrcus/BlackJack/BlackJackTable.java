package mvrcus.BlackJack;

public class BlackJackTable {
	
	
	public BlackJackTable() {
		Deck deck = new Deck(8);
		int players = 2;
		
		CardHand hand1 = new CardHand(deck.pullCard());
		CardHand hand2 = new CardHand(deck.pullCard());
		hand1.addCard(deck.pullCard());
		hand1.addCard(deck.pullCard());
		hand1.addCard(deck.pullCard());
		
		
		hand2.addCard(deck.pullCard());
		hand2.addCard(deck.pullCard());
		
		System.out.println("Hand 1 sum: " + hand1.getSum().toString());
		System.out.println("Hand 2 sum: " + hand2.getSum().toString());
		System.out.println("Hand 1 cards: " + hand1.toString());
		System.out.println("Hand 2 cards: " + hand2.toString());
		
	}

}
