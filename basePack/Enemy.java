package basePack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * @author seaworb
 * represents basic enemy
 */
public class Enemy extends Entity {

	private static final int GACCELERATION = 1;
	private static final int MAXHORIZONTALVELO = 5;
	private static final int MAXVERTICALVELO = 100;
	private boolean onGround;
	private BufferedImage sprite;
	boolean spriteLoaded = false;
	
	public Enemy(int xPos, int yPos, int height, int width) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.height = height;
		this.width = width;
		this.xVelo = 1;
		this.yVelo = 0;
		
		try {
	         sprite = ImageIO.read(GroundPlatform.class.getResource("BulldogEnemy.png"));
	         spriteLoaded = true;
	    } 
		catch (IOException e) {
	         spriteLoaded = false;
	    }
	}
	
	public void turnAround() {
		xVelo = -xVelo;
	}
	
	public boolean isGroundInFront(int x, int y, List<GroundPlatform> platforms) {
		//check if ground is in front to see if enemy should turn around
		if(xVelo < 0) {
			for (GroundPlatform g : platforms) {
				if (((x - width + 1) < (g.getX() + g.getWidth())) && ((x - width + 1) > (g.getX()))) {
					if(((y + height + 1) < (g.getY() + g.getHeight())) && ((y + height + 1) > (g.getY()))) {
						return true;
					}
				}
			}
		}
		else {
			for (GroundPlatform g : platforms) {
				if (((x + 2 * width - 1) < (g.getX() + g.getWidth())) && ((x + 2 * width - 1) > (g.getX()))) {
					if(((y + height + 1) < (g.getY() + g.getHeight())) && ((y + height + 1) > (g.getY()))) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	@Override
	public void update(List<GroundPlatform> platforms,ArrayList<Entity> e) {	
		//may need to add in friction decrease in xVelo, may be different based on whether in the air or on the ground	

		//update Positions
		if(!isGroundInFront(xPos, yPos, platforms) && onGround) {
			turnAround();
		}
		if (notInBlock(xPos + xVelo,yPos,platforms)) {
			xPos += xVelo;
		}
		else {
			//find the last place not in a block
			while (notInBlock(xPos,yPos,platforms)) {
				xPos++;
			}
			xPos--;
			//sets velo to zero after collision with block
			xVelo = 0;
		}
		if (notInBlock(xPos,yPos + yVelo,platforms)) {
			yPos += yVelo;
			onGround = false;
			//apply gravity;
			if (yVelo + GACCELERATION > MAXVERTICALVELO) {
				yVelo = MAXVERTICALVELO;
			}
			else {
				yVelo += GACCELERATION;
			}
		}
		else {
			//find the last place not in a block
			while (notInBlock(xPos,yPos,platforms)) {
				yPos++;
			}
			yPos--;
			//sets velo to zero after collision with block
			yVelo = 0;
			onGround = true;
			//don't need to apply gravity because on the ground
		}

	}
	
	private boolean notInBlock(int x, int y, List<GroundPlatform> platforms) {
		for (GroundPlatform g : platforms) {
			for (int i=0;i<=width;i++) {
				for (int j=0;j<=height;j++) {
					if (((x+i) < (g.getX() + g.getWidth())) && ((x+i) > (g.getX()))) {
						if ((y+j) < (g.getY() + g.getHeight()) && ((y+j) > (g.getY()))) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
	 	if (spriteLoaded) {
	 		g.drawImage(sprite, xPos, yPos, width,height, null);
	 	}
	 	else {
	 		g.setColor(Color.RED);
			g.fillRect(xPos, yPos, width, height);

	    }
		
	}
}
