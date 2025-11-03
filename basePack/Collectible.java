package basePack;

/**
 * @author Evan McElwain
 * Represents the collectible goal of the game
 */
public class Collectible extends Entity{
	
	public Collectible(int xPos,int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.xVelo = 0;
		this.yVelo = 0;
	}
}
