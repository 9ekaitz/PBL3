package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import dialogs.CreateAccountDialog;
import dialogs.SettingsDialog;
import jssc.SerialPortException;
import models.FileHandler;
import models.PortManager;
import models.User;
import models.UserHandler;

public class SettingsController implements ActionListener {

	SettingsDialog view;
	PortManager portManager;
	
	public SettingsController(SettingsDialog view, PortManager portManager) {
		this.view = view;
		this.portManager = portManager;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		
		
		case "refresh":	//Portuen zerrenda freskatzeko
			view.refreshPorts();
			break;
		case "open":	//Aukeratutako portua irekitzeko
			String p = view.getSelectedPort();
			try {
				portManager.openConnection(p);	//Portua irekitzen saiatzen da
				view.displayStatus("Connection succesfull");	//JTextField-en mezua idatzi
			} catch (SerialPortException e1) {
				view.displayStatus("Error when connecting to port "+p);	//JTextField-en mezua idatzi
				e1.printStackTrace();
			}
			break;
			
		case "delete":
			if (JOptionPane.showConfirmDialog(view, "Any data related with this user will be\nlost. Are you sure?", "Delete Account", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
//				UserHandler.removeUserFromFile(view.getSession().getName());
				System.out.println("Account deleted");
			}
			break;
		case "create":
			CreateAccountDialog dialog = new CreateAccountDialog(view, "Create user", true);
			if (dialog.userIsCreated()) {
				UserHandler.saveUserToFile(dialog.getUserData());	//Dialogoari erabiltzailearen datuak eskatzen dizkio eta fitxategia abtean gordetzen ditu
			}
			break;
		default:
			break;
		}
	}


}
