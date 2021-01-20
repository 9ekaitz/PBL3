package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.SettingsController;
import models.PortManager;

public class Settings extends JPanel implements PropertyChangeListener{

	PortManager portManager;
	SettingsController controller;
	JComboBox<String> ports;
	
	public Settings() {
		super(new GridBagLayout());	//Panela hasieratu
//		controller = new SettingsController(this);
		portManager = new PortManager();
		setPreferredSize(new Dimension(1024, 600));	//Aldatu behar da
		this.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		createInterface();	//Paneleko elementuak sortu
	}
	
	private void createInterface() {
		
		createAccountSection();
		createPortSection();
	}

	
	private void createAccountSection() {
		
	}
	
	private void createPortSection() {
		
	}


	public void refreshPorts() {
		ports.removeAllItems();
		for (String port : portManager.getAvaiblePorts()) {
			ports.addItem(port);
		}
		
	}
	
	public String getSelectedPort() {
		return (String) ports.getSelectedItem();
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
	}
	
}
