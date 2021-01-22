package views;

import java.awt.Color;
import java.awt.Dimension;

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

import models.Material;
import models.MaterialModel;
import models.Product;
import models.ProductModel;
import pbl.ListRenderer;
import pbl.ViewController;

import javax.swing.JLabel;
import java.awt.Font;

@SuppressWarnings("serial")
public class MaterialView extends JPanel{

	private ViewController controller;
	private MaterialModel materialModel;
	private ProductModel productModel;
	private Product product;
	private ListRenderer renderer;
	private JTextField textField;
	private JList<Material> lst;
	
	public MaterialView(ViewController controller, MaterialModel model) {
		this.materialModel = model;
		this.product = new Product();
		this.controller = controller;
		this.renderer = new ListRenderer();
		createPanel();		
	}

	
	private void createPanel() {
		setBorder(new EmptyBorder(30, 30, 15, 30));
		setPreferredSize(new Dimension(1024, 600));
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0};
		setLayout(gridBagLayout);
		
		/* Produktuak sortzeko botoia */
		JButton button = new JButton("Create product");
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.setBackground(new Color(36, 123, 160));
		button.setForeground(Color.WHITE);
		button.setActionCommand("createProduct");
		button.addActionListener(controller);
		
		GridBagConstraints buttonConst = new GridBagConstraints();
		buttonConst.anchor = GridBagConstraints.WEST;
		buttonConst.insets = new Insets(0, 0, 5, 5);
		buttonConst.gridx = 0;
		buttonConst.gridy = 0;
		add(button, buttonConst);
		
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
		
		/* Atzera bueltatzeko botoia */
		button = new JButton("Back");
		button.setBackground(new Color(36, 123, 160));
		button.setForeground(Color.WHITE);
		button.setActionCommand("goBackFromMaterialView");
		button.addActionListener(controller);
		
		buttonConst = new GridBagConstraints();
		buttonConst.anchor = GridBagConstraints.EAST;
		buttonConst.gridx = 2;
		buttonConst.gridy = 2;
		add(button, buttonConst);
		
	}

	private JList<Material> createMaterialList() {
		lst = new JList<>();
		lst.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lst.addListSelectionListener(controller);
		lst.setModel(materialModel);
		lst.setCellRenderer(renderer);
		return lst;
	}
	
	public String getProductName() {
		return textField.getText();
	}
	
	public Product getProduct() {
		return product;
	}
	
	public JList<Material> getMaterialList() {
		return lst;
	}

	public void addToRecipe(Material m) {
		product.addMaterial(m);
	}
	
	public void removeFromRecipe(Material m) {
		product.removeMaterial(m);
	}
	
}
