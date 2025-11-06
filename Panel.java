import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


public class Panel extends JPanel{
	
	private List<GroundPlatform> platforms;
	
	public Panel() {
		 platforms = new ArrayList<>();

	        // Test platforms
	      platforms.add(new GroundPlatform(50, 500, 200, 30));
	      platforms.add(new GroundPlatform(300, 400, 250, 30));
	      platforms.add(new GroundPlatform(600, 300, 150, 30));
		
	}
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Set the background color
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Draws platforms + sets their color
        for (GroundPlatform platform : platforms) {
            platform.draw(g);
        }
	}
}
