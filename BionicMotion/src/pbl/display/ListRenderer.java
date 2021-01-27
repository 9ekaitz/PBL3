package pbl.display;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import pbl.models.Material;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ListRenderer implements ListCellRenderer<Material> {

	@Override
	public Component getListCellRendererComponent(JList<? extends Material> list, Material value, int index,
			boolean isSelected, boolean cellHasFocus) {
		
		JPanel panel = new JPanel(new BorderLayout(10, 10));
		panel.setBackground(Color.WHITE);

		JLabel label = new JLabel(value.toString());
		panel.add(label, BorderLayout.CENTER);
		
		JLabel icon;
		/* Elementua aukeratzen denean ikonoa aldatzeko */
		if (value.isSelected()) icon = new JLabel(new ImageIcon("res/icons/valid.png"));
		else icon = new JLabel(new ImageIcon("res/icons/error.png"));
		
		/* Elementua klikatzean atzeko planoaren kolorea aldatzeko */
		if (cellHasFocus || isSelected) panel.setBackground(new Color(197, 229, 242));

		panel.add(icon, BorderLayout.EAST);
		return panel;
	}

}
