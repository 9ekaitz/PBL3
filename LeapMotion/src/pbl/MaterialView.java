package pbl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JList;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class MaterialView extends JPanel{

	ViewController controller;
	Color darkBlue;
	private JTextField textField;
	
	public MaterialView(ViewController controller) {
		setBorder(new EmptyBorder(30, 30, 15, 30));
		this.controller = controller;
		setPreferredSize(new Dimension(1024, 600));
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0};
		setLayout(gridBagLayout);
		
		JButton btnCreateProduct = new JButton("Create product");
		GridBagConstraints gbc_btnCreateProduct = new GridBagConstraints();
		gbc_btnCreateProduct.anchor = GridBagConstraints.WEST;
		gbc_btnCreateProduct.insets = new Insets(0, 0, 5, 5);
		gbc_btnCreateProduct.gridx = 0;
		gbc_btnCreateProduct.gridy = 0;
		add(btnCreateProduct, gbc_btnCreateProduct);
		
		JLabel productName = new JLabel("Product Name: ");
		GridBagConstraints gbc_productName = new GridBagConstraints();
		gbc_productName.insets = new Insets(0, 0, 5, 5);
		gbc_productName.anchor = GridBagConstraints.EAST;
		gbc_productName.gridx = 1;
		gbc_productName.gridy = 0;
		add(productName, gbc_productName);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 0;
		add(textField, gbc_textField);
		textField.setColumns(30);
		
		JList list = new JList();
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 5, 0);
		gbc_list.gridwidth = 3;
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 1;
		add(list, gbc_list);
		
		JButton btnGoBack = new JButton("Back");
		btnGoBack.setBackground(new Color(36, 123, 160));
		btnGoBack.setForeground(Color.WHITE);
		btnGoBack.setActionCommand("goBackFromMaterialView");
		btnGoBack.addActionListener(controller);
		GridBagConstraints gbc_btnGoBack = new GridBagConstraints();
		gbc_btnGoBack.anchor = GridBagConstraints.EAST;
		gbc_btnGoBack.gridx = 2;
		gbc_btnGoBack.gridy = 2;
		add(btnGoBack, gbc_btnGoBack);
		
		
		
	}
}
