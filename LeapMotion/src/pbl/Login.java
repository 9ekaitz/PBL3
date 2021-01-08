package pbl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
<<<<<<< HEAD
=======
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
>>>>>>> refs/heads/vista

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
<<<<<<< HEAD
import java.awt.Panel;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
=======
>>>>>>> refs/heads/vista

public class Login extends JPanel{

	ViewController controller;
	private JPasswordField passwordField;
	private JTextField textField;
	
<<<<<<< HEAD
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
=======
	public Login(ViewController controller) {
		this.controller = controller;
>>>>>>> refs/heads/vista
		
<<<<<<< HEAD
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.LEFT);
		passwordField.setBounds(414, 333, 181, 31);
		panel.add(passwordField);
=======
		GridBagLayout gridBagLayout = new GridBagLayout();
		this.setLayout(gridBagLayout);
		
		JLabel logo = new JLabel(new ImageIcon("img/Logo-vista.png"));
		GridBagConstraints gbc_logo = new GridBagConstraints();
		gbc_logo.gridwidth = 2;
		gbc_logo.insets = new Insets(0, 0, 20, 0);
		gbc_logo.gridx = 0;
		gbc_logo.gridy = 0;
		this.add(logo, gbc_logo);
		
		JLabel lblUser = new JLabel("User: ");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblUser = new GridBagConstraints();
		gbc_lblUser.insets = new Insets(0, 0, 5, 5);
		gbc_lblUser.anchor = GridBagConstraints.EAST;
		gbc_lblUser.gridx = 0;
		gbc_lblUser.gridy = 1;
		this.add(lblUser, gbc_lblUser);
>>>>>>> refs/heads/vista
		
<<<<<<< HEAD
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setBounds(414, 275, 181, 30);
		panel.add(textField);
		textField.setColumns(10);
=======
		usernameField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		this.add(usernameField, gbc_textField);
		usernameField.setColumns(10);
>>>>>>> refs/heads/vista
		
<<<<<<< HEAD
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
=======
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 2;
		this.add(lblPassword, gbc_lblPassword);
>>>>>>> refs/heads/vista
		
<<<<<<< HEAD
		JButton loginButton = new JButton("Login");
		loginButton.setForeground(Color.WHITE);
		loginButton.setBackground(new Color(36, 123, 160));
		loginButton.setBounds(462, 394, 85, 21);
		panel.add(loginButton);
=======
		passwordField = new JPasswordField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 2;
		this.add(passwordField, gbc_textField_1);
		passwordField.setColumns(10);
>>>>>>> refs/heads/vista
		
<<<<<<< HEAD
		return panel;
=======
		JButton btnLogin = new JButton("Login");
		btnLogin.setActionCommand("loginAttempt");
		btnLogin.addActionListener(controller);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(new Color(36, 123, 160));
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.insets = new Insets(20, 0, 0, 0);
		gbc_btnLogin.gridwidth = 2;
		gbc_btnLogin.gridx = 0;
		gbc_btnLogin.gridy = 3;
		this.add(btnLogin, gbc_btnLogin);
>>>>>>> refs/heads/vista
	}

	public String getPasswordField() {
		return String.valueOf(passwordField.getPassword());
	}

	public String getUsernameField() {
		return usernameField.getText();
	}
	
	
}
	
