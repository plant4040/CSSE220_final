package basePack;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

/**
 * @author seaworb
 * represents basic enemy
 */
public class Enemy extends Entity {

	private static final int GACCELERATION = 1;
	private static final int MAXHORIZONTALVELO = 15;
	private static final int MAXVERTICALVELO = 100;
	private static final int XENEMYSIZE = 10;
	private static final int YENEMYSIZE = 20;
	private boolean onGround;
	
	public Enemy(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.xVelo = 5;
		this.yVelo = 0;
	}
	
	public void turnAround() {
		xVelo = -xVelo;
	}
	
	public boolean isGroundInFront(int x, int y, List<GroundPlatform> platforms) {
		//check if ground is in front to see if enemy should turn around
		if(xVelo < 0) {
			for (GroundPlatform g : platforms) {
				if (((x - XENEMYSIZE + 1) < (g.getX() + g.getWidth())) && ((x - XENEMYSIZE + 1) > (g.getX()))) {
					if(((y + YENEMYSIZE + 1) < (g.getY() + g.getHeight())) && ((y + YENEMYSIZE + 1) > (g.getY()))) {
						return true;
					}
				}
			}
		}
		else {
			for (GroundPlatform g : platforms) {
				if (((x + 2 * XENEMYSIZE - 1) < (g.getX() + g.getWidth())) && ((x + 2 * XENEMYSIZE - 1) > (g.getX()))) {
					if(((y + YENEMYSIZE + 1) < (g.getY() + g.getHeight())) && ((y + YENEMYSIZE + 1) > (g.getY()))) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	@Override
	public void update(List<GroundPlatform> platforms) {	
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
			for (int i=0;i<=XENEMYSIZE;i++) {
				for (int j=0;j<=YENEMYSIZE;j++) {
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
		g.setColor(Color.RED);
		g.fillRect(xPos, yPos, XENEMYSIZE, YENEMYSIZE);
	}
}
