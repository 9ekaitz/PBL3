package pbl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Bola {
	
	int x, y;
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void dibujar(Graphics g) {
		Graphics2D graphics2d = (Graphics2D) g;
		graphics2d.setColor(Color.red);
		graphics2d.fillOval(this.x+250, this.y+250, 50, 50);
	}
}
