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
		controller = new SettingsController(this);
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
		/* Kategoriaren goiburura */
		JLabel label = new JLabel("Port");
		label.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		GridBagConstraints labelKonst = new GridBagConstraints();
		labelKonst.gridwidth = 1;
		labelKonst.insets = new Insets(10, 5, 20, 30);	//top left bottom right
		labelKonst.gridx = 0;
		labelKonst.gridy = 2;
		labelKonst.weightx = 0.3;
		labelKonst.weighty = 0.3;
		labelKonst.anchor = GridBagConstraints.NORTHWEST;
		
		this.add(label, labelKonst);
		
		/* JComboBox bat portu ezberdinak erakusteko, eta boti bat portuen zerrenda freskatzeko */
		JPanel subPanel = new JPanel();
		ports = new JComboBox<String>(portManager.getAvaiblePorts());
		ports.setForeground(Color.WHITE);
		ports.setBackground(new Color(36, 123, 160));
		ports.setActionCommand("logout");
//		btnLogout.addActionListener(controller);
		subPanel.add(ports);
		
		JButton button = new JButton(new ImageIcon("res/icons/refresh.png"));
		button.setActionCommand("refresh");
		button.addActionListener(controller);
		button.setBackground(Color.WHITE);
		button.setBorder(BorderFactory.createEmptyBorder(5,5,5,7));
		button.setOpaque(false);
		button.setFocusPainted(false);
		subPanel.add(button);
		
		GridBagConstraints gbc_btnLogout = new GridBagConstraints();
		gbc_btnLogout.insets = new Insets(0, 0, 5, 0);
		gbc_btnLogout.gridx = 1;
		gbc_btnLogout.gridy = 3;
		gbc_btnLogout.weightx = 0.7;
		gbc_btnLogout.weighty = 0.7;
		gbc_btnLogout.anchor = GridBagConstraints.WEST;
		this.add(subPanel, gbc_btnLogout);
	}


	public void refreshPorts() {
		ports.removeAllItems();
		for (String port : portManager.getAvaiblePorts()) {
			ports.addItem(port);
		}
		
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
	}
	
}
