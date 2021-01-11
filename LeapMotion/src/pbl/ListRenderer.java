package pbl;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ListRenderer implements ListCellRenderer<String>{

	
	@Override
	public Component getListCellRendererComponent(JList<? extends String> list, String value, int index,
			boolean isSelected, boolean cellHasFocus) {
		JPanel panel = new JPanel(new BorderLayout(10, 10));
		panel.setBackground(Color.WHITE);
		JLabel lblIsSelected;
		
		JLabel lblMaterialName = new JLabel(value);
		panel.add(lblMaterialName, BorderLayout.CENTER);	
		
		if(isSelected) {
			lblIsSelected = new JLabel(new ImageIcon("icons/valid.png"));
			panel.add(lblIsSelected, BorderLayout.EAST);
			panel.setBackground(new Color(197, 229, 242));
		} else if(!isSelected) {
			lblIsSelected = new JLabel(new ImageIcon("icons/error.png"));
			panel.add(lblIsSelected, BorderLayout.EAST);
		}
		return panel;
	}

	
}
