package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.Insets;
import javax.swing.border.EmptyBorder;

import models.MaterialList;
import pbl.ListRenderer;
import pbl.ViewController;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;

public class MaterialView extends JPanel{

	ViewController controller;
	MaterialList materialListModel;
	ListRenderer renderer;
	Color darkBlue;
	JTextField textField;
	
	public MaterialView(ViewController controller) {
		initializeVariables();
		createPanel(controller);		
	}

	private void initializeVariables() {
		materialListModel = new MaterialList();
		renderer = new ListRenderer();
	}
	
	private void createPanel(ViewController controller) {
		setBorder(new EmptyBorder(30, 30, 15, 30));
		this.controller = controller;
		setPreferredSize(new Dimension(1024, 600));
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0};
		setLayout(gridBagLayout);
		
		JButton btnCreateProduct = new JButton("Create product");
		btnCreateProduct.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCreateProduct.setBackground(new Color(36, 123, 160));
		btnCreateProduct.setForeground(Color.WHITE);
		btnCreateProduct.setActionCommand("createProduct");
		btnCreateProduct.addActionListener(controller);
		GridBagConstraints gbc_btnCreateProduct = new GridBagConstraints();
		gbc_btnCreateProduct.anchor = GridBagConstraints.WEST;
		gbc_btnCreateProduct.insets = new Insets(0, 0, 5, 5);
		gbc_btnCreateProduct.gridx = 0;
		gbc_btnCreateProduct.gridy = 0;
		add(btnCreateProduct, gbc_btnCreateProduct);
		
		JLabel productName = new JLabel("Product Name: ");
		productName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_productName = new GridBagConstraints();
		gbc_productName.insets = new Insets(0, 0, 5, 5);
		gbc_productName.anchor = GridBagConstraints.EAST;
		gbc_productName.gridx = 1;
		gbc_productName.gridy = 0;
		add(productName, gbc_productName);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 0;
		add(textField, gbc_textField);
		textField.setColumns(20);
		
		JScrollPane listPanel = new JScrollPane(createMaterialList());
		
		GridBagConstraints gbc_listPanel = new GridBagConstraints();
		gbc_listPanel.insets = new Insets(0, 0, 5, 0);
		gbc_listPanel.gridwidth = 3;
		gbc_listPanel.fill = GridBagConstraints.BOTH;
		gbc_listPanel.gridx = 0;
		gbc_listPanel.gridy = 1;
		add(listPanel, gbc_listPanel);
		
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

	private JList<String> createMaterialList() {
		JList<String> materialList = new JList<>();
		materialList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		materialList.addListSelectionListener(controller);
		materialList.setModel(materialListModel);
		materialList.setCellRenderer(renderer);
		return materialList;
	}
	
}
