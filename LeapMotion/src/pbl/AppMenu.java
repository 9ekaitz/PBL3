package pbl;

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

public class AppMenu extends JPanel{

	ViewController controller;
	Color darkBlue;
	
	public AppMenu(ViewController controller) {
		this.controller = controller;
		setPreferredSize(new Dimension(1024, 600));
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, createButtons(), createGraph());
		splitPane.setDividerLocation(300);
		add(splitPane);
		
	}

	private JScrollPane createGraph() {
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		JLabel graph = new JLabel(new ImageIcon("tmp/graph.png"));
		scrollPane.setViewportView(graph);
		
		return scrollPane;
	}

	private JPanel createButtons() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(new EmptyBorder(30, 20, 30, 20));
		
		GridBagLayout gbl_buttonPanel = new GridBagLayout();
		gbl_buttonPanel.columnWidths = new int[]{0, 0, 0};
		gbl_buttonPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_buttonPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_buttonPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		buttonPanel.setLayout(gbl_buttonPanel);
		
		JButton btnNewButton = new JButton("Add material");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(36, 123, 160));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		buttonPanel.add(btnNewButton, gbc_btnNewButton);
	
		JButton btnNewButton_2 = new JButton("Delete material");
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setBackground(new Color(36, 123, 160));
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_2.gridx = 1;
		gbc_btnNewButton_2.gridy = 0;
		buttonPanel.add(btnNewButton_2, gbc_btnNewButton_2);
	
		JButton btnNewButton_1 = new JButton("See material");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(new Color(36, 123, 160));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 1;
		buttonPanel.add(btnNewButton_1, gbc_btnNewButton_1);

		JButton btnManual = new JButton("Manual manipulation");
		btnManual.setIcon(new ImageIcon("icons/warning.png"));
		btnManual.setActionCommand("manualManipulation");
		btnManual.addActionListener(controller);
		GridBagConstraints gbc_btnManual = new GridBagConstraints();
		gbc_btnManual.fill = GridBagConstraints.BOTH;
		gbc_btnManual.gridwidth = 2;
		gbc_btnManual.insets = new Insets(30, 0, 0, 0);
		gbc_btnManual.gridx = 0;
		gbc_btnManual.gridy = 3;
		buttonPanel.add(btnManual, gbc_btnManual);
		
		JButton btnCreateUser = new JButton("Create User");
		GridBagConstraints gbc_btnCreateUser = new GridBagConstraints();
		gbc_btnCreateUser.insets = new Insets(20, 0, 5, 5);
		gbc_btnCreateUser.gridx = 0;
		gbc_btnCreateUser.gridy = 2;
		buttonPanel.add(btnCreateUser, gbc_btnCreateUser);
		

		return buttonPanel;
	}
}
