package basePack;


import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import basePack.KeyHandler;

/**
 * @author Evan McElwain
 * Represents player in game
 */
public class Player extends Entity {
	
	//all subject to change
	private static final int XACCELERATION = 10;	private static final int GACCELERATION = 5;

	private static final int JUMPACCELERATION = -165;
	private static final int MAXHORIZONTALVELO = 40;
	private static final int VERTICALKNOCKBACK = 40;
	private static final int INVINCIBILITY = 120;
	private KeyHandler k;
	private GamePanel scoring;
	private int iFrames;
	private static final int MAXVERTICALVELO = 1000;
	private BufferedImage sprite;
	boolean spriteLoaded = false;
	
	public Player(int xPos,int yPos, int width, int height, GamePanel scoring) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
		this.xVelo = 0;
		this.yVelo = 0;
		this.k=new KeyHandler();
		iFrames = 0;
		this.scoring = scoring;
		
		try {
	         sprite = ImageIO.read(GroundPlatform.class.getResource("ColonelSanders.png")); // Once sprite is added change na
	         spriteLoaded = true;
	    } 
		catch (IOException e) {
	         spriteLoaded = false;
	    }
	}
	
	/**
	 * updates player's velocity if left arrow is clicked
	 */
	public void moveLeft() {
		if (xVelo - XACCELERATION < -MAXHORIZONTALVELO) {
			xVelo = -MAXHORIZONTALVELO;
		}
		else {
			xVelo -= XACCELERATION;
		}
	}
	
	
	/**
	 * updates player's velocity if right arrow is clicked
	 */
	public void moveRight() {
		if (xVelo+XACCELERATION > MAXHORIZONTALVELO) {
			xVelo = MAXHORIZONTALVELO;
		}
		else{
			xVelo += XACCELERATION;
		}
	}
	
	
	/**
	 * brings player to a stop horizontally
	 */
	public void stop() {
		xVelo = 0;
	}
	
	/**
	 * updates vertical velocity if up button is pressed and the player is on the ground
	 * @param platforms to check if on the ground
	 */
	public void jump(List<GroundPlatform>  platforms) {
		if (onGround(platforms)) {
			yVelo = JUMPACCELERATION;
		}

	}
	
	/**
	 * Updates players position and movement after being called by timer,
	 * handles vertical and horizontal collisions with blocks
	 */
	public void update(List<GroundPlatform> platforms, ArrayList<Entity> things) {	
		//may need to add in friction decrease in xVelo, may be different based on whether in the air or on the ground	
				// Horizontal movement and collision
				// Horizontal movement and collision
				if (notInBlock(xPos + xVelo/10, yPos, platforms)) {
				    xPos += xVelo/10;
				    if (xPos + width > ((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth())) && xVelo > 0) {
				    	xPos = 0;
				    }
				    else if (xPos < 0 && xVelo <0) {
				    	xPos = ((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()));
				    }
				    if(!notInBlock(xPos + xVelo/10, yPos+2, platforms)) {
				        if(Math.abs(xVelo)-Math.abs(xVelo)/10 > 0) {		
				            xVelo = (int)((Math.abs(xVelo)-Math.abs(xVelo)/10)*xVelo/Math.abs(xVelo));
				        } else {
				            xVelo = 0;
				        }
				    }
				}
				else {   // <-- moved out of inner if, now correctly tied to the outer one
				    // resolve collision depending on direction
				    if (xVelo > 0) { // moving right
				        while (notInBlock(xPos + 1, yPos, platforms)) {
				            xPos++;
				        }
				        xPos--; // stop before entering block
				    } else if (xVelo < 0) { // moving left
				        while (notInBlock(xPos - 1, yPos, platforms)) {
				            xPos--;
				        }
				        xPos++; // stop before entering block
				    }
				    xVelo = 0;
				}
		 
				if (notInBlock(xPos,yPos + yVelo/10,platforms)) {
					yPos += yVelo/10;
					//apply gravity;
					if (yVelo + GACCELERATION > MAXVERTICALVELO) {
						yVelo = MAXVERTICALVELO;
					}
					else {
						yVelo += GACCELERATION;
					}
				}
				else {
					if(yVelo < 0) {
						yPos += yVelo/10;
					}
					//find the last place not in a block
					if (yVelo>0) {
						while (notInBlock(xPos,yPos,platforms)) {
							yPos+=1;
						}
						yPos-=1;
						//sets velo to zero after collision with block
						yVelo = 0;
					}
					else if (yVelo<0) { //Accounts for bumping head above
						while (notInBlock(xPos,yPos,platforms)) {
							yPos-=1;
						}
						yPos+=10;
						//sets velo to zero after collision with block
						yVelo = 0;
					}
					//don't need to apply gravity because on the ground
				}
		
				//Checks for collisions with enemy and whether invincible
				if (inEnemy(things) && iFrames == 0) {
					scoring.hit();
					iFrames = INVINCIBILITY;
					if (xVelo >= 0) {
						xVelo = -MAXHORIZONTALVELO;
						yVelo -= VERTICALKNOCKBACK;
					}
					else {
						xVelo = MAXHORIZONTALVELO;
					}
					//Add code to update lives
				}		
		
		//Updates iFrames
		if (iFrames > 0) {
			iFrames--;
		}

	}
	
	/**
	 * checks if a player is inside a block for a given position to handle collisions
	 * @param x
	 * @param y
	 * @param platforms
	 * @return whether the player is not in a block
	 */
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
	
	/**
	 * draws player
	 */
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
	 	if (spriteLoaded) {
	 		g.drawImage(sprite, xPos, yPos, width,height, null);
	 	}
	 	else {
	 		g2d.setColor(Color.DARK_GRAY);
	 		g2d.fillRect(xPos, yPos, width, height);

	    }
	}
	
	/**
	 * checks if player is on the ground
	 * @param platforms
	 * @return whether player is on the ground
	 */
	public boolean onGround(List<GroundPlatform> platforms) {
		for(GroundPlatform g : platforms) {
			if ((yPos + height) == g.getY()) {
				if (((xPos + (width/2)) > g.getX()) && ((xPos + (width/2)) < (g.getX() + g.getWidth()))) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * returns the coin to be collected if hitboxes overlap, otherwise returns null 
	 * @param things
	 */
	public Entity inCollectible(ArrayList<Entity> es) {
		for (Entity e : es) {
			String type = e.getClass().getName();
			if (type.equals("basePack.Collectible")) {
				for (int i=0; i<width;i++) {
					for (int j=0;j<height;j++) {
						if ((xPos+i) <= (e.getxPos() + e.getWidth()) && (xPos+i) >= e.getxPos()){
							if ((yPos+j) <= (e.getyPos() + e.getHeight()) && (yPos+j) >= e.getyPos()) {
								return e;
							}
						}
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * Called when player tries to collect coin, removes coin when successful
	 * @param things
	 */
	public void collect(ArrayList<Entity> things) {
		Entity e = inCollectible(things);
		if (!(e == null)) {
			things.remove(e);
			scoring.increaseScore(1);
		}
	}
	
	public boolean inEnemy(ArrayList<Entity> things) {
		for (Entity e: things) {
			if (e.getClass().getName().equals("basePack.Enemy")){
			for (int i=0;i<width;i++) {
				for (int j=0;j<height;j++) {
					if ((xPos+i) <= (e.getxPos() + e.getWidth()) && (xPos+i) >= e.getxPos()){
						if ((yPos+j) <= (e.getyPos() + e.getHeight()) && (yPos+j) >= e.getyPos()) {
							return true;
						}
					}
				}
			}
			}
		}
		return false;
	}
	

}
