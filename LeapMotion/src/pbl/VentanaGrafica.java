package pbl;

import java.awt.Container;

import javax.swing.JFrame;

public class VentanaGrafica {
	
	Canvas canvas;
	Bola bola1,bola2;
	
	public VentanaGrafica(JFrame frame) {
		frame.setContentPane(createMainPanel());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private Container createMainPanel() {
		bola1 = new Bola();
		bola2 = new Bola();
		canvas = new Canvas(bola1,bola2);
		return canvas;
	}

	public Bola getBola1() {
		return bola1;
	}

	public Bola getBola2() {
		return bola2;
	}
}
