package basePack;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

/**
 * @author Evan McElwain
 * 
 * represents any non permanent element of the game, extended by Collectible, Player, and Enemy
 */
public abstract class Entity {
	protected int xPos;
	protected int yPos;
	protected int xVelo;
	protected int yVelo;
	protected int height;
	protected int width;
	//protected int xAcc;
	//protected int yAcc;
	
	protected int[] getPos() {
		return new int[]{xPos,yPos};
	}
	
	protected int[] getVel() {
		return new int[]{xVelo,yVelo};
	}
	
	/*protected int[] getAcc() {
		return new int[]{xAcc,yAcc};
	}*/
	
	protected void update(List<GroundPlatform> platforms) {
		xPos+=xVelo;
		yPos+=yVelo;
		//xVelo+=xAcc;
		//yVelo+=yAcc;
	}
	public void draw(Graphics g) {}
}
