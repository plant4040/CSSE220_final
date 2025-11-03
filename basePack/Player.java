package basePack;

/**
 * @author Evan McElwain
 * Represents player in game
 */
public class Player extends Entity {
	
	public Player(int xPos,int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.xVelo = 0;
		this.yVelo = 0;
	}
	
	
}
