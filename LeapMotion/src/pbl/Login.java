package pbl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

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
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;

public class Login extends JFrame{

	Color lightGreen, darkGreen;
	private JPasswordField passwordField;
	private JTextField textField;
	
	public Login() {
		super("Leap Motion");
		this.setLocation (200,200);
		//this.setSize(400,400);
		this.setSize(1024,600);
		this.setIconImage(new ImageIcon("img/test.png").getImage());
		this.setContentPane(createLoginPanel());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private JPanel createLoginPanel() {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(300, 300));
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.LEFT);
		passwordField.setBounds(414, 333, 181, 31);
		panel.add(passwordField);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setBounds(414, 275, 181, 30);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel username = new JLabel("Username: ");
		username.setHorizontalAlignment(SwingConstants.CENTER);
		username.setFont(new Font("Tahoma", Font.PLAIN, 12));
		username.setBounds(341, 273, 72, 31);
		panel.add(username);
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPassword.setBounds(341, 332, 72, 31);
		panel.add(lblPassword);
		
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon("C:\\Users\\gorka\\Documents\\Repostiorios\\PBL-3\\PBL3\\LeapMotion\\img\\logoPrueba.png"));
		logo.setBounds(455, 141, 100, 100);
		panel.add(logo);
		
		JButton loginButton = new JButton("Login");
		loginButton.setForeground(Color.WHITE);
		loginButton.setBackground(new Color(36, 123, 160));
		loginButton.setBounds(462, 394, 85, 21);
		panel.add(loginButton);
		
		return panel;
	}
}
