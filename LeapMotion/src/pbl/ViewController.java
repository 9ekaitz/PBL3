package pbl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dialogs.CreateAccountDialog;
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
			createAccount();
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
		case "startcreateProduct":
			view.setActualPanel(new MaterialView(this, materialListModel));
			break;
		case "createProduct":
			createProductWithMaterials();
			
			break;
		case "goBackFromMaterialView":
			view.setActualPanel(new AppMenu(this, materialListModel));
			break;
		case "goBackFromProcessView":
			view.setActualPanel(new MaterialView(this, materialListModel));
			break;
		default:
			break;
		}	
	}

	private void createProductWithMaterials() {
		MaterialView matView = view.getActualPanelMaterial();
		String productName = matView.getProductName();
		int materialQuantity = 15; //////////////////////////// HACE FALTA METER LA CANTIDAD DE MATERIALES SELECCIONADOS EN LA JLIST
		
		if (productName.isEmpty()) {
			JOptionPane.showMessageDialog(view, "You must enter a product name!", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			view.setActualPanel(new ProcessView(this, processListModel, productName, materialQuantity));
		}
		
		
	}

	private void createAccount() {
		CreateAccountDialog dialog = new CreateAccountDialog(view, "Create user", true);
		User user = dialog.getUser();
		usersmodel.addUser(user);
		usersmodel.saveUsersOnFile();
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
			view.setActualPanel(new AppMenu(this, materialListModel));
		} else {
			JOptionPane.showMessageDialog(view, "Username or password is incorrect,\n please try again", "Authentication failed", JOptionPane.ERROR_MESSAGE, new ImageIcon("icons/error.png"));
		}
		
	}
	
	private void shutdownMachine() {
		Runtime runtime = Runtime.getRuntime();
		try {
			Process proc = runtime.exec("shutdown -s -t 1");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.exit(0);	
	}

}
