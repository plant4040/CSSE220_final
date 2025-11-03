package basePack;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JPanel;

import basePack.KeyHandler;

public class GamePanel extends JPanel implements Runnable{
	
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
		//draw stuff here or add components to draw here-------------------------------
		
		
		
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
