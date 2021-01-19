package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.Settings;

public class SettingsController implements ActionListener {

	Settings view;
	
	public SettingsController(Settings view) {
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "refresh":
			view.refreshPorts();
			break;

		default:
			break;
		}
	}


}
