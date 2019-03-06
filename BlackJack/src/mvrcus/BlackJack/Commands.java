package mvrcus.BlackJack;

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
			if(args.length < 2 || new Integer(args[1]) < 1 || new Integer(args[1]) > 10) {
				event.getChannel().sendTyping().queue();
				event.getChannel().sendMessage("Usage - !play [1-10]").queue(); 
			}else {
				game = new BlackJackGame(new Integer(args[1]),8);
			    String round = game.toString();
				
				event.getChannel().sendTyping().queue();
				event.getChannel().sendMessage(round).queue(); 
			}
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
		}
	
	}
}
