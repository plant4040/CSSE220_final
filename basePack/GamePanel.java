package basePack;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import basePack.KeyHandler;

public class GamePanel extends JPanel implements Runnable{
	
	private List<GroundPlatform> platforms;
<<<<<<< HEAD
	
	private List<Collectible> collectables;
	
	int collectableSize  = 45;
=======
	private ArrayList<Entity> things= new ArrayList<Entity>();
	private  Timer timer;
	private Player player;
	private Graphics2D g2d;
	private Dimension screenRes = Toolkit.getDefaultToolkit().getScreenSize();
	final int windowWidth = (int)screenRes.getWidth();
	final int windowHeight = (int)screenRes.getHeight();
	Thread gameThread;
>>>>>>> refs/remotes/origin/main
	
	public GamePanel() {

		this.setPreferredSize(new Dimension(windowWidth, windowHeight));
		this.setBackground(Color.white);
		this.setDoubleBuffered(true);
<<<<<<< HEAD
		this.addKeyListener(k);
=======
	    
		//Initialize player
		player = new Player(150, 0, 10, 20);
	    
		//Add controls
		buildKeys();
		
>>>>>>> refs/remotes/origin/main
		//this.addMouseListener(m);
		//this.addMouseMotionListener(mm);
		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
		this.setVisible(true);
		/*Jpanel panel1 = new Jpanel();
		panel1.setOpaque(false);*/
		
		
		
		// makes platforms
		platforms = new ArrayList<>();

<<<<<<< HEAD
	        // Test platforms
	      platforms.add(new GroundPlatform(0, windowHeight/2, windowWidth, 75));
	      platforms.add(new GroundPlatform(300, 400, 250, 30));
	      platforms.add(new GroundPlatform(600, 300, 150, 30));
	      
	      
	     
	      
	      
	     collectables = new ArrayList<>();
	     collectables.add(new Collectible(40, 500,collectableSize, collectableSize));
	     collectables.add(new Collectible(40, 400,collectableSize, collectableSize));
	     collectables.add(new Collectible(40, 300,collectableSize, collectableSize));
		}
=======
	    // Adds platforms
	    platforms.add(new GroundPlatform(0, windowHeight-75, windowWidth, 75));
	    platforms.add(new GroundPlatform(300, 400, 250, 30));
	    platforms.add(new GroundPlatform(600, 300, 150, 30));
	    
	    //Adds our Player to list of entities
	    things.add(player);
	    
	    //Adds an Enemy
	    things.add(new Enemy(200,0,20,10));
	    things.add(new Enemy(400,0,20,10));
	    things.add(new Enemy(300,0,20,10));
	    
	    //Creates and Starts Timer
	    timer = new Timer(30, e -> tick());
	    timer.start();
	}
>>>>>>> refs/remotes/origin/main
	
<<<<<<< HEAD
	
	
=======
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
			e.update(platforms);
		}
		repaint();
	}
>>>>>>> refs/remotes/origin/main
	
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
		//draw stuff here or add components to draw here-------------------------------

		
<<<<<<< HEAD
=======
		for(Entity i: things) {
	    	  //i.update(platforms);
	    	  i.draw(g2d);
	      }
>>>>>>> refs/remotes/origin/main
		
        // Uses draw method in GroundPlatform class to make platforms
        for (GroundPlatform platform : platforms) {
            platform.draw(g);
            
        }
        
        
        for (Collectible collectable: collectables) {

			collectable.drawCollectible(g);
            
        }
		
        
        
        
        
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
	            }
	        }
	        });
	}
}
