package pbl;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

public class Login extends JFrame{

	Color darkBlue;
	private JPasswordField passwordField;
	private JTextField usernameField;
	
	public Login() {
		super("Leap Motion");
		this.setSize(1024,600);
		this.setWindowCentered();
		this.setIconImage(new ImageIcon("img/Logo-icon.png").getImage());
		this.initializeVariables();
		this.setContentPane(createMainWindow());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void setWindowCentered() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
	
	private void initializeVariables() {
		darkBlue = new Color(36, 123, 160);
		passwordField = new JPasswordField();
		usernameField = new JTextField();
	}
	
	private Container createMainWindow() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JButton loginButton = new JButton("Login");
		loginButton.setBackground(darkBlue);
		loginButton.setForeground(Color.WHITE);

		loginButton.setPreferredSize(new Dimension(0, 25));
		loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel logo = new JLabel();
		logo.setIcon(new ImageIcon("img/Logo-vista.png"));
		logo.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JPanel panelTextFields = createLabelsPanel();
		
		panel.add(Box.createVerticalGlue());
		panel.add(logo);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
//		panel.add(panelTextFields);
//		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(loginButton);
		panel.add(Box.createVerticalGlue());
		
		return panel;
	}

	private JPanel createLabelsPanel() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JLabel textUsername = new JLabel("Username: "); //300x33
		textUsername.setSize(new Dimension(300, 33));
//		textUsername.setBorder(BorderFactory.createEmptyBorder(0, 362, 0, 362));
		
		panel.add(Box.createHorizontalGlue());
		panel.add(textUsername);
		panel.add(Box.createRigidArea(new Dimension(10, 0)));
		panel.add(usernameField);
		panel.add(Box.createHorizontalGlue());
		
		panel.setBackground(darkBlue);

		return panel;
	}
}
