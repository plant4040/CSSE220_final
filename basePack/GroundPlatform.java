package basePack;

import java.awt.Graphics;
import java.awt.Color;

public class GroundPlatform {
 private int x, y, width, height;

 public GroundPlatform(int x, int y, int width, int height) {
     this.x = x;
     this.y = y;
     this.width = width;
     this.height = height;
 }

 public void draw(Graphics g) {
     g.setColor(Color.DARK_GRAY);
     g.fillRect(x, y, width, height);
 }
}

