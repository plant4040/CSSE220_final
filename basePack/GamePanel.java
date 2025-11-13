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
	private  Timer timer;
	private Player player;
	private Graphics2D g2d;
	private Dimension screenRes = Toolkit.getDefaultToolkit().getScreenSize();
	final int windowWidth = (int)screenRes.getWidth();
	final int windowHeight = (int)screenRes.getHeight();
	Thread gameThread;
	KeyHandler k;
	private int lives=5;
	private int target=10;
	private int score = 0;
	JLabel label;

	
	
	public GamePanel() {

		this.setPreferredSize(new Dimension(windowWidth, windowHeight));
		this.setBackground(Color.white);
		this.setDoubleBuffered(true);
	    
		//Initialize player
		player = new Player(150, 0, 30, 60,this);
	    
		//Add controls
		buildKeys();
//		k = new KeyHandler();
//		this.addKeyListener(k);
		
		//this.addMouseListener(m);
		//this.addMouseMotionListener(mm);
		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
		this.setVisible(true);
		/*Jpanel panel1 = new Jpanel();
		panel1.setOpaque(false);*/
		
		setFocusable(true);
		requestFocusInWindow();
		
		// makes platforms
		platforms = new ArrayList<>();

	    // Adds platforms
	    platforms.add(new GroundPlatform(0, windowHeight-75, windowWidth, 75));
	    platforms.add(new GroundPlatform(300, 400, 250, 30));
	    platforms.add(new GroundPlatform(600, 300, 150, 30));
	    
	    //Adds our Player to list of entities
	    things.add(player);
	    
	    //Adds an Enemy
	    things.add(new Enemy(200,0,60,60));
	    //things.add(new Enemy(400,0,60,60));
	    //things.add(new Enemy(300,0,60,60));
	    
	    //Adds Collectibles
	    things.add(new Collectible (50,windowHeight-125,100,100));
	    things.add(new Collectible (400,windowHeight-125,100,100));
	    
//	    this.setLayout(new FlowLayout());
//	    lives = 3;
//		target = 10;
//		score = 0;
//		label = new JLabel();
//		label.setText("<html>Lives: " + lives + "<br />Score: " + score + "<br />Target: " +  "</HTML>");
//		this.add(label);
	    
	    //Creates and Starts Timer
	    timer = new Timer(30, e -> tick());
	    timer.start();
	}
	
	/**
	 * Updates all entities
	 * 
	 */
	public void tick() {
		requestFocusInWindow();
//		if(k.checkKey("w")) {
//			player.jump();
//		}
//		if(k.checkKey("a") && !k.checkKey("d")) {
//			player.moveLeft();
//		}
//		if(k.checkKey("d") && !k.checkKey("a")) {
//			player.moveRight();
//		}
//		if((!k.checkKey("d") && !k.checkKey("a")) || (k.checkKey("d") && k.checkKey("a"))) {
//			player.stop();
//		}
//		for (Entity e : things) {
//			e.update(platforms);
//		}
		
		for (Entity e: things) {
			e.update(platforms, things);
		}
		repaint();
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
		
        g.setColor(Color.BLACK);
		
		g.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
		g.drawString("Score: " + score, 10, 30);
		g.drawString("Lives: " + lives, 10, 65);
		g.drawString("Target: " + target, 10, 100);
		
		g2d.dispose();
	}

	

	//MouseHandler m = new MouseHandler();
	//MouseMotionHandler mm = new MouseMotionHandler();
	//if we need mouseHandler i can probably write a better implementation

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
			      /*if(k.checkKey()) {
			    	  
			      }*/
			      repaint();
			}
		}
		//when game ends, game ends.
		System.exit(0);
	}
	
	//adds controls
	private void buildKeys() {
		this.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyPressed(KeyEvent e) {
	            switch (e.getKeyCode()) {
	            	case KeyEvent.VK_ESCAPE -> quit();
	                case KeyEvent.VK_LEFT  -> player.moveLeft();
	                case KeyEvent.VK_RIGHT -> player.moveRight();
	                case KeyEvent.VK_UP -> player.jump(platforms);
	                case KeyEvent.VK_DOWN -> player.collect(things);
	            }
	        }
	        });
	}
	
	public void increaseScore(int amount) {
	    score += amount;
	}
	
	public void hit() {
		lives--;
	}
	
}
