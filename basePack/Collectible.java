package basePack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;

/**
 * @author Evan McElwain
 * Represents the collectible goal of the game
 */
public class Collectible extends Entity{
	
	private ImageIcon sprite;
	boolean spriteLoaded = false;
	
	public Collectible(int xPos,int yPos, int width, int height) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
		this.xVelo = 0;
		this.yVelo = 0;
		try {
		    sprite = new ImageIcon(Player.class.getResource("FishCollect.gif"));
		    spriteLoaded = true;
		}
		catch (Exception e) {
		    System.out.println("Error loading player images: " + e);
		}
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
        if (spriteLoaded && sprite.getImage() != null) {
            g.drawImage(sprite.getImage(), xPos, yPos, width, height, null);
        } else {
            g2d.setColor(Color.YELLOW);
            g2d.fillRect(xPos, yPos, width, height);
        }
	   
	 }
	
	
	
	
}
