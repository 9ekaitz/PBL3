package pbl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Canvas extends JPanel{

	Bola bola1,bola2;
	
	public Canvas(Bola bola1, Bola bola2) {
		this.bola1 = bola1;
		this.bola2 = bola2;
		this.setPreferredSize(new Dimension(500,500));
	}


	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D gr = (Graphics2D) g;
		gr.setColor(Color.white);
		gr.fillRect(0, 0, 500, 500);
		
		bola1.dibujar(g);
		bola2.dibujar(g);
	}
	
}
