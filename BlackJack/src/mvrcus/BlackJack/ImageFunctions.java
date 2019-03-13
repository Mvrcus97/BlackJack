package mvrcus.BlackJack;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import mvrcus.BlackJack.Deck.Card;
import mvrcus.BlackJack.ImageFunctions.imageFunctions;

public class ImageFunctions {
	public static class imageFunctions{
		
		public static void drawPlayerHand(CardHand ch, BufferedImage tableImg) throws IOException {
			System.out.println("--draw player hand--");
			String[] cards = ch.toStringArray();
			File f;
			int hSpaceSize = 75;
			BufferedImage cardImg;
			for( int i = 0; i < cards.length; i++) {
				System.out.println(" import path: images/"+cards[i]+".png");
				f = new File("images/"+cards[i]+".png");
				cardImg = ImageIO.read(f);
				tableImg = imageFunctions.drawCardOnTable(cardImg, tableImg, (415 + i*hSpaceSize), 385, 150);
				
			}
		    File fo = new File("images/output.png");
		    ImageIO.write(tableImg, "PNG", fo);
			 
		}
		
		
		
		public static void drawHand(DealerHand dh, CardHand ph) throws IOException {
			System.out.println("--draw entire hand--");
			boolean hidden = dh.isHidden();
			
			String[] cardsD = dh.toStringArray();
			String[] cardsP = ph.toStringArray();
			File f1,f2;
			int hSpaceSize = 108;
			BufferedImage cardImg;
			BufferedImage tableImg = ImageIO.read(new File("images/output.png"));
			//Draw Dealer Hand. 
			for( int i = 0; i < cardsD.length; i++) {
				System.out.println(" import path: images/"+cardsD[i]+".png");
				if(i == 1 && hidden) f1 = new File("images/card_back_black.png");
				
				else f1 = new File("images/"+cardsD[i]+".png");
				cardImg = ImageIO.read(f1);
				tableImg = imageFunctions.drawCardOnTable(cardImg, tableImg, (500 - i*hSpaceSize), 45, 150);
			}
			//Draw Player Hand. 
			imageFunctions.drawPlayerHand(ph, tableImg);
		}//end drawHand
		
		
		
		
		
		
		
		
		
		/*     
		 *		 This method drawn an image on top of another image, at location (x,y). 
		 */
		private BufferedImage drawImage(BufferedImage smaller, BufferedImage larger, int x, int y) {
			larger.getGraphics().drawImage(smaller, x, y, null);
			return larger; 
	    }// end drawImage
		
		
		
		/**
		 * Takes a BufferedImage and resizes it according to the provided targetSize
		 *
		 * @param src the source BufferedImage
		 * @param targetSize maximum height (if portrait) or width (if landscape)
		 * @return a resized version of the provided BufferedImage
		 */
		private static BufferedImage resize(BufferedImage src, int targetSize) {
		    if (targetSize <= 0) {
		        return src; //this can't be resized
		    }
		    int targetWidth = targetSize;
		    int targetHeight = targetSize;
		    float ratio = ((float) src.getHeight() / (float) src.getWidth());
		    if (ratio <= 1) { //square or landscape-oriented image
		        targetHeight = (int) Math.ceil((float) targetWidth * ratio);
		    } else { //portrait image
		        targetWidth = Math.round((float) targetHeight / ratio);
		    }
		    BufferedImage bi = new BufferedImage(targetWidth, targetHeight, src.getTransparency() == Transparency.OPAQUE ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB);
		    Graphics2D g2d = bi.createGraphics();
		    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR); //produces a balanced resizing (fast and decent quality)
		    g2d.drawImage(src, 0, 0, targetWidth, targetHeight, null);
		    g2d.dispose();
		    return bi;
		}
		
		/*     
		 *		 This method drawn a card on top of the table, at location (x,y). 
		 */
		public static BufferedImage drawCardOnTable(BufferedImage smaller, BufferedImage larger, int x, int y, int targetSize) {
			smaller = resize(smaller, targetSize);
			larger.getGraphics().drawImage(smaller, x, y, null);
			return larger; 
	    }// end drawCardOnTable
		
		
		
		
	}



}
