package models;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

public class MaterialList extends AbstractListModel<String>{

	List<String> materialsList;
	
	public MaterialList() {
		materialsList = new ArrayList<>();
		pruebas();
	}
	
	private void pruebas() {
		addMaterial("Óxido nitroso");
		addMaterial("Bromuro de potasio");
		addMaterial("Agua");
		addMaterial("Óxido nitroso");
		addMaterial("Bromuro de potasio");
		addMaterial("Agua");
		addMaterial("Óxido nitroso");
		addMaterial("Bromuro de potasio");
		addMaterial("Agua");
		addMaterial("Óxido nitroso");
		addMaterial("Bromuro de potasio");
		addMaterial("Agua");
		addMaterial("Óxido nitroso");
		addMaterial("Bromuro de potasio");
		addMaterial("Agua");
		addMaterial("Óxido nitroso");
		addMaterial("Bromuro de potasio");
		addMaterial("Agua");
		addMaterial("Óxido nitroso");
		addMaterial("Bromuro de potasio");
		addMaterial("Agua");
		addMaterial("Óxido nitroso");
		addMaterial("Bromuro de potasio");
		addMaterial("Agua");
		
	}

	public void addMaterial(String materialName) {
		materialsList.add(materialName);
		this.fireContentsChanged(materialsList, 0, materialsList.size());
	}
	
	@Override
	public int getSize() {
		return materialsList.size();
	}

	@Override
	public String getElementAt(int index) {
		return materialsList.get(index);
	}

	

}
