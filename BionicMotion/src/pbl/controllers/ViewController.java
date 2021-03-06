package pbl.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jfree.chart.event.ChartChangeEvent;
import org.jfree.chart.event.ChartChangeEventType;

import pbl.dialogs.AddMaterialDialog;
import pbl.display.AppMenu;
import pbl.display.MainFrame;
import pbl.display.MaterialView;
import pbl.display.ProcessView;
import pbl.io.PortManager;
import pbl.launcher.Launcher;
import pbl.models.Material;
import pbl.models.MaterialModel;
import pbl.models.Product;
import pbl.models.ProductModel;

public class ViewController implements ActionListener, ListSelectionListener {

	MainFrame view;
	MaterialModel materialModel;
	ProductModel productModel;
	PortManager portManager;
	Map<String, Integer> typeMap;

	public ViewController(MainFrame view, MaterialModel materialModel, ProductModel productModel) {
		this.view = view;
		this.materialModel = materialModel;
		this.productModel = productModel;
		this.portManager = new PortManager();
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
				if (lst.getSelectedValue().isSelected()) panel.addToRecipe(lst.getSelectedValue());	//Materiala produktura gehitzeko
				else panel.removeFromRecipe(lst.getSelectedValue());	//Materiala produktutik kentzeko
				lst.clearSelection();
			}
		}
		else if(view.getPanel() instanceof AppMenu){
			AppMenu panel = (AppMenu) view.getPanel();
			JList<Product> lst = panel.getProductList();
			
			if (lst.getSelectedValue() != null) {
				typeMap = new HashMap<>();

				lst.getSelectedValue().getMaterials().forEach(d->{	//Material moten mapa egiteko
		            Integer count = typeMap.get(d.getType());
		            if (count == null) count = 1;
		            else count++;
		            typeMap.put(d.getType(), count);
		        });
				
				panel.getChart().getDataset().clear();
				
				for (String string:typeMap.keySet() ) {
					panel.getChart().getDataset().setValue(string, new Double(typeMap.get(string)));	//Grafikoaren datu modeloa
				}
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
			shutdownMachine(); // Ordenagailua itzaltzeko
			break;
		case "startcreateProduct":
			view.setActualPanel(new MaterialView(this, materialModel)); // Panelez aldatzeko funtzioa
			materialModel.resetSelection(); // Material guztiak aukeratu gabe agerrarazten ditu
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
			panel = (ProcessView) view.getPanel();
			panel.finishProgress(); 
			view.setActualPanel(new MaterialView(this, materialModel));
			materialModel.resetSelection();
			
			break;
		case "start":
			panel = (ProcessView) view.getPanel();
			panel.getMaterialLst().setSelectedIndex(0); // Lehen elementua aukeratzeko
			if (portManager.getPort() != null) { // Portu seriala irekita (datuak bidaltzeko prest) dagoela ziurtatzeko
				panel.startProcess();
			} else {
				JOptionPane.showMessageDialog(view, "You must open the port first", "Warning", JOptionPane.WARNING_MESSAGE);
			}
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
		 if (panel.getMaterialLst().getSelectedIndex()+1 >= panel.getMaterialLst().getModel().getSize()) {// Materiala zerrendako azken materiala den jakiteko
			 panel.finishProgress();
			 JOptionPane.showMessageDialog(view, "You have created a product", "Completed", JOptionPane.INFORMATION_MESSAGE);
			 productModel.addProduct(panel.getProduct()); // Produktua zerrendara gehitzeko
			 view.setActualPanel(new AppMenu(this, materialModel, productModel));
			 
		 } else {
			 panel.updateProgress();
		 }
	}

	private void addMaterial() {
		AddMaterialDialog dialog = new AddMaterialDialog(view, "Add material", true);	//Materiala gehitzeko dialgoa irekitzen da	
		Material material = dialog.getMaterial();
		if (material != null) {
			materialModel.addMaterial(material); // Dialogotik material bat hartu eta listan sartzen du
		}
	}

	private void removeMaterial() {
		AppMenu appMenu = (AppMenu) view.getPanel();
		materialModel.removeMaterial(appMenu.getMaterialList().getSelectedValue()); 
	}

	private void createProduct() {
		MaterialView panel = (MaterialView) view.getPanel(); 
		String productName = panel.getProductName();  // Produktuaren izena jasotzen du

		int size = panel.getProduct().getSize(); // Material kantitatea
		
		if (productName.isEmpty()) { // Izen bat sartu dugula ziurtatzeko
			JOptionPane.showMessageDialog(view, "You must enter a product name!", "Error", JOptionPane.ERROR_MESSAGE);
		} else if(size < 1){ // Gutxienez material bat aukeratu dugula ziurtatzeko
			JOptionPane.showMessageDialog(view, "You must select at least one material", "Error", JOptionPane.ERROR_MESSAGE);
		}else {
			panel.getProduct().setName(productName); // Produktuari bere izena esleitzen diogu
			view.setActualPanel(new ProcessView(this, panel.getProduct()));
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
	
	public PortManager getPortManager() {
		return portManager;
	}

	public void setPortManager(PortManager portManager) {
		this.portManager = portManager;
	}
	
}
