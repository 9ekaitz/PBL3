package views;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.EmptyBorder;

import chart.ChartView;
import models.Material;
import models.MaterialModel;
import pbl.ViewController;
import javax.swing.JList;

@SuppressWarnings("serial")
public class AppMenu extends JPanel{

	ViewController controller;
	MaterialModel materialModel;
	Color darkBlue;
	JList<Material> materialJList;
	
	public AppMenu(ViewController controller, MaterialModel materialModel) {
		this.controller = controller;
		this.materialModel = materialModel;
		
		setPreferredSize(new Dimension(1024, 600));
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, createMenu(), createGraph());
		splitPane.setDividerLocation(350);
		add(splitPane);
		
	}

	private JScrollPane createGraph() {
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		scrollPane.setViewportView(new ChartView());
		
		return scrollPane;
	}

	private JPanel createMenu() {
		JPanel menuPanel = new JPanel();
		menuPanel.setBorder(new EmptyBorder(30, 20, 30, 20));
		GridBagLayout gbl_buttonPanel = new GridBagLayout();
		gbl_buttonPanel.columnWidths = new int[]{0, 134};
		gbl_buttonPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0};
		gbl_buttonPanel.columnWeights = new double[]{1.0, 0.0};
		menuPanel.setLayout(gbl_buttonPanel);

		JLabel lblMaterials = new JLabel("Materials");
		GridBagConstraints gbc_lblMaterials = new GridBagConstraints();
		gbc_lblMaterials.gridwidth = 2;
		gbc_lblMaterials.insets = new Insets(0, 0, 5, 0);
		gbc_lblMaterials.gridx = 0;
		gbc_lblMaterials.gridy = 2;
		menuPanel.add(lblMaterials, gbc_lblMaterials);

		JButton btnAddMaterial = new JButton("Add Material");
		btnAddMaterial.setForeground(Color.WHITE);
		btnAddMaterial.setBackground(new Color(36, 123, 160));
		btnAddMaterial.setActionCommand("addMaterial");
		btnAddMaterial.addActionListener(controller);

		GridBagConstraints gbc_btnAddMaterial = new GridBagConstraints();
		gbc_btnAddMaterial.fill = GridBagConstraints.BOTH;
		gbc_btnAddMaterial.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddMaterial.gridx = 0;
		gbc_btnAddMaterial.gridy = 3;
		menuPanel.add(btnAddMaterial, gbc_btnAddMaterial);

		JButton btnRemoveMaterial = new JButton("Remove Material");
		btnRemoveMaterial.setForeground(Color.WHITE);
		btnRemoveMaterial.setBackground(new Color(36, 123, 160));
		btnRemoveMaterial.setActionCommand("removeMaterial");
		btnRemoveMaterial.addActionListener(controller);
		
		GridBagConstraints gbc_btnRemoveMaterial = new GridBagConstraints();
		gbc_btnRemoveMaterial.fill = GridBagConstraints.BOTH;
		gbc_btnRemoveMaterial.insets = new Insets(0, 0, 5, 0);
		gbc_btnRemoveMaterial.gridx = 1;
		gbc_btnRemoveMaterial.gridy = 3;
		menuPanel.add(btnRemoveMaterial, gbc_btnRemoveMaterial);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 4;
		menuPanel.add(scrollPane, gbc_scrollPane);
		
		materialJList = new JList<>();
		materialJList.setModel(materialModel);
		materialJList.addListSelectionListener(controller);
		scrollPane.setViewportView(materialJList);

		JLabel lblLast50products = new JLabel("Last 50 products");
		GridBagConstraints gbc_lblLast50products = new GridBagConstraints();
		gbc_lblLast50products.gridwidth = 2;
		gbc_lblLast50products.insets = new Insets(0, 0, 5, 0);
		gbc_lblLast50products.gridx = 0;
		gbc_lblLast50products.gridy = 5;
		menuPanel.add(lblLast50products, gbc_lblLast50products);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 2;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 6;
		menuPanel.add(scrollPane_1, gbc_scrollPane_1);
		
		JList<String> list_1 = new JList<>();
		scrollPane_1.setViewportView(list_1);
		
		JButton btnCreateProduct = new JButton("Create Product");
		btnCreateProduct.setBackground(new Color(36, 123, 160));
		btnCreateProduct.setForeground(Color.WHITE);
		btnCreateProduct.setActionCommand("startcreateProduct");
		btnCreateProduct.addActionListener(controller);
		
		GridBagConstraints gbc_btnCreateProduct = new GridBagConstraints();
		gbc_btnCreateProduct.insets = new Insets(0, 0, 5, 0);
		gbc_btnCreateProduct.fill = GridBagConstraints.BOTH;
		gbc_btnCreateProduct.gridheight = 2;
		gbc_btnCreateProduct.gridwidth = 2;
		gbc_btnCreateProduct.gridx = 0;
		gbc_btnCreateProduct.gridy = 7;
		menuPanel.add(btnCreateProduct, gbc_btnCreateProduct);

		JButton btnShutdown = new JButton();
		btnShutdown.setActionCommand("shutdown");
		btnShutdown.addActionListener(controller);
		btnShutdown.setOpaque(false);
		btnShutdown.setContentAreaFilled(false);
		btnShutdown.setBorderPainted(false);
		btnShutdown.setIcon(new ImageIcon("res/icons/shutdown.png"));
		
		GridBagConstraints gbc_btnShutdown = new GridBagConstraints();
		gbc_btnShutdown.anchor = GridBagConstraints.EAST;
		gbc_btnShutdown.gridx = 1;
		gbc_btnShutdown.gridy = 9;
		menuPanel.add(btnShutdown, gbc_btnShutdown);
		
		return menuPanel;
	}
	
	public JList<Material> getMaterialList() {
		return materialJList;
	}
}
