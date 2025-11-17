package basePack;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JPanel;
import basePack.KeyHandler;

public class GamePanel extends JPanel implements Runnable{
	
	private List<GroundPlatform> platforms;
	private ArrayList<Entity> things= new ArrayList<Entity>();
	private Player player;
	private Graphics2D g2d;
	private Dimension screenRes = Toolkit.getDefaultToolkit().getScreenSize();
	private final int windowWidth = (int)screenRes.getWidth();
	private final int windowHeight = (int)screenRes.getHeight();
	private Thread gameThread;
	private KeyHandler k;
	private int lives= 5;
	private int target=10;
	private int score = 0;

	
	
	public GamePanel() {

		this.setPreferredSize(new Dimension(windowWidth, windowHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
	    
		//Initialize player
		player = new Player(150, 800, 40, 60,this);
	    
		//Add controls
		k = new KeyHandler();
		this.addKeyListener(k);

		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
		this.setVisible(true);
		setFocusable(true);
		requestFocusInWindow();
		
		// makes platforms
		platforms = new ArrayList<>();

	    // Adds platforms
	    platforms.add(new GroundPlatform(0, windowHeight-75, windowWidth, 75));
	    platforms.add(new GroundPlatform(350, 500, 300, 50));
	    platforms.add(new GroundPlatform(150, 650, 200, 50));
	    platforms.add(new GroundPlatform(750, 400, 200, 50));
	    platforms.add(new GroundPlatform(1150,450,300,50));
	    platforms.add(new GroundPlatform(1275,250,50,50));
	    platforms.add(new GroundPlatform(425,300,150,50));
	    platforms.add(new GroundPlatform(1050,windowHeight-325,50,250));
	    
	    //Adds our Player to list of entities
	    things.add(player);
	    
	    //Adds Enemies
	    things.add(new Enemy(420,350,60,60));
	    things.add(new Enemy(470,350,60,60));
	    things.add(new Enemy(1150,350,60,60));
	    
	    //Adds Collectibles
	    things.add(new Collectible (30,windowHeight-125,70,70));
	    things.add(new Collectible (290,windowHeight-125,70,70));
	    things.add(new Collectible (550,windowHeight-125,70,70));
	    things.add(new Collectible (800,windowHeight-300,70,70));
	    things.add(new Collectible (1280,windowHeight-125,70,70));
	    things.add(new Collectible (465,250,70,70));
	    things.add(new Collectible (215,600,70,70));
	    things.add(new Collectible(815,350,70,70));
	    things.add(new Collectible(1265,50,70,70));
	    things.add(new Collectible(1265,400,70,70));
	}
	
	//Quits game if escape is pressed
	private void quit() {
		if (gameThread != null) {
			gameThread.interrupt();
			gameThread = null;
		}
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2d = (Graphics2D)g;
		//draw stuff here or draw components here-------------------------------
		
		for(Entity i: things) {
	    	  //i.update(platforms);
	    	  i.draw(g2d);
	      }
		
        // Draws platforms + sets their color
        for (GroundPlatform platform : platforms) {
            platform.draw(g2d);
        }
		
        g.setColor(Color.CYAN);
		g.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
		g.drawString("Score: " + score, 10, 30);
		g.drawString("Lives: " + lives, 10, 65);
		g.drawString("Target: " + target, 10, 100);
		
		if (lives <= 0) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, windowWidth, windowHeight);
			g.setColor(Color.WHITE);
			g.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 100));
			g.drawString("GAME OVER", windowWidth/2 - 300, windowHeight/2);
			
		}
		if (score >= target) {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, windowWidth, windowHeight);
			g.setColor(Color.YELLOW);
			g.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 100));
			g.drawString("YOU WIN!", windowWidth/2 - 245, windowHeight/2);
		}
		g2d.dispose();
	}

	//sets up the thread for the game
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	//synchronizes events in game using the concept of "delta time" so that if you lower framerate you won't slow other functions of the game.
	@Override
	public void run() {
		long last_time = System.nanoTime();
		while(gameThread != null) {
			long time = System.nanoTime();
			int delta_time = (int)((time - last_time)/1000000);
			if(delta_time >= 10){
			      last_time = time;
			      requestFocusInWindow();
					if(k.checkKey("left")){
						player.moveLeft();
					}
					if(k.checkKey("right")){
						player.moveRight();
					}
					if(k.checkKey("up")){
						player.jump(platforms);
					}
					if(k.checkKey("down")) {
						player.collect(things);
					}
					if (k.checkKey("escape")) {
						quit();
					}
					for (Entity e: things) {
						e.update(platforms, things);
					}
			      repaint();
			}
		}
		//when game ends, game ends.
		System.exit(0);
	}
	
	
	public void increaseScore(int amount) {
	    score += amount;
	}
	
	public void hit() {
		lives--;
	}
	
}
