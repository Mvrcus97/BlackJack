package mvrcus.BlackJack;

import java.util.ArrayList;
import mvrcus.BlackJack.Deck.Card;

public class CardHand {
	ArrayList<Card> list;
	int[] sum;
	
	public CardHand(Card c) {
		list = new ArrayList<Card>();
		list.add(c);
		sum = new int[2];
		updateSum(c.getNum());
	}
	
	public void addCard(Card c) {
		list.add(c);
		updateSum(c.getNum());
	}
	
	public String getSum() {
		String res = Integer.toString(sum[0]);
		if(sum[0] != sum[1] ) {
			res += " or " + sum[1];
		}
		
		
		return res;
	}
	public String toString() {
		StringBuilder cards = new StringBuilder();
		for(Card c : list) {
			cards.append(c.getType()).append(" ");
			cards.append(c.getNum()).append(", ");
		}
		return cards.toString();
	}
	
	// Update total sum, J,Q,K = 10. A = 1 and 11. 
	public void updateSum(int x) {
		int tmp1, tmp2;
		if( x == 14) {
			// If A, never need to add 11 if sum will be over 21. 
			tmp1 = sum[0] > 20? 1:11;
			tmp2 = 1;
		}else if(x > 10) {
			tmp1 = tmp2 = 10;
		}else {
			tmp1 = tmp2 = x;
		}
		sum[0] += tmp1;
		sum[1] += tmp2;
	}//end updateSum
	
}// end CardHand
