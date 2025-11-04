package basePack;


import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;


import java.awt.Color;
/**
 * @author Dallen Burks
 * Represents the platform goal of the game
 */
public class GroundPlatform {
 
	private int x, y, width, height;
	Color color = Color.RED;
	private BufferedImage sprite;
 
 /*
 * 
 * 
 * 
 * 
 */
	boolean spriteLoaded = false;
 
 public GroundPlatform(int x, int y, int width, int height) {
     this.x = x;
     this.y = y;
     this.width = width;
     this.height = height;
     
     
     
     try {
         sprite = ImageIO.read(GroundPlatform.class.getResource("Grass Block-1.png.png"));
         spriteLoaded = true;
         } catch (IOException e) {
         	spriteLoaded = false;
         }
 }
 
 
 
 /*
  * draw method for ground platform
  */
 public void draw(Graphics g) {
 	
 	int drawX = x;
 	int drawY = y;
 	int drawHeight = height;
 	int drawWidth = width;
 	
 	if (spriteLoaded) {
 		g.drawImage(sprite, drawX, drawY, drawWidth,drawHeight, null );
 	} else {
 		  g.setColor(color);

 	}
   
 }
 
 
 
 
// Current stand in for Color 
// public void draw(Graphics g) {
//     g.setColor(Color.RED);
//     g.fillRect(x, y, width, height);
// }
//}

}