package pbl;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;
import java.awt.Insets;
import java.awt.Font;
import java.awt.BorderLayout;

public class TestPanel extends JPanel{

	ViewController controller;
	Color darkBlue;
	
	public TestPanel(ViewController controller) {
		this.controller = controller;
		this.setBackground(new Color(235, 181, 49));
		setLayout(new BorderLayout(0, 0));
		
		JLabel photo = new JLabel(new ImageIcon("img/underconstruction.png"));
		this.add(photo);
	}
}
	
