package pbl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dialogs.AddMaterialDialog;
import dialogs.CreateAccountDialog;
import launcher.Launcher;
import models.MaterialList;
import models.ProcessList;
import models.UserHandler;
import views.AppMenu;
import views.MaterialView;
import views.ProcessView;

public class ViewController implements ActionListener, ListSelectionListener{
	
	MainViewFrame view;
	MaterialList materialListModel;
	ProcessList processListModel;
	
	JList<String> materialJList;
	
	public ViewController(MainViewFrame view) {
		this.view = view;
		materialListModel = new MaterialList();
		processListModel = new ProcessList();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String command = arg0.getActionCommand();
		
		switch (command) {
		case "createAccount":
			createAccount();
			break;
		case "logout":	//Erabiltzailearen sesioa amaitzen da eta login leihoa irekitzen da
			Launcher l = new Launcher();
			view.close();
			break;
		case "addMaterial":
			addMaterial();
			break;
		case "removeMaterial":
			/**
			 * TODO: Selected material remove from list
			 */
			UserHandler.removeUserFromFile("test");	//Erabiltzaileak ezabatzeko funtzioa probatzeko, hemendik endu behar da,
			//eta ezabatu nahi den erabiltzailearen izena pasatu behar zaio
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
			UserHandler.saveUserToFile(dialog.getUserData());	//Dialogoari erabiltzailearen datuak eskatzen dizkio eta fitxategia abtean gordetzen ditu
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
