package pbl;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

public class Login extends JPanel{

	ViewController controller;
	private JPasswordField passwordField;
	private JTextField usernameField;
	
	public Login(ViewController controller) {
		this.controller = controller;
		
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
		
		usernameField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		this.add(usernameField, gbc_textField);
		usernameField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 2;
		this.add(lblPassword, gbc_lblPassword);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 2;
		this.add(passwordField, gbc_textField_1);
		passwordField.setColumns(10);
		
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
	}

	public String getPasswordField() {
		return String.valueOf(passwordField.getPassword());
	}

	public String getUsernameField() {
		return usernameField.getText();
	}
	
}