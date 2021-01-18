package dialogs;

import java.awt.Container;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import java.awt.Font;

public class CreateAccountDialog extends JDialog implements ActionListener{
	
	JTextField usernameField;
	JPasswordField passwordField;
	JPasswordField repeatPasswordField;
	JCheckBox isAdminCheckBox;
	
	JFrame frame;
	
	boolean userIsAdmin;
	String username;
	int passHash;
	
	boolean userIsCreated;
	
	public CreateAccountDialog(JFrame frame, String title, boolean mode) {
		super(frame, title, mode);
		this.frame = frame;
		this.userIsCreated = false;
		this.setSize(400,320);
		this.setLocation(352,150); ///////// TODO: PONER EN EL CENTRO DEL JPANEL
		this.setContentPane(createDialogPanel());
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private Container createDialogPanel() {
		JPanel panel = new JPanel();
		
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{136, 158};
		gbl_panel.columnWeights = new double[]{0.0, 1.0};
		panel.setLayout(gbl_panel);
		
		JLabel lblTitle = new JLabel("New User");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.gridwidth = 2;
		gbc_lblTitle.insets = new Insets(0, 0, 15, 0);
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
		
		usernameField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		panel.add(usernameField, gbc_textField);
		usernameField.setColumns(20);
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 2;
		panel.add(lblPassword, gbc_lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(20);
		GridBagConstraints gbc_passwordField_1 = new GridBagConstraints();
		gbc_passwordField_1.anchor = GridBagConstraints.WEST;
		gbc_passwordField_1.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField_1.gridx = 1;
		gbc_passwordField_1.gridy = 2;
		panel.add(passwordField, gbc_passwordField_1);
		
		JLabel lblRepeatPassword = new JLabel("Repeat password:  ");
		lblRepeatPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblRepeatPassword = new GridBagConstraints();
		gbc_lblRepeatPassword.anchor = GridBagConstraints.EAST;
		gbc_lblRepeatPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblRepeatPassword.gridx = 0;
		gbc_lblRepeatPassword.gridy = 3;
		panel.add(lblRepeatPassword, gbc_lblRepeatPassword);
		
		repeatPasswordField = new JPasswordField();
		repeatPasswordField.setColumns(20);
		GridBagConstraints gbc_passwordField_2 = new GridBagConstraints();
		gbc_passwordField_2.anchor = GridBagConstraints.WEST;
		gbc_passwordField_2.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField_2.gridx = 1;
		gbc_passwordField_2.gridy = 3;
		panel.add(repeatPasswordField, gbc_passwordField_2);
		
		isAdminCheckBox = new JCheckBox("Administrator");
		GridBagConstraints gbc_isAdminCheckBox = new GridBagConstraints();
		gbc_isAdminCheckBox.anchor = GridBagConstraints.WEST;
		gbc_isAdminCheckBox.insets = new Insets(10, 0, 10, 0);
		gbc_isAdminCheckBox.gridx = 1;
		gbc_isAdminCheckBox.gridy = 4;
		panel.add(isAdminCheckBox, gbc_isAdminCheckBox);
		
		JButton btnCreateUser = new JButton("Create user");
		btnCreateUser.setActionCommand("createUser");
		btnCreateUser.addActionListener(this);
		GridBagConstraints gbc_btnCreateUser = new GridBagConstraints();
		gbc_btnCreateUser.gridwidth = 3;
		gbc_btnCreateUser.insets = new Insets(0, 0, 0, 5);
		gbc_btnCreateUser.gridx = 0;
		gbc_btnCreateUser.gridy = 5;
		panel.add(btnCreateUser, gbc_btnCreateUser);
		
		return panel;
	}

	public String[] getUserData() {
		String[] data = {username, String.valueOf(passHash), (userIsAdmin?"T":"F")};
		return data;
	}
	
	public void saveUserCredentials() { ///////////////////////////////////////// TODO: excepciones
		username = usernameField.getText();
		passHash = String.valueOf(passwordField.getPassword()).hashCode();
		userIsAdmin = isAdminCheckBox.isSelected();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "createUser":
			checkPasswordsEquals();	
			break;
		case "cancel":
			this.dispose();
			break;
		default:
			break;
		}
		
	}

	private void checkPasswordsEquals() {	
		String user = usernameField.getText();
		String passw1 = String.valueOf(passwordField.getPassword());
		String passw2 = String.valueOf(repeatPasswordField.getPassword());

		if (passw1.equals(passw2)) {
			saveUserCredentials();
			userIsCreated = true;
			JOptionPane.showMessageDialog(frame, "The user \""+user+"\" was successfully added!", "Success", JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(frame, "The passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public boolean userIsCreated() {
		return userIsCreated;
	}
	
}
