package basePack;

<<<<<<< HEAD
import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


import java.awt.Color;
=======
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
>>>>>>> refs/remotes/origin/main

/**
 * @author Dallen Burks + Evan McElwain
 * Represents the collectible goal of the game
 */
public class Collectible extends Entity{
	
<<<<<<< HEAD
	
	
	private int xPos, yPos, Cwidth, Cheight;

	Color color = Color.RED;
	private BufferedImage Csprite;
	
	public Collectible(int xPos,int yPos,  int Cwidth, int Cheight) {
		
=======
	private BufferedImage sprite;
	boolean spriteLoaded = false;
	
	public Collectible(int xPos,int yPos, int width, int height) {
>>>>>>> refs/remotes/origin/main
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
		this.xVelo = 0;
		this.yVelo = 0;
<<<<<<< HEAD
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
=======
		
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
>>>>>>> refs/remotes/origin/main
