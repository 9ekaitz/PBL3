package pbl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ViewController implements ActionListener{
	
	MainViewFrame view;
	UsersModel model;
	
	public ViewController(MainViewFrame view) {
		this.view = view;
		model = new UsersModel();
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
		case "seeMaterial":
			view.setActualPanel(new MaterialView(this));
			break;
		case "goBackFromMaterialView":
			view.setActualPanel(new AppMenu(this));
			break;
		default:
			break;
		}	
	}

	private void loginAttempt() {
		Login panel = view.getActualPanel();
		
		String username = panel.getUsernameField();
		String password = panel.getPasswordField();
		
		if (model.authorizedUser(username, password)) {
			view.setActualPanel(new AppMenu(this));
		} else {
			JOptionPane.showMessageDialog(view, "Username or password is incorrect,\n please try again", "Authentication failed", JOptionPane.ERROR_MESSAGE, new ImageIcon("icons/error.png"));
		}
		
	}
}
