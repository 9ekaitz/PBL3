package pbl.dialogs;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import pbl.models.Material;

import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class AddMaterialDialog extends JDialog implements ActionListener{

	JTextField materialField;
	Material material = null;
	JFrame frame;
	ButtonGroup typeButtons;
	
	public AddMaterialDialog(JFrame frame, String title, boolean mode) {
		super(frame, title, mode);
		this.frame = frame;
		this.setContentPane(createDialogPanel());
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private Container createDialogPanel() { // Panela sortu
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
		GridBagLayout gbl_panel = new GridBagLayout();
		panel.setLayout(gbl_panel);
		JRadioButton radioButton;
		
		JLabel lblNewLabel_1 = new JLabel("Material name: ");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		gbc_lblNewLabel_1.weightx = 0.5;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		materialField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.insets = new Insets(10, 0, 10, 0);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		gbc_textField.weightx = 0.5;
		panel.add(materialField, gbc_textField);
		materialField.setColumns(10);
		
		JPanel subPanel = new JPanel();
		typeButtons = new ButtonGroup();
		
		radioButton = new JRadioButton("Non-Reactive");
		radioButton.setActionCommand("Non_Reactive");
		radioButton.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
		radioButton.setSelected(true);
		typeButtons.add(radioButton);
		subPanel.add(radioButton);
		
		radioButton = new JRadioButton("Toxic");
		radioButton.setActionCommand("Toxic");
		radioButton.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
		typeButtons.add(radioButton);
		subPanel.add(radioButton);

		
		radioButton = new JRadioButton("Corrosive");
		radioButton.setActionCommand("Corrosive");
		radioButton.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
		typeButtons.add(radioButton);
		subPanel.add(radioButton);
		
		radioButton = new JRadioButton("Flammable");
		radioButton.setActionCommand("Flammable");
		radioButton.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
		typeButtons.add(radioButton);
		subPanel.add(radioButton);

		GridBagConstraints subPanelConst = new GridBagConstraints();
		subPanelConst.insets = new Insets(10, 0, 20, 5);
		subPanelConst.gridx = 0;
		subPanelConst.gridy = 1;
		subPanelConst.gridwidth = 2;
		panel.add(subPanel,subPanelConst);
		
		JButton btnAddMaterial = new JButton("Add");
		btnAddMaterial.setForeground(Color.WHITE); // Hizkien kolorea
		btnAddMaterial.setBackground(new Color(36, 123, 160)); // Botoiaren kolorea
		btnAddMaterial.setActionCommand("create");
		btnAddMaterial.addActionListener(this);
		
		GridBagConstraints gbc_btnAddMaterial = new GridBagConstraints();
		gbc_btnAddMaterial.insets = new Insets(0, 0, 0, 5);
		gbc_btnAddMaterial.anchor = GridBagConstraints.EAST;
		gbc_btnAddMaterial.gridx = 0;
		gbc_btnAddMaterial.gridy = 2;
		gbc_btnAddMaterial.weightx = 0.5;
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
		gbc_btnNewButton_1.weightx = 0.5;
		panel.add(btnCancel, gbc_btnNewButton_1);
	
		return panel;
	}
	
	public Material getMaterial() {
		return material;
	}
	
	
	public void testFieldInput() {	
		String materialName = materialField.getText();
		
		if (materialName.trim().isEmpty()) { // Balio duen izen bat sartu duzula ziurtatzeko
			JOptionPane.showMessageDialog(frame, "You should enter a valid name!", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(frame, "The material \""+materialName+"\" was successfully added!", "Success", JOptionPane.INFORMATION_MESSAGE);
			material = new Material(materialName, typeButtons.getSelection().getActionCommand());
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
