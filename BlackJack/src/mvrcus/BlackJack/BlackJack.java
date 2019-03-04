package mvrcus.BlackJack;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;

public class BlackJack {
	
	public static JDA jda;
	public static String prefix = "!";
	
	
	public static void main(String[] args) {
       try {
		jda = new JDABuilder("NTUyMjI0NjM2MDE2OTE4NTcx.D18qeQ.HRgboujxDmn1xAjhnRzIswBxcdU").build();
	} catch (LoginException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       
       jda.getPresence().setStatus(OnlineStatus.IDLE);
       jda.getPresence().setGame(Game.playing("BlackJack"));
       
       jda.addEventListener(new Commands());
	}
	
	// yeh
}
