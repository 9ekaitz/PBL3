package pbl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dialogs.AddMaterialDialog;
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
	
	JList<String> materialJList;
	
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
			addMaterial();
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
			initializeMaterialJlist();
			break;
		case "createProduct":
			createProductWithMaterials();
			break;
		case "goBackFromMaterialView":
			view.setActualPanel(new AppMenu(this, materialListModel));
			break;
		case "goBackFromProcessView":
			view.setActualPanel(new MaterialView(this, materialListModel));
			initializeMaterialJlist();
			break;
		default:
			break;
		}	
	}

	private void initializeMaterialJlist() {
		MaterialView materialView = (MaterialView) view.getActualObject();
		materialJList = materialView.getJlist();
		
	}

	private void addMaterial() {
		AddMaterialDialog dialog = new AddMaterialDialog(view, "Add material", true);
		if (dialog.materialIsCreated()) {
			String material = dialog.getMaterial();
			materialListModel.addMaterial(material);
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
		if (dialog.userIsCreated()) {
			User user = dialog.getUser();
			usersmodel.addUser(user);
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
			view.setActualPanel(new AppMenu(this, materialListModel));
		} else {
			JOptionPane.showMessageDialog(view, "Username or password is incorrect,\n please try again", "Authentication failed", JOptionPane.ERROR_MESSAGE, new ImageIcon("icons/error.png"));
		}
		
	}
	
	private void shutdownMachine() {
		int option = JOptionPane.showConfirmDialog(view, "Are you sure you want to shutdown the machine?");
		
		if (option == JOptionPane.YES_OPTION) {
			Runtime runtime = Runtime.getRuntime();
			try {
				Process proc = runtime.exec("shutdown -s -t 0");
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.exit(0);	
		}
		
	}

}
