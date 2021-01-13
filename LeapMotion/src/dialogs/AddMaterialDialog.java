package dialogs;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import pbl.User;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import java.awt.Font;

public class AddMaterialDialog extends JDialog implements ActionListener{

	JTextField usernameField;
	JPasswordField passwordField;
	JTextField textField;
	JPasswordField passwordField_1;
	JCheckBox isAdminCheckBox;
	
	User user;
	boolean userIsAdmin;
	int userHash;
	int passHash;
	
	public AddMaterialDialog(JFrame frame, String title, boolean mode) {
		super(frame, title, mode);
		this.setContentPane(createDialogPanel());
		this.setPreferredSize(new Dimension(200, 100));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private Container createDialogPanel() {
		JPanel panel = new JPanel();
		
		GridBagLayout gbl_panel = new GridBagLayout();
		panel.setLayout(gbl_panel);
		
		JLabel lblTitle = new JLabel("New User");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.gridwidth = 2;
		gbc_lblTitle.insets = new Insets(0, 0, 15, 5);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		panel.add(lblTitle, gbc_lblTitle);
		
		JLabel lblUser = new JLabel("Username: ");
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblUser = new GridBagConstraints();
		gbc_lblUser.insets = new Insets(0, 0, 5, 5);
		gbc_lblUser.anchor = GridBagConstraints.EAST;
		gbc_lblUser.gridx = 0;
		gbc_lblUser.gridy = 1;
		panel.add(lblUser, gbc_lblUser);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		panel.add(textField, gbc_textField);
		textField.setColumns(20);
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 2;
		panel.add(lblPassword, gbc_lblPassword);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setColumns(20);
		GridBagConstraints gbc_passwordField_1 = new GridBagConstraints();
		gbc_passwordField_1.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField_1.gridx = 1;
		gbc_passwordField_1.gridy = 2;
		panel.add(passwordField_1, gbc_passwordField_1);
		
		isAdminCheckBox = new JCheckBox("Administrator");
		GridBagConstraints gbc_isAdminCheckBox = new GridBagConstraints();
		gbc_isAdminCheckBox.anchor = GridBagConstraints.WEST;
		gbc_isAdminCheckBox.insets = new Insets(10, 0, 10, 5);
		gbc_isAdminCheckBox.gridx = 1;
		gbc_isAdminCheckBox.gridy = 3;
		panel.add(isAdminCheckBox, gbc_isAdminCheckBox);
		
		JButton btnNewButton = new JButton("Create user");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 4;
		panel.add(btnNewButton, gbc_btnNewButton);
		
		return panel;
	}

	public User getUser() {
		return new User(userHash, passHash, userIsAdmin);
	}
	
	public void saveUserCredentials() {
		userHash = usernameField.getText().hashCode();
		passHash = String.valueOf(passwordField.getPassword()).hashCode();
		userIsAdmin = isAdminCheckBox.isSelected();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "createUser":
			saveUserCredentials();
			this.dispose();
			break;
		case "cancel":
			this.dispose();
			break;
		default:
			break;
		}
		
	}
	
	
}
