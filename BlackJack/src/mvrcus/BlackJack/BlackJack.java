package mvrcus.BlackJack;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.security.auth.login.LoginException;

import mvrcus.BlackJack.ImageFunctions.functions;
import mvrcus.BlackJack.ImageFunctions.imageFunctions;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;

public class BlackJack {
	
	public static JDA jda;
	public static String prefix = "!";
	
	
	public static void main(String[] args) throws IOException {
       try {
		jda = new JDABuilder("NTUyMjI0NjM2MDE2OTE4NTcx.D18qeQ.HRgboujxDmn1xAjhnRzIswBxcdU").build();
	} catch (LoginException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       
       jda.getPresence().setStatus(OnlineStatus.IDLE);
       jda.getPresence().setGame(Game.playing("BlackJack"));
       
       jda.addEventListener(new Commands());
       
       /* TODO PICTURE TESTING */
    // Open a JPEG file, load into a BufferedImage.
       
       BufferedImage card = ImageIO.read(new File("images/6_of_clubs.png"));
       BufferedImage table = ImageIO.read(new File("images/table1.jpg"));
       File f = new File("images/output.png");
       
      table =  imageFunctions.drawCardOnTable(card, table, 500, 385, 150);
     
       
      
       ImageIO.write( table, "PNG", f);
       
   
  
 
	}
	
}// end BlackJack
 