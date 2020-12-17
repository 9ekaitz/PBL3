package pbl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Canvas extends JPanel{

	bola Bola;
	
	public Canvas(bola Bola) {
		this.Bola = Bola;
		this.setPreferredSize(new Dimension(500,500));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D gr = (Graphics2D) g;
		gr.setColor(Color.white);
		gr.fillRect(0, 0, 500, 500);
		
		Bola.dibujar(g);
	}
	
}
