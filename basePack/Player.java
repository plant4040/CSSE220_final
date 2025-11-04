package basePack;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author Evan McElwain
 * Represents player in game
 */
public class Player extends Entity {
	
	//all subject to change
	private static final int XACCELERATION = 5;
	private static final int GACCELERATION = 10;
	private static final int JUMPACCELERATION = 50;
	private static final int MAXHORIZONTALVELO = 20;
	private static final int MAXVERTICALVELO = 100;
	private boolean onGround;
	private static final int XPLAYERSIZE = 10;
	private static final int YPLAYERSIZE = 20;
	
	public Player(int xPos,int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.xVelo = 0;
		this.yVelo = 0;
	}
	
	public void MoveLeft() {
		if (xVelo - XACCELERATION < -MAXHORIZONTALVELO) {
			xVelo = -MAXHORIZONTALVELO;
		}
		else {
			xVelo -= XACCELERATION;
		}
	}
	
	public void MoveRight() {
		if (xVelo+XACCELERATION > MAXHORIZONTALVELO) {
			xVelo = MAXHORIZONTALVELO;
		}
		else{
			xVelo += XACCELERATION;
		}
	}
	
	public void Jump() {
		yVelo += JUMPACCELERATION;
	}
	
	public void update() {
		xPos += xVelo;
		yVelo-=GACCELERATION;
		
		//may need to add in friction decrease in xVelo, may be different based on whether in the air or on the ground
		
		//update Positions
//		if (notInBlockX(xPos + xVelo)) {
//			xPos += xVelo;
//		}
//		else {
//			//find the last place not in a block
//			while (notInBlockX(xPos)) {
//				xPos++;
//			}
//			xPos--;
//			//sets velo to zero after collision with block
//			xVelo = 0;
//		}
//		if (notInBlockY(yPos + yVelo)) {
//			yPos += yVelo;
//			//apply gravity;
//			if (yVelo - GACCELERATION < -MAXVERTICALVELO) {
//				yVelo = -MAXVERTICALVELO;
//			}
//			else {
//				yVelo -= GACCELERATION;
//			}
//		}
//		else {
//			//find the last place not in a block
//			while (notInBlockY(yPos)) {
//				yPos++;
//			}
//			yPos--;
//			//sets velo to zero after collision with block
//			yVelo = 0;
//			//don't need to apply gravity because on the ground
//		}
//		//may need to add in friction decrease in xVelo, may be different based on whether in the air or on the ground

	}
	public void draw(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(xPos-XPLAYERSIZE/2, yPos-YPLAYERSIZE/2, XPLAYERSIZE, YPLAYERSIZE);
	}
}
