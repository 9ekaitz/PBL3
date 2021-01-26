package pbl.dialogs;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pbl.Session;
import pbl.controllers.SettingsController;
import pbl.io.PortManager;

@SuppressWarnings("serial")
public class SettingsDialog extends JDialog {

	JFrame frame;
	JComboBox<String> ports;
	JTextField l;
	PortManager portManager;
	Session session;
	SettingsController controller;
	
	public SettingsDialog(JFrame frame, String title, boolean mode, Session session, PortManager portManager) {
		super(frame, title, mode);
		this.frame = frame;
		this.setSize(400,320);
		this.session = session;
		if (portManager == null) this.portManager = new PortManager();
		else this.portManager = portManager;
		
		controller = new SettingsController(this, this.portManager);
		this.setLocationRelativeTo(null);
		this.setContentPane(createPanel());
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private Container createPanel() {
		
		JPanel panel = new JPanel(new GridBagLayout());
		createAccountSection(panel);
		createPortsSection(panel);
		
		return panel;
	}
	
	private void createAccountSection(JPanel panel) {
		/* Erabiltzaileen kontuekin erlazionatutako ekintzak */
		
		/* Kategoriaren goiburura */
		JLabel label = new JLabel("Account");
		label.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		GridBagConstraints labelKonst = new GridBagConstraints();
		labelKonst.insets = new Insets(10,10,5,10);
		labelKonst.gridx = 0;
		labelKonst.gridy = 0;
		labelKonst.weightx = 0.3;
		labelKonst.weighty = 0.3;
		labelKonst.anchor = GridBagConstraints.NORTHWEST;
		
		panel.add(label, labelKonst);


		JPanel subPanel = new JPanel();
		/* 1 Aukera -- kontu bat sortu, bakarrik administratzileentzako */
		JButton button = new JButton("Create Account");
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(36, 123, 160));
		button.setActionCommand("create");
		button.addActionListener(controller);
		if (!session.isPriviledged()) button.setEnabled(false);
		
		subPanel.add(button);
		
		/* 2 Aukera -- sesio hasita daukan kontu ezabatu, dialogo bat erakutsi daiteke konfirmatzeko */
		button = new JButton("Delete Account");	//Botoia sortu
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(36, 123, 160));
		button.setActionCommand("delete");
		button.addActionListener(controller);
		
		subPanel.add(button);
		
		GridBagConstraints subPanelKonst = new GridBagConstraints();	//Botoiaren konstraintak sortu
		subPanelKonst.insets = new Insets(0, 0, 5, 0);
		subPanelKonst.gridx = 0;
		subPanelKonst.gridy = 1;
		subPanelKonst.weightx = 0.7;
		subPanelKonst.weighty = 0.7;
		subPanelKonst.gridwidth = 2;
		subPanelKonst.anchor = GridBagConstraints.CENTER;
//		subPanelKonst.weightx = 1;
		
		
		
		panel.add(subPanel, subPanelKonst);
	}
	
	private void createPortsSection(JPanel panel) {
		/* Kategoriaren goiburura */
		JLabel label = new JLabel("Port");
		label.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		GridBagConstraints labelKonst = new GridBagConstraints();
		labelKonst.gridwidth = 1;
		labelKonst.insets = new Insets(10,10,5,10);	//top left bottom right
		labelKonst.gridx = 0;
		labelKonst.gridy = 2;
		labelKonst.weightx = 0.3;
		labelKonst.weighty = 0.3;
		labelKonst.anchor = GridBagConstraints.NORTHWEST;
		
		panel.add(label, labelKonst);
		
		/* JComboBox bat portu ezberdinak erakusteko, eta boti bat portuen zerrenda freskatzeko */
		JPanel subPanel = new JPanel(new GridLayout(2, 1, 0, 5));
		
		JPanel azpiPanel = new JPanel();
		ports = new JComboBox<String>(portManager.getAvaiblePorts());
		ports.setForeground(Color.BLACK);
//		ports.setBackground(Color.LIGHT_GRAY);
		ports.setPreferredSize(new Dimension(150,30));
		ports.addActionListener(controller);
		azpiPanel.add(ports);
		
		JButton button = new JButton(new ImageIcon("res/icons/refresh.png"));
		button.setActionCommand("refresh");
//		button.addActionListener(controller);
		button.setBackground(Color.WHITE);
		button.setBorder(BorderFactory.createEmptyBorder(5,5,5,7));
		button.setOpaque(false);
		button.setFocusPainted(false);
		azpiPanel.add(button);
		
		subPanel.add(azpiPanel);
		
		azpiPanel = new JPanel();
		
		l = new JTextField();
		l.setPreferredSize(new Dimension(200, 25));
		l.setText("Status");
		l.setEditable(false);
		l.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		azpiPanel.add(l);
		
		button = new JButton("Open");
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(36, 123, 160));
		button.setActionCommand("open");
		button.addActionListener(controller);
		azpiPanel.add(button);
		
		subPanel.add(azpiPanel);
		
		GridBagConstraints subPanelKonst = new GridBagConstraints();
		subPanelKonst.insets = new Insets(0, 0, 5, 0);
		subPanelKonst.gridx = 0;
		subPanelKonst.gridy = 3;
		subPanelKonst.weightx = 0.7;
		subPanelKonst.weighty = 0.7;
		subPanelKonst.gridwidth = 2;
		subPanelKonst.anchor = GridBagConstraints.CENTER;
		panel.add(subPanel, subPanelKonst);
	}
	
	public void refreshPorts() {
		ports.removeAllItems();
		Arrays.stream(portManager.getAvaiblePorts()).forEach(p->ports.addItem(p));
	}
	
	public String getSelectedPort() {
		return (String) ports.getSelectedItem();
	}
	
	public void displayStatus(String status) {
		l.setText(status);
	}
	
	public Session getSession() {
		return session;
	}
	
	public PortManager getPortManager() {
		return portManager;
	}
}
