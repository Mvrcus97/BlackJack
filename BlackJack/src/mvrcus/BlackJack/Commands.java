package mvrcus.BlackJack;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter {
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String[] args = event.getMessage().getContentRaw().split("//s+");
		
		if (args[0].equalsIgnoreCase(BlackJack.prefix + "info")){
			event.getChannel().sendTyping().queue();
			event.getChannel().sendMessage("Hey, whassssup?").queue();
		}
	}
}
