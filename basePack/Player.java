package basePack;


import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Graphics;

/**
 * @author Evan McElwain
 * Represents player in game
 */
public class Player extends Entity {
	
	//all subject to change
	private static final int XACCELERATION = 2;
	private static final int GACCELERATION = 1;

	private static final int JUMPACCELERATION = -12;
	private static final int MAXHORIZONTALVELO = 5;
	private boolean onGround;



	private static final int MAXVERTICALVELO = 100;
	private static final int XPLAYERSIZE = 10;
	private static final int YPLAYERSIZE = 20;
	
	public Player(int xPos,int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.xVelo = 0;
		this.yVelo = 0;
	}
	
	public void moveLeft() {
		if (xVelo - XACCELERATION < -MAXHORIZONTALVELO) {
			xVelo = -MAXHORIZONTALVELO;
		}
		else {
			xVelo -= XACCELERATION;
		}
	}
	
	public void moveRight() {
		if (xVelo+XACCELERATION > MAXHORIZONTALVELO) {
			xVelo = MAXHORIZONTALVELO;
		}
		else{
			xVelo += XACCELERATION;
		}
	}
	

	public void jump() {
		if(onGround) {
			yVelo += JUMPACCELERATION;
		}
	}
	
	public void stop() {
		xVelo = 0;
	}
	
	public void jump(List<GroundPlatform>  platforms) {
		if (onGround(platforms)) {
			yVelo -= JUMPACCELERATION;
		}

	}
	
	@Override
	public void update(List<GroundPlatform> platforms) {	
		//may need to add in friction decrease in xVelo, may be different based on whether in the air or on the ground	
		
		//update Positions
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
			onGround = false;
			yPos += yVelo;
			//apply gravity;
			if (yVelo + GACCELERATION > MAXVERTICALVELO) {
				yVelo = MAXVERTICALVELO;
			}
			else {
				yVelo += GACCELERATION;
			}
		}
		else {
			onGround = true;
			if(yVelo < 0) {
				yPos += yVelo;
			}
			//find the last place not in a block
			while (notInBlock(xPos,yPos,platforms)) {
				yPos++;
			}
			yPos--;
			//sets velo to zero after collision with block
			yVelo = 0;
			//don't need to apply gravity because on the ground
		}

	}
	
	private boolean notInBlock(int x, int y, List<GroundPlatform> platforms) {
		for (GroundPlatform g : platforms) {
			for (int i=0;i<=XPLAYERSIZE;i++) {
				for (int j=0;j<=YPLAYERSIZE;j++) {
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
		g.setColor(Color.DARK_GRAY);
		g.fillRect(xPos, yPos, XPLAYERSIZE, YPLAYERSIZE);
	}
	
	public boolean onGround(List<GroundPlatform> platforms) {
		for(GroundPlatform g : platforms) {
			if ((yPos + YPLAYERSIZE) == g.getY()) {
				if (((xPos + (XPLAYERSIZE/2)) > g.getX()) && ((xPos + (XPLAYERSIZE/2)) < (g.getX() + g.getWidth()))) {
					return true;
				}
			}
		}
		return false;
	}

}
