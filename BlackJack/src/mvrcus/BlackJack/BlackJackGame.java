package mvrcus.BlackJack;

public class BlackJackGame {
	BlackJackTable table;
	
	public BlackJackGame(int players, int decks) {
		this.table = new BlackJackTable(players, decks); 
		
		
	}
	
	public String toString() { 
		return table.toString();
	}
	
	public CardHand getCardHand(int n) {
		return table.getCardHand(n);
	}
	
	public void hitPlayer(int n) {
		this.table.hitPlayer(n);
	}
	
	
//
}
