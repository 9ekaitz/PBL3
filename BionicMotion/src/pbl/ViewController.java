package pbl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dialogs.AddMaterialDialog;
import launcher.Launcher;
import models.Material;
import models.MaterialModel;
import views.AppMenu;
import views.MaterialView;
import views.ProcessView;

public class ViewController implements ActionListener, ListSelectionListener {

	MainViewFrame view;
	MaterialModel materialModel;

	public ViewController(MainViewFrame view, MaterialModel materialModel) {
		this.view = view;
		this.materialModel = materialModel;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) return;
		if (view.getPanel() instanceof MaterialView) {
			MaterialView panel = (MaterialView) view.getPanel();
			JList<Material> lst = panel.getMaterialList();
			MaterialModel model = (MaterialModel) lst.getModel();
			
			if (lst.getSelectedValue() != null) {
				model.changeStatus(lst.getSelectedValue());
				if (lst.getSelectedValue().isSelected()) panel.addToRecipe(lst.getSelectedValue());
				else panel.removeFromRecipe(lst.getSelectedValue());
				lst.clearSelection();
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		ProcessView panel;
		
		switch (arg0.getActionCommand()) {
		case "logout": // Erabiltzailearen sesioa amaitzen da eta login leihoa irekitzen da
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
			materialModel.resetSelection();
			break;
		case "createProduct":
			createProduct();
			break;
		case "goBackFromMaterialView":
			view.setActualPanel(new AppMenu(this, materialModel));
			break;
		case "goBackFromProcessView":
			view.setActualPanel(new MaterialView(this, materialModel));
			break;
		case "cancel":
			view.setActualPanel(new MaterialView(this, materialModel));
			materialModel.resetSelection();
			break;
		case "start":
			panel = (ProcessView) view.getPanel();
			panel.getMaterialLst().setSelectedIndex(0);
			break;
		case "next":
			 panel = (ProcessView) view.getPanel();
			 if (panel.getMaterialLst().getSelectedIndex() > panel.getMaterialLst().getModel().getSize()) {
				 //////////////////////////////////////////////////////////////////////////////////////////
				 // produktua gehitu
				 //////////////////////////////////////////////////////////////////////////////////////////
			 } else {
				 panel.getMaterialLst().setSelectedIndex(panel.getMaterialLst().getSelectedIndex()+1);
			}
			break;
		default:
			break;
		}
	}

	private void addMaterial() {
		AddMaterialDialog dialog = new AddMaterialDialog(view, "Add material", true);
		Material material = dialog.getMaterial();
		if (material != null) {
			materialModel.addMaterial(material);
		}
	}

	private void removeMaterial() {
		AppMenu appMenu = (AppMenu) view.getPanel();
		materialModel.removeMaterial(appMenu.getMaterialList().getSelectedValue());
	}

	private void createProduct() {
		MaterialView panel = (MaterialView) view.getPanel();
		String productName = panel.getProductName();

		if (productName.isEmpty()) {
			JOptionPane.showMessageDialog(view, "You must enter a product name!", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			view.setActualPanel(new ProcessView(this, panel.getProduct()));
			panel.getProduct().setName(productName);
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
