package basePack;

/**
 * @author Evan McElwain
 * Represents player in game
 */
public class Player extends Entity {
	
	//all subject to change
<<<<<<< HEAD
	private static final int XACCELERATION = 5;
	private static final int GACCELERATION = 10;
	private static final int JUMPACCELERATION = 50;
	private static final int MAXHORIZONTALVELO = 20;
	private static final int MAXVERTICALVELO = 100;
	private static final int PLAYERHEIGHT = 0;
	private static final int PLAYERWIDTH = 0;
=======
	private static final int XACCELERATION = 2;
	private static final int GACCELERATION = 1;

	private static final int JUMPACCELERATION = -12;
	private static final int MAXHORIZONTALVELO = 5;



	private static final int MAXVERTICALVELO = 100;
>>>>>>> refs/remotes/origin/main
	
	public Player(int xPos,int yPos, int width, int height) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
		this.xVelo = 0;
		this.yVelo = 0;
	}
	
<<<<<<< HEAD
	public void MoveLeft() {
=======
	/**
	 * updates player's velocity if left arrow is clicked
	 */
	public void moveLeft() {
>>>>>>> refs/remotes/origin/main
		if (xVelo - XACCELERATION < -MAXHORIZONTALVELO) {
			xVelo = -MAXHORIZONTALVELO;
		}
		else {
			xVelo -= XACCELERATION;
		}
	}
	
<<<<<<< HEAD
	public void MoveRight() {
=======
	
	/**
	 * updates player's velocity if right arrow is clicked
	 */
	public void moveRight() {
>>>>>>> refs/remotes/origin/main
		if (xVelo+XACCELERATION > MAXHORIZONTALVELO) {
			xVelo = MAXHORIZONTALVELO;
		}
		else{
			xVelo += XACCELERATION;
		}
	}
	
<<<<<<< HEAD
	public void Jump() {
		yVelo += JUMPACCELERATION;
	}
=======
>>>>>>> refs/remotes/origin/main
	
<<<<<<< HEAD
	public void update() {
=======
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
			yVelo += JUMPACCELERATION;
		}

	}
	
	/**
	 * Updates players position and movement after being called by timer,
	 * handles vertical and horizontal collisions with blocks
	 */
	@Override
	public void update(List<GroundPlatform> platforms) {	
		//may need to add in friction decrease in xVelo, may be different based on whether in the air or on the ground	
		
>>>>>>> refs/remotes/origin/main
		//update Positions
<<<<<<< HEAD
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
=======
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
>>>>>>> refs/remotes/origin/main

<<<<<<< HEAD
=======
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
>>>>>>> refs/remotes/origin/main
	}
	
<<<<<<< HEAD
=======
	/**
	 * draws player
	 */
	public void draw(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(xPos, yPos, width, height);
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

>>>>>>> refs/remotes/origin/main
}
