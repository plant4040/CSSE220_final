package basePack;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class Launcher {
	public static void main(String[] args) {
		JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setResizable(true);
        window.setTitle("Kernel's finger lickin' adventure");

	    GamePanel gamePanel = new GamePanel();
	    window.add(gamePanel);

	    window.setUndecorated(true);
	    window.setExtendedState(JFrame.MAXIMIZED_BOTH);


	    window.pack();

	    window.setLocationRelativeTo(null);
	    window.setVisible(true);

	    gamePanel.startGameThread();
	    
	    window.addWindowListener(new WindowAdapter() {
	    	public void windowClosing(WindowEvent e) {
	    		window.dispose();
            	System.exit(0);
	        }
	    });
	}
}
