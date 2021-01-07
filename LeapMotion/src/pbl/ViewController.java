package pbl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewController implements ActionListener{
	
	MainView view;
	ViewModel model;
	
	public ViewController(MainView view) {
		this.view = view;
		model = new ViewModel();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String command = arg0.getActionCommand();
		
		switch (command) {
		case "loginAttempt":
			loginAttempt();
			break;
		case "manualManipulation":
			view.setActualPanel(new TestPanel(this));
		break;
		default:
			break;
		}
		
	}

	private void loginAttempt() {
		String username = view.getActualPanelClass().
		String password = "qwerty";
		
		if (model.authorizedUser(username, password)) {
			view.setActualPanel(new AppMenu(this));
		} else {
			System.out.println("Usuario y contraseña incorrectos");
		}
		
	}
}
