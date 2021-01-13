package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.EmptyBorder;

import chart.ChartView;
import pbl.ViewController;
import javax.swing.JList;

public class AppMenu extends JPanel{

	ViewController controller;
	Color darkBlue;
	
	public AppMenu(ViewController controller) {
		this.controller = controller;
		setPreferredSize(new Dimension(1024, 600));
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, createMenu(), createGraph());
		splitPane.setDividerLocation(300);
		add(splitPane);
		
	}

	private JScrollPane createGraph() {
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		scrollPane.setViewportView(new ChartView());
		
		return scrollPane;
	}

	private JPanel createMenu() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(new EmptyBorder(30, 20, 30, 20));
		GridBagLayout gbl_buttonPanel = new GridBagLayout();
		gbl_buttonPanel.columnWidths = new int[]{0, 134};
		gbl_buttonPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0};
		gbl_buttonPanel.columnWeights = new double[]{1.0, 0.0};
		buttonPanel.setLayout(gbl_buttonPanel);

		JLabel lbAccount = new JLabel("Account");
		GridBagConstraints gbc_lbAccount = new GridBagConstraints();
		gbc_lbAccount.gridwidth = 2;
		gbc_lbAccount.insets = new Insets(0, 0, 5, 0);
		gbc_lbAccount.gridx = 0;
		gbc_lbAccount.gridy = 0;
		buttonPanel.add(lbAccount, gbc_lbAccount);

		JButton btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.setActionCommand("createAccount");
		btnCreateAccount.addActionListener(controller);
		
		GridBagConstraints gbc_btnCreateAccount = new GridBagConstraints();
		gbc_btnCreateAccount.fill = GridBagConstraints.BOTH;
		gbc_btnCreateAccount.insets = new Insets(0, 0, 5, 5);
		gbc_btnCreateAccount.gridx = 0;
		gbc_btnCreateAccount.gridy = 1;
		buttonPanel.add(btnCreateAccount, gbc_btnCreateAccount);

		JButton btnLogout = new JButton("Logout");
		btnLogout.setActionCommand("logout");
		btnLogout.addActionListener(controller);
		
		GridBagConstraints gbc_btnLogout = new GridBagConstraints();
		gbc_btnLogout.fill = GridBagConstraints.BOTH;
		gbc_btnLogout.insets = new Insets(0, 0, 5, 0);
		gbc_btnLogout.gridx = 1;
		gbc_btnLogout.gridy = 1;
		buttonPanel.add(btnLogout, gbc_btnLogout);

		JLabel lblMaterials = new JLabel("Materials");
		GridBagConstraints gbc_lblMaterials = new GridBagConstraints();
		gbc_lblMaterials.gridwidth = 2;
		gbc_lblMaterials.insets = new Insets(0, 0, 5, 0);
		gbc_lblMaterials.gridx = 0;
		gbc_lblMaterials.gridy = 2;
		buttonPanel.add(lblMaterials, gbc_lblMaterials);

		JButton btnAddMaterial = new JButton("Add Material");
		btnAddMaterial.setActionCommand("addMaterial");
		btnAddMaterial.addActionListener(controller);

		GridBagConstraints gbc_btnAddMaterial = new GridBagConstraints();
		gbc_btnAddMaterial.fill = GridBagConstraints.BOTH;
		gbc_btnAddMaterial.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddMaterial.gridx = 0;
		gbc_btnAddMaterial.gridy = 3;
		buttonPanel.add(btnAddMaterial, gbc_btnAddMaterial);

		JButton btnRemoveMaterial = new JButton("Remove Material");
		btnRemoveMaterial.setActionCommand("removeMaterial");
		btnRemoveMaterial.addActionListener(controller);
		
		GridBagConstraints gbc_btnRemoveMaterial = new GridBagConstraints();
		gbc_btnRemoveMaterial.fill = GridBagConstraints.BOTH;
		gbc_btnRemoveMaterial.insets = new Insets(0, 0, 5, 0);
		gbc_btnRemoveMaterial.gridx = 1;
		gbc_btnRemoveMaterial.gridy = 3;
		buttonPanel.add(btnRemoveMaterial, gbc_btnRemoveMaterial);

		JList<String> list = new JList<>();
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.gridwidth = 2;
		gbc_list.insets = new Insets(0, 0, 5, 0);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 4;
		buttonPanel.add(list, gbc_list);

		JLabel lblLast50products = new JLabel("Last 50 products");
		GridBagConstraints gbc_lblLast50products = new GridBagConstraints();
		gbc_lblLast50products.gridwidth = 2;
		gbc_lblLast50products.insets = new Insets(0, 0, 5, 0);
		gbc_lblLast50products.gridx = 0;
		gbc_lblLast50products.gridy = 5;
		buttonPanel.add(lblLast50products, gbc_lblLast50products);

		JList<String> list2 = new JList<>();
		GridBagConstraints gbc_list2 = new GridBagConstraints();
		gbc_list2.gridwidth = 2;
		gbc_list2.insets = new Insets(0, 0, 5, 0);
		gbc_list2.fill = GridBagConstraints.BOTH;
		gbc_list2.gridx = 0;
		gbc_list2.gridy = 6;
		buttonPanel.add(list2, gbc_list2);

		JButton btnCreateProduct = new JButton("Create Product");
		btnCreateAccount.setActionCommand("createProduct");
		btnCreateAccount.addActionListener(controller);
		
		GridBagConstraints gbc_btnCreateProduct = new GridBagConstraints();
		gbc_btnCreateProduct.insets = new Insets(0, 0, 5, 0);
		gbc_btnCreateProduct.fill = GridBagConstraints.BOTH;
		gbc_btnCreateProduct.gridheight = 2;
		gbc_btnCreateProduct.gridwidth = 2;
		gbc_btnCreateProduct.gridx = 0;
		gbc_btnCreateProduct.gridy = 7;
		buttonPanel.add(btnCreateProduct, gbc_btnCreateProduct);

		JButton btnShutdown = new JButton();
		btnShutdown.setActionCommand("shutdown");
		btnShutdown.addActionListener(controller);
		btnShutdown.setOpaque(false);
		btnShutdown.setContentAreaFilled(false);
		btnShutdown.setBorderPainted(false);
		btnShutdown.setIcon(new ImageIcon("icons/shutdown.png"));
		
		GridBagConstraints gbc_btnShutdown = new GridBagConstraints();
		gbc_btnShutdown.anchor = GridBagConstraints.EAST;
		gbc_btnShutdown.gridx = 1;
		gbc_btnShutdown.gridy = 9;
		buttonPanel.add(btnShutdown, gbc_btnShutdown);
		
		return buttonPanel;
	}
}
