package basePack;

import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


import java.awt.Color;

/**
 * @author Dallen Burks + Evan McElwain
 * Represents the collectible goal of the game
 */
public class Collectible extends Entity{
	
	
	
	private int xPos, yPos, Cwidth, Cheight;

	Color color = Color.RED;
	private BufferedImage Csprite;
	
	public Collectible(int xPos,int yPos,  int Cwidth, int Cheight) {
		
		this.xPos = xPos;
		this.yPos = yPos;
		this.xVelo = 0;
		this.yVelo = 0;
	    this.Cwidth = Cwidth;
	    this.Cheight = Cheight;
	     
	     
	     try {
	         Csprite = ImageIO.read(Collectible.class.getResource("Chicken Sandwich-1.png.png"));
	         spriteLoaded = true;
	         } catch (IOException e) {
	         	spriteLoaded = false;
	         }
	 }
	

 
 /*
 * 
 * 
 * 
 * 
 */
	boolean spriteLoaded = false;
 
 
 public void drawCollectible(Graphics g) {
 	
 	int drawX = xPos;
 	int drawY = yPos;
 	int drawHeight = Cheight;
 	int drawWidth = Cwidth;
 	
 	if (spriteLoaded) {
 		g.drawImage(Csprite, drawX, drawY, drawWidth,drawHeight, null );
 	} else {
 		  g.setColor(color);

 	}
   
 }
 
}