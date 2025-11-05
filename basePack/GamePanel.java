package basePack;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;

import javax.swing.JPanel;

import basePack.KeyHandler;

public class GamePanel extends JPanel implements Runnable{
	private List<GroundPlatform> platforms;
	private ArrayList<Entity> things= new ArrayList<Entity>();
	private  Timer timer;
	public GamePanel() {
		
		
		
		
		
		this.setPreferredSize(new Dimension(windowWidth, windowHeight));
		this.setBackground(Color.white);
		this.setDoubleBuffered(true);
		this.addKeyListener(k);
		//this.addMouseListener(m);
		//this.addMouseMotionListener(mm);
		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
		this.setVisible(true);
		/*Jpanel panel1 = new Jpanel();
		panel1.setOpaque(false);*/
		
		
		
		// makes platforms
		 platforms = new ArrayList<>();

	        // Test platforms
	      platforms.add(new GroundPlatform(0, windowHeight-75, windowWidth, 75));
	      platforms.add(new GroundPlatform(300, 400, 250, 30));
	      platforms.add(new GroundPlatform(600, 300, 150, 30));
	      //Adds our Player
	      things.add(new Player(60, 0));
	      //Adds an Enemy
	      things.add(new Enemy(200,0));
	      //Creates and Starts Timer
	      timer = new Timer(30, e -> tick());
	      timer.start();
		}
	
	
	public void tick() {
		for (Entity e : things) {
			e.update(platforms);
		}
		repaint();
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		//closes game if you hit escape
		if(k.checkKey("escape") && gameThread!=null){
			gameThread.interrupt();
			gameThread = null;
		}  
		super.paintComponent(g);
		g2d = (Graphics2D)g;
		//draw stuff here or draw components here-------------------------------

		
		for(Entity i: things) {
	    	  //i.update(platforms);
	    	  i.draw(g2d);
	      }
		//System.out.println("5");
		
        // Draws platforms + sets their color
        for (GroundPlatform platform : platforms) {
            platform.draw(g2d);
        }
		
		g2d.dispose();
	}
	//gets screensize
	Dimension screenRes = Toolkit.getDefaultToolkit().getScreenSize();
	final int windowWidth = (int)screenRes.getWidth();
	final int windowHeight = (int)screenRes.getHeight();
	
	Graphics2D g2d;
	KeyHandler k = new KeyHandler();
	//MouseHandler m = new MouseHandler();
	//MouseMotionHandler mm = new MouseMotionHandler();
	//if we need mouseHandler i can probably write a better implementation
	Thread gameThread;
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
}
