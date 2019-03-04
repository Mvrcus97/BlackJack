package mvrcus.BlackJack;

import java.util.ArrayList;
import mvrcus.BlackJack.Deck.Card;

public class CardHand {
	ArrayList<Card> list;
	int sum; 
	
	public CardHand(Card c) {
		list = new ArrayList<Card>();
		list.add(c);
		sum = c.getNum();
	}
	
	public void addCard(Card c) {
		list.add(c);
	}
}
