package basePack;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JPanel;

import basePack.KeyHandler;

public class GamePanel extends JPanel implements Runnable{
	@Override
	protected void paintComponent(Graphics g) {
		if(k.checkKey("escape") && gameThread!=null){
			gameThread.interrupt();
			gameThread = null;
		}  
			super.paintComponent(g);
			g2d = (Graphics2D)g;
	}

	Dimension screenRes = Toolkit.getDefaultToolkit().getScreenSize();
	final int windowWidth = (int)screenRes.getWidth();
	final int windowHeight = (int)screenRes.getHeight();
	Graphics2D g2d;
	KeyHandler k = new KeyHandler();
	//MouseHandler m = new MouseHandler();
	//MouseMotionHandler mm = new MouseMotionHandler();
	//if we need mouseHandler i can probably write a better implementation
	Thread gameThread;
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

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
		System.exit(0);
	}
}
