package basePack;

import java.util.ArrayList;

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
	private static final int PLAYERHEIGHT = 20;
	private static final int PLAYERWIDTH = 10;
	
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
	
	public void update(ArrayList<GroundPlatform> platforms) {
		//update Positions
//		if (notInBlockX(xPos + xVelo,yPos)) {
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
	
//	private boolean notInBlockX(int x, int y, ArrayList<GroundPlatform> platforms) {
//		for (GroundPlatform g : platforms) {
//			if (x )
//		}
//	}
	
}
