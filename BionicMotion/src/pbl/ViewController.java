package pbl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dialogs.AddMaterialDialog;
import launcher.Launcher;
import models.Material;
import models.MaterialModel;
import models.PortManager;
import models.ProductModel;
import views.AppMenu;
import views.MaterialView;
import views.ProcessView;

public class ViewController implements ActionListener, ListSelectionListener {

	MainViewFrame view;
	MaterialModel materialModel;
	ProductModel productModel;
	PortManager portManager = null;

	public ViewController(MainViewFrame view, MaterialModel materialModel, ProductModel productModel) {
		this.view = view;
		this.materialModel = materialModel;
		this.productModel = productModel;
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
		
		ProcessView panel = null;
		
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
			view.setActualPanel(new AppMenu(this, materialModel, productModel));
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
			panel.startProcess();
			break;
		case "next":
			nextMaterial(panel);
			break;
		default:
			break;
		}
	}

	private void nextMaterial(ProcessView panel) {
		 panel = (ProcessView) view.getPanel();
		 if (panel.getMaterialLst().getSelectedIndex()+1 >= panel.getMaterialLst().getModel().getSize()) {
			 panel.finishProgress();
			 JOptionPane.showMessageDialog(view, "You have created a product", "Completed", JOptionPane.INFORMATION_MESSAGE);
			 productModel.addProduct(panel.getProduct());
			 view.setActualPanel(new AppMenu(this, materialModel, productModel));
			 
		 } else {
			 panel.updateProgress();
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

		int size = panel.getProduct().getSize();
		
		if (productName.isEmpty()) {
			JOptionPane.showMessageDialog(view, "You must enter a product name!", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(size < 1){
			JOptionPane.showMessageDialog(view, "You must select at least one material", "Error", JOptionPane.ERROR_MESSAGE);
		}else {
			panel.getProduct().setName(productName);
			view.setActualPanel(new ProcessView(this, panel.getProduct()));
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
	
	public PortManager getPortManager() {
		return portManager;
	}

	public void setPortManager(PortManager portManager) {
		this.portManager = portManager;
	}
	
}
