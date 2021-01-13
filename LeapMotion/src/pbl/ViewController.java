package pbl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import models.MaterialList;
import models.ProcessList;
import models.UsersModel;
import views.AppMenu;
import views.Login;
import views.MaterialView;
import views.ProcessView;
import views.TestPanel;

public class ViewController implements ActionListener, ListSelectionListener{
	
	MainViewFrame view;
	UsersModel usersmodel;
	MaterialList materialListModel;
	ProcessList processListModel;
	
	public ViewController(MainViewFrame view) {
		this.view = view;
		usersmodel = new UsersModel();
		materialListModel = new MaterialList();
		processListModel = new ProcessList();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String command = arg0.getActionCommand();
		
		switch (command) {
		case "loginAttempt":
			loginAttempt();
			break;
		case "createAccount":
			/**
			 * TODO: Create account dialog
			 */
			break;
		case "logout":
			view.setActualPanel(new Login(this));
			break;
		case "addMaterial":
			/**
			 * TODO: Material add dialog
			 */
			break;
		case "removeMaterial":
			/**
			 * TODO: Selected material remove from list
			 */
			break;
		case "shutdown":
			shutdownMachine();	
			break;
		case "seeMaterial":
			view.setActualPanel(new MaterialView(this, materialListModel));
			break;
		case "goBackFromMaterialView":
			view.setActualPanel(new AppMenu(this));
			break;
		case "goBackFromProcessView":
			view.setActualPanel(new MaterialView(this, materialListModel));
			break;
		case "createProduct":
			/**
			 * TODO: Coger los materiales seleccionados y meterle al contructor en forma de Lista
			 */
			view.setActualPanel(new ProcessView(this, processListModel));
			break;
		default:
			break;
		}	
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) return;
			if (e.getSource() instanceof MaterialList){
 				MaterialList lista = (MaterialList) e.getSource();
 				System.out.println("MaterialList");
			} else if (e.getSource() instanceof ProcessList) {
				System.out.println("ProcessList");
			}
		
	}

	private void loginAttempt() {
		Login panel = view.getActualPanel();
		
		int username = panel.getUsernameField().hashCode();
		int password = panel.getPasswordField().hashCode();
		
		if (usersmodel.authorizedUser(username, password)) {
			view.setActualPanel(new AppMenu(this));
		} else {
			JOptionPane.showMessageDialog(view, "Username or password is incorrect,\n please try again", "Authentication failed", JOptionPane.ERROR_MESSAGE, new ImageIcon("icons/error.png"));
		}
		
	}
	
	private void shutdownMachine() {
		Runtime runtime = Runtime.getRuntime();
		try {
			Process proc = runtime.exec("shutdown -s");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.exit(0);	
	}

}
