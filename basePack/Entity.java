package basePack;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
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
	
	public int getxPos() {
		return xPos;
	}
	
	public int getyPos() {
		return yPos;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	/**
	 * basic update method for changing position
	 * @param platforms
	 * @param things
	 */
	protected void update(List<GroundPlatform> platforms, ArrayList<Entity> things) {
		xPos+=xVelo;
		yPos+=yVelo;
	}
	
	/**
	 * basic draw method
	 * @param g
	 */
	public void draw(Graphics g) {}
}
