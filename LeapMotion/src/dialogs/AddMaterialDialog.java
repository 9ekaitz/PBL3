package dialogs;

import java.awt.Color;
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
	
	JFrame frame;
	boolean materialCreated;
	
	public AddMaterialDialog(JFrame frame, String title, boolean mode) {
		super(frame, title, mode);
		materialCreated = false;
		this.frame = frame;
		this.setSize(new Dimension(300, 125));
		this.setContentPane(createDialogPanel());
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private Container createDialogPanel() {
		JPanel panel = new JPanel();
		GridBagLayout gbl_panel = new GridBagLayout();
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel_1 = new JLabel("Material name: ");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		materialField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		panel.add(materialField, gbc_textField);
		materialField.setColumns(10);
		
		JButton btnAddMaterial = new JButton("Add");
		btnAddMaterial.setForeground(Color.WHITE);
		btnAddMaterial.setBackground(new Color(36, 123, 160));
		btnAddMaterial.setActionCommand("create");
		btnAddMaterial.addActionListener(this);
		
		GridBagConstraints gbc_btnAddMaterial = new GridBagConstraints();
		gbc_btnAddMaterial.insets = new Insets(0, 0, 0, 5);
		gbc_btnAddMaterial.gridx = 0;
		gbc_btnAddMaterial.gridy = 2;
		panel.add(btnAddMaterial, gbc_btnAddMaterial);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setBackground(new Color(36, 123, 160));
		btnCancel.setActionCommand("cancel");
		btnCancel.addActionListener(this);
		
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 2;
		panel.add(btnCancel, gbc_btnNewButton_1);
	
		return panel;
	}

	public String getMaterial() {
		return materialField.getText();
	}
	
	public boolean materialIsCreated() {
		return materialCreated;
	}
	
	public void testFieldInput() {	
		String material = getMaterial();
		
		if (material.trim().isEmpty()) {
			JOptionPane.showMessageDialog(frame, "You should enter a valid name!", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showConfirmDialog(frame, "The material \""+material+"\" was successfully added!", "Success", JOptionPane.OK_OPTION);
			materialCreated = true;
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
