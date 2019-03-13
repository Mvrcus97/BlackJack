package mvrcus.BlackJack;

import java.io.File;
import java.io.IOException;

import mvrcus.BlackJack.ImageFunctions.imageFunctions;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter {
	private BlackJackGame game = null;
		
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String args[] = event.getMessage().getContentRaw().split("\\s+"); //.split("//s+");
		
		if (args[0].equalsIgnoreCase(BlackJack.prefix + "info")){
			event.getChannel().sendTyping().queue();
			event.getChannel().sendMessage("Hey, whassssup?").queue(); 
		}
		
		
		if (args[0].equalsIgnoreCase(BlackJack.prefix + "play")){
			initializeGame(args, event);
			try {
				imageFunctions.drawHand(game.getDealerHand(), game.getCardHand(0));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			sendFile(null, new File("images/output.png"), event);
			
		}
		
		if (args[0].equalsIgnoreCase(BlackJack.prefix + "show")){
		    if(game == null) {
		    	event.getChannel().sendTyping().queue();
				event.getChannel().sendMessage("Please start a game first!").queue();
				event.getChannel().sendMessage("Start a game with '!play'").queue();
		    }
			String round = game.toString();
			
			event.getChannel().sendTyping().queue();
			event.getChannel().sendMessage(round).queue(); 
			sendFile("Generated Picture:", new File("images/output.png"), event);
			
		}
		
		if (args[0].equalsIgnoreCase(BlackJack.prefix + "h")){
		    if(game == null) {
		    	event.getChannel().sendTyping().queue();
				event.getChannel().sendMessage("Please start a game first!").queue();
				event.getChannel().sendMessage("Start a game with '!play'").queue();
		    }
		    
		    try {
		    	game.hitNext();
				imageFunctions.drawHand(game.getDealerHand(), game.getCardHand(0));
				sendFile("You chose to HIT!", new File("images/output.png"),event);
				if(game.getPlayerSum(0) == -1) {
					event.getChannel().sendMessage("You Lost...").queue();
					event.getChannel().sendMessage("Start a new game with '!play'").queue();
					//Show Dealers' hidden card. 
					Thread.sleep(3500);
					game.standNext();
			    	imageFunctions.drawHand(game.getDealerHand(), game.getCardHand(0));
				    sendFile("Let's see what the dealer got...", new File("images/output.png"),event);
					//TODO game.reset();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		
		
		
		if (args[0].equalsIgnoreCase(BlackJack.prefix + "s")){
		    if(game == null) {
		    	event.getChannel().sendTyping().queue();
				event.getChannel().sendMessage("Please start a game first!").queue();
				event.getChannel().sendMessage("Start a game with '!play'").queue();
		    }
		    
		    try {
				imageFunctions.drawHand(game.getDealerHand(), game.getCardHand(0));// Draw HiddenCard
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		  
		    
		    //TODO now shows dealers hand instant after player 1. FIX check if all players done.
		    try {
		    	game.standNext();
		    	imageFunctions.drawHand(game.getDealerHand(), game.getCardHand(0)); //Draw ShowCard
			    sendFile("You chose to STAND!", new File("images/output.png"),event);
			    Thread.sleep(3500);
			    int dealerDone = game.dealerTurn();
			    while(dealerDone != 1) {
			    	System.out.println("WHILE: dealderDone = " + dealerDone);
			    	imageFunctions.drawHand(game.getDealerHand(), game.getCardHand(0));
					sendFile("Dealer must Hit!", new File("images/output.png"),event);
					Thread.sleep(3500);
					dealerDone = game.dealerTurn();
				
			    }
			    String winner = "PUSH";
			    if(game.getPlayerSum(0) > game.getDealerSum()) winner = "Player"; 
			    else if(game.getPlayerSum(0) < game.getDealerSum()) winner = "Dealer"; 
			    event.getChannel().sendMessage("Winner is: " +winner + "!").queue();
			    
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	
		
		
		
	}
	
	
	
	
	



	/*  This methods begins a game of blackjack by dealing to each player, 
	 *  
	 */
	private void initializeGame(String[] args, GuildMessageReceivedEvent event) {
		if(args.length < 2 || new Integer(args[1]) < 1 || new Integer(args[1]) > 10) {
			event.getChannel().sendTyping().queue();
			event.getChannel().sendMessage("Usage - !play [1-10]").queue(); 
		}else {
			game = new BlackJackGame(new Integer(args[1]),8);
			event.getChannel().sendTyping().queue();
			event.getChannel().sendMessage("[!h]it or [!s]tand").queue();
		}
	}// End initializeGame

	
	/* Send a file to chat with specified message. 
	 *  Also include player score and dealer score. 
	 */
	public void sendFile(String txt, File f, GuildMessageReceivedEvent event) {
		int playerSum = game.getPlayerSum(0);
		int dealerSum = game.getDealerSum();
		String playerString = "" + playerSum;
		String dealerString = "" + dealerSum;
		if(dealerSum == -1) dealerString = "BUST!";
		if(playerSum == -1) playerString = "BUST!";
		if(txt!=null) event.getChannel().sendMessage(txt).queue();
		
		event.getChannel().sendMessage("Dealer score: " + game.getDealerSum()).addFile(f).queue();
		event.getChannel().sendMessage("Player score: " + playerString).queue();
	}//end sendFile






}
