package models;

import java.util.ArrayList;
import java.util.List;

import javax.jws.Oneway;
import javax.swing.AbstractListModel;

public class ProcessList extends AbstractListModel<Material>{

	List<Material> processList;
	int processIndex = 0;
	
	public ProcessList() {
		processList = new ArrayList<>();
		pruebas();
	}
	
	private void pruebas() {
		addMaterial(new Material("�xido nitroso"));
		addMaterial(new Material("Bromuro de potasio"));
		addMaterial(new Material("Agua"));
		
	}

	public void addMaterial(Material materialName) {
		processList.add(materialName);
		this.fireContentsChanged(processList, 0, processList.size());
	}
	
	public List<Material> getList() {
		
		return processList;
	}
	
	@Override
	public int getSize() {
		return processList.size();
	}

	@Override
	public Material getElementAt(int index) {
		return processList.get(index);
	}

	

}
