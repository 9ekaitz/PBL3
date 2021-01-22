package views;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pbl.ViewController;

@SuppressWarnings("serial")
public class TestPanel extends JPanel{

	ViewController controller;
	Color darkBlue;
	
	public TestPanel(ViewController controller) {
		this.controller = controller;
		this.setBackground(new Color(235, 181, 49));
		setLayout(new BorderLayout(0, 0));
		
		JLabel photo = new JLabel(new ImageIcon("tmp/underconstruction.png"));
		this.add(photo);
	}
}
	
