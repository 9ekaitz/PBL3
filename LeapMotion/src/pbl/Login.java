package pbl;

import java.awt.Color;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Panel;

public class Login extends JFrame{

	Color lightGreen, darkGreen;
	private JPasswordField passwordField;
	private JTextField usernameField;
	
	public Login() {
		super("Leap Motion");
		this.setLocation (200,200);
		this.setSize(1024,600);
		this.setIconImage(new ImageIcon("img/test.png").getImage());
		this.setContentPane(createMainWindow());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	private Container createMainWindow() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(371, 344, 208, 27);
		panel.add(passwordField);
		
		usernameField = new JTextField();
		usernameField.setBounds(371, 298, 208, 27);
		panel.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel logo = new JLabel("New label");
		logo.setIcon(new ImageIcon("img/logoPrueba.png"));
		logo.setBounds(371, 89, 208, 187);
		panel.add(logo);
		
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(371, 388, 89, 23);
		loginButton.setBackground(new Color(25, 83, 92));
		loginButton.setForeground(Color.WHITE);
		panel.add(loginButton);
		
		JButton registerButton = new JButton("Register");
		registerButton.setForeground(Color.WHITE);
		registerButton.setBounds(490, 388, 89, 23);
		registerButton.setBackground(new Color(25, 83, 92));
		registerButton.setForeground(Color.WHITE);
		panel.add(registerButton);
		
		JTextPane textUser = new JTextPane();
		textUser.setFont(new Font("Tahoma", Font.BOLD, 11));
		textUser.setText("Username:");
		textUser.setBounds(301, 298, 278, 27);
		panel.add(textUser);
		
		JTextPane txtpnPassword = new JTextPane();
		txtpnPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtpnPassword.setText("Password:");
		txtpnPassword.setBounds(301, 344, 278, 27);
		panel.add(txtpnPassword);
		
		return panel;
	}
}
