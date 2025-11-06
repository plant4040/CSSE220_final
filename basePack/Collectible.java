package basePack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Evan McElwain
 * Represents the collectible goal of the game
 */
public class Collectible extends Entity{
	
	private BufferedImage sprite;
	boolean spriteLoaded = false;
	
	public Collectible(int xPos,int yPos, int width, int height) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
		this.xVelo = 0;
		this.yVelo = 0;
		
		try {
	         sprite = ImageIO.read(GroundPlatform.class.getResource("Collectible.png")); // Once sprite is added change na
	         spriteLoaded = true;
	    } 
		catch (IOException e) {
	         spriteLoaded = false;
	    }
	}
	
	public void draw(Graphics g) {
	 	
	 	if (spriteLoaded) {
	 		g.drawImage(sprite, xPos, yPos, width,height, null);
	 	}
	 	else {
	 		g.setColor(Color.yellow);
	 		g.fillRect(xPos, yPos, width, height);

	    }
	   
	 }
	
	
	
	
}
