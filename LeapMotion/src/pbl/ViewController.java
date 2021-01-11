package pbl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ViewController implements ActionListener, ListSelectionListener{
	
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
		case "createProduct":
			/**
			 * TODO: Coger los materiales seleccionados y meterle al contructor en forma de Lista
			 */
			view.setActualPanel(new ProcessView(this));
			break;
		default:
			break;
		}	
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

	private void loginAttempt() {
		Login panel = view.getActualPanel();
		
		int username = panel.getUsernameField().hashCode();
		int password = panel.getPasswordField().hashCode();
		
		if (model.authorizedUser(username, password)) {
			view.setActualPanel(new AppMenu(this));
		} else {
			JOptionPane.showMessageDialog(view, "Username or password is incorrect,\n please try again", "Authentication failed", JOptionPane.ERROR_MESSAGE, new ImageIcon("icons/error.png"));
		}
		
	}
}
