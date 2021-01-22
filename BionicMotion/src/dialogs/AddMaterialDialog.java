package dialogs;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import models.Material;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class AddMaterialDialog extends JDialog implements ActionListener{

	JTextField materialField;
	Material material = null;
	JFrame frame;
	
	public AddMaterialDialog(JFrame frame, String title, boolean mode) {
		super(frame, title, mode);
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

	public Material getMaterial() {
		return material;
	}
	
	
	public void testFieldInput() {	
		String materialName = materialField.getText();
		
		if (materialName.trim().isEmpty()) {
			JOptionPane.showMessageDialog(frame, "You should enter a valid name!", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(frame, "The material \""+materialName+"\" was successfully added!", "Success", JOptionPane.INFORMATION_MESSAGE);
			material = new Material(materialName);
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
