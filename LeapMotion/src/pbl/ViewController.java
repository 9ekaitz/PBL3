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
import models.MaterialModel;
import models.ProcessList;
import models.UserHandler;
import views.AppMenu;
import views.MaterialView;
import views.ProcessView;
import views.Settings;

public class ViewController implements ActionListener, ListSelectionListener{
	
	MainViewFrame view;
	MaterialModel materialModel;
	ProcessList processListModel;
	
	JList<String> materialJList;
	
	public ViewController(MainViewFrame view, MaterialModel materialModel) {
		this.view = view;
		this.materialModel = materialModel;
		
		processListModel = new ProcessList();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String command = arg0.getActionCommand();
		
		switch (command) {
		case "logout":	//Erabiltzailearen sesioa amaitzen da eta login leihoa irekitzen da
			Launcher l = new Launcher();
			view.close();
			break;
		case "addMaterial":
			addMaterial();
			break;
		case "removeMaterial":
			removeMaterial();
			break;
		case "shutdown":
			shutdownMachine();	
			break;
		case "startcreateProduct":
			view.setActualPanel(new MaterialView(this, materialModel));
			initializeMaterialJlist();
			break;
		case "createProduct":
			createProductWithMaterials();
			break;
		case "goBackFromMaterialView":
			view.setActualPanel(new AppMenu(this, materialModel));
			break;
		case "goBackFromProcessView":
			view.setActualPanel(new MaterialView(this, materialModel));
			initializeMaterialJlist();
			break;
		default:
			break;
		}	
	}

	private void addMaterial() {
		AddMaterialDialog dialog = new AddMaterialDialog(view, "Add material", true);
		if (dialog.materialIsCreated()) {
			String material = dialog.getMaterial();
			materialModel.addMaterial(material);
		}
	}
	
	private void removeMaterial() {
		AppMenu appMenu = (AppMenu) view.getPanel();
		materialModel.removeMaterial(appMenu.getMaterialList().getSelectedValue());
	}

	private void initializeMaterialJlist() {
		MaterialView materialView = (MaterialView) view.getPanel();
		materialJList = materialView.getJlist();
		
	}

	

	private void createProductWithMaterials() {
		MaterialView matView = (MaterialView) view.getPanel();
		String productName = matView.getProductName();
		int materialQuantity = 15; //////////////////////////// HACE FALTA METER LA CANTIDAD DE MATERIALES SELECCIONADOS EN LA JLIST
		
		if (productName.isEmpty()) {
			JOptionPane.showMessageDialog(view, "You must enter a product name!", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			view.setActualPanel(new ProcessView(this, processListModel, productName, materialQuantity));
		}		
	}


	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) return;
			if (e.getSource() instanceof MaterialModel){
 				MaterialModel model = (MaterialModel) e.getSource();
 				AppMenu panel = (AppMenu) view.getPanel();
 				model.changeStatus(panel.getMaterialList().getSelectedValue());
			}
		
	}

	private void shutdownMachine() {
		int option = JOptionPane.showConfirmDialog(view, "Are you sure you want to shutdown the machine?");
		
//		if (option == JOptionPane.YES_OPTION) {
//			Runtime runtime = Runtime.getRuntime();
//			try {
//				Process proc = runtime.exec("shutdown -s -t 0");
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			System.exit(0);	
//		}
		
	}

}
