package pbl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class bola {
	int x, y;
	
	
	public void dibujar(Graphics g) {
		Graphics2D graphics2d = (Graphics2D) g;
		graphics2d.setColor(Color.red);
		graphics2d.fillOval(x+250, y+250, 50, 50);
	}
}
