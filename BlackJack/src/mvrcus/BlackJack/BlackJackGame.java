package mvrcus.BlackJack;

public class BlackJackGame {
	private BlackJackTable table;
	private int currPlayer;
	private int players;
	
	public BlackJackGame(int players, int decks) {
		this.table = new BlackJackTable(players, decks); 
		this.currPlayer = 0;
		this.players = players;
	}
	
	
	public void hitNext() { 
		if(currPlayer == players) {
			table.hitDealer();
		}else {
			table.hitPlayer(currPlayer); 
		}
	}
	
	
	public void standNext() {
		currPlayer ++; 
		if(currPlayer == players) {
			//Players done. Signal show dealer card.
			table.showDealer();
		}
	}
	
	public int getNextSum() {
		return this.getPlayerSum(currPlayer);
	}


	//Return SUM or -1 if (Sum > 21)
	public int getDealerSum() {
		int ret = table.getDealerSum();
		if(ret > 21) ret =-1; 
		return ret;
		}
	
	//Return SUM or -1 if (Sum > 21)
	public int getPlayerSum(int id) {
		int ret = table.getPlayerSum(id);
		if(ret > 21) ret = -1;
		return ret;
		}

	/* Return
	 *  0 - Dealer Not Done
	 *  1 - Dealer Done
	 */
	public int dealerTurn() {
		int ret = 1;
		if(table.getDealerSum() < 17) {
			table.hitDealer();
			ret = 0;
		}
		return ret;
	}



	public String toString() { 
		return table.toString();
	}
	
	public CardHand getNextHand() {return table.getCardHand(currPlayer);}
	public CardHand getCardHand(int n) {
		return table.getCardHand(n);
	}
	
	public void hitPlayer(int n) {
		this.table.hitPlayer(n);
	}
	
	public void hitDealer() {
		this.table.hitDealer();
	}


	public DealerHand getDealerHand() {
		
		return this.table.getDealerHand();
	}
	
	
	
	
//
}
