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
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import java.awt.Font;

public class AddMaterialDialog extends JDialog implements ActionListener{

	JTextField materialField;
	private JTextField textField;
	
	JFrame frame;
	
	public AddMaterialDialog(JFrame frame, String title, boolean mode) {
		super(frame, title, mode);
		this.frame = frame;
		this.setContentPane(createDialogPanel());
//		this.setPreferredSize(new Dimension(200, 50));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private Container createDialogPanel() {
		JPanel panel = new JPanel();
		GridBagLayout gbl_panel = new GridBagLayout();
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 40);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Material name: ");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		panel.add(textField, gbc_textField);
		textField.setColumns(20);
		
		JButton btnAddMaterial = new JButton("Add Material");
		btnAddMaterial.setActionCommand("crate");
		btnAddMaterial.addActionListener(this);
		
		GridBagConstraints gbc_btnAddMaterial = new GridBagConstraints();
		gbc_btnAddMaterial.insets = new Insets(0, 0, 0, 5);
		gbc_btnAddMaterial.gridx = 0;
		gbc_btnAddMaterial.gridy = 2;
		panel.add(btnAddMaterial, gbc_btnAddMaterial);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setActionCommand("cancel");
		btnNewButton_1.addActionListener(this);
		
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 2;
		panel.add(btnNewButton_1, gbc_btnNewButton_1);
	
		return panel;
	}

	public String getMaterial() {
		return materialField.getText();
	}
	
	public void testFieldInput() {	
		String material = materialField.getText();
		
		if (material.trim().isEmpty()) {
			JOptionPane.showMessageDialog(frame, "You should enter a valid name!", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showConfirmDialog(frame, "The material \""+material+"\" was successfully added!", "Success", JOptionPane.OK_OPTION);
			this.dispose();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "create":
			testFieldInput();
			break;
		case "cancel":
			this.dispose();
			break;
		default:
			break;
		}
		
	}
	
	
}
