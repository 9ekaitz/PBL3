package pbl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class MaterialView extends JPanel{

	ViewController controller;
	Color darkBlue;
	
	public MaterialView(ViewController controller) {
		this.controller = controller;
		setPreferredSize(new Dimension(1024, 600));
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, createButtons(), createGraph());
		splitPane.setDividerLocation(300);
		add(splitPane);
		
	}
}
