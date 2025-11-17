package basePack;

import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


import java.awt.Color;

public class GroundPlatform {
 
	private int x, y, width, height;
	Color color = Color.RED;
	private BufferedImage sprite;
	boolean spriteLoaded = false;
 
 public GroundPlatform(int x, int y, int width, int height) {
     this.x = x;
     this.y = y;
     this.width = width;
     this.height = height;
     
     try {
         sprite = ImageIO.read(GroundPlatform.class.getResource("New Piskel(2).png"));
         spriteLoaded = true;
     } catch (IOException e) {
    	 spriteLoaded = false;
     }
 }
 
 public int getX() {
	 return x;
 }
 
 public int getY() {
	 return y;
 }
 
 public int getHeight() {
	 return height;
 }
 
 public int getWidth() {
	 return width;
 }
 
 /**
	 * draws ground platform sprite if loaded or a red box if not
	 */
 public void draw(Graphics g) {
 	if (spriteLoaded) {
 		g.drawImage(sprite, x, y, width, height, null);
 	}
 	else {
 		g.setColor(color);
 		g.fillRect(x, y, width, height);

    }
   
 }

}