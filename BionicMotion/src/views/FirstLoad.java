package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class FirstLoad extends JFrame{

	public FirstLoad() {
		super();
		this.setSize(400,200);
		this.setContentPane(createMainWindow());
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.setVisible(true);
	}

	private Container createMainWindow() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel logo = new JLabel();
		logo.setIcon(new ImageIcon("res/img/Logo-init.png"));
		logo.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panel.add(Box.createVerticalGlue());
		panel.add(logo);
		panel.add(Box.createVerticalGlue());
		
		panel.setBackground(new Color(36, 123, 160));
		
		return panel;
	}
}
