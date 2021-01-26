package pbl.display;

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

import org.jfree.chart.event.ChartChangeEvent;
import org.jfree.chart.event.ChartChangeListener;

import chart.PieChart;
import models.Material;
import models.MaterialModel;
import models.Product;
import models.ProductModel;
import pbl.controllers.ViewController;

import javax.swing.JList;

@SuppressWarnings("serial")
public class AppMenu extends JPanel{

	ViewController controller;
	MaterialModel materialModel;
	ProductModel productModel;
	Color darkBlue;
	JList<Material> materialJList;
	JList<Product> list_1;
	PieChart chart;
	
	public AppMenu(ViewController controller, MaterialModel materialModel, ProductModel productModel) {
		this.controller = controller;
		this.materialModel = materialModel;
		this.productModel = productModel;
		this.chart=new PieChart();
	
		
		setPreferredSize(new Dimension(1024, 600));
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, createMenu(), createGraph());
		splitPane.setDividerLocation(350);
		add(splitPane);
		
	}

	private JScrollPane createGraph() {
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportView(chart);
		
		
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

		/* Material zerrendaren goiburua */
		JLabel label = new JLabel("Materials");
		GridBagConstraints labelConst = new GridBagConstraints();
		labelConst.gridwidth = 2;
		labelConst.insets = new Insets(0, 0, 5, 0);
		labelConst.gridx = 0;
		labelConst.gridy = 2;
		menuPanel.add(label, labelConst);

		
		/* Materialak gehitzeko botoia */
		JButton button = new JButton("Add Material");
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(36, 123, 160));
		button.setActionCommand("addMaterial");
		button.addActionListener(controller);

		GridBagConstraints buttonConst = new GridBagConstraints();
		buttonConst.fill = GridBagConstraints.BOTH;
		buttonConst.insets = new Insets(0, 0, 5, 5);
		buttonConst.gridx = 0;
		buttonConst.gridy = 3;
		menuPanel.add(button, buttonConst);

		/* Materialak ezabatzeko botoia */
		button = new JButton("Remove Material");
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(36, 123, 160));
		button.setActionCommand("removeMaterial");
		button.addActionListener(controller);
		
		buttonConst = new GridBagConstraints();
		buttonConst.fill = GridBagConstraints.BOTH;
		buttonConst.insets = new Insets(0, 0, 5, 0);
		buttonConst.gridx = 1;
		buttonConst.gridy = 3;
		menuPanel.add(button, buttonConst);
		
		
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
		
		list_1= new JList<>();
		list_1.setModel(productModel);
		list_1.addListSelectionListener(controller);
		scrollPane_1.setViewportView(list_1);
		
		
		/* Produktuak sortzeko botoia */
		button = new JButton("Create Product");
		button.setBackground(new Color(36, 123, 160));
		button.setForeground(Color.WHITE);
		button.setActionCommand("startcreateProduct");
		button.addActionListener(controller);
		
		buttonConst = new GridBagConstraints();
		buttonConst.insets = new Insets(0, 0, 5, 0);
		buttonConst.fill = GridBagConstraints.BOTH;
		buttonConst.gridheight = 2;
		buttonConst.gridwidth = 2;
		buttonConst.gridx = 0;
		buttonConst.gridy = 7;
		menuPanel.add(button, buttonConst);

		
		/* Itzaltzeko botoia */
		button = new JButton();
		button.setActionCommand("shutdown");
		button.addActionListener(controller);
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setIcon(new ImageIcon("res/icons/shutdown.png"));
		
		buttonConst = new GridBagConstraints();
		buttonConst.anchor = GridBagConstraints.EAST;
		buttonConst.gridx = 1;
		buttonConst.gridy = 9;
		menuPanel.add(button, buttonConst);
		
		return menuPanel;
	}
	
	public JList<Material> getMaterialList() {
		return materialJList;
	}
	
	public JList<Product> getProductList() {
		return list_1;
	}

	public PieChart getChart() {
		return chart;
	}

	
	
}
