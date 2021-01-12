package models;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

public class ProcessList extends AbstractListModel<String>{

	List<String> processList;
	int processIndex = 0;
	
	public ProcessList() {
		processList = new ArrayList<>();
		pruebas();
	}
	
	private void pruebas() {
		addMaterial("Óxido nitroso");
		addMaterial("Bromuro de potasio");
		addMaterial("Agua");
		
	}

	public void addMaterial(String materialName) {
		processList.add(materialName);
		this.fireContentsChanged(processList, 0, processList.size());
	}
	
	public List<String> getList() {
		
		return processList;
	}
	
	@Override
	public int getSize() {
		return processList.size();
	}

	@Override
	public String getElementAt(int index) {
		return processList.get(index);
	}

	

}
