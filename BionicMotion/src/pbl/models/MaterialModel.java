package pbl.models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import pbl.io.FileHandler;

@SuppressWarnings("serial")
public class MaterialModel extends AbstractListModel<Material>{

	private final static String PATH = "res/files/materials.txt";
	List<Material> materialList;
	
	public MaterialModel() {
		materialList = new ArrayList<>();
		loadFromFile();
	}
	
	private void loadFromFile() {
		String line;
		
		try (BufferedReader in = new BufferedReader(new FileReader(PATH))) {
			while((line = in.readLine())!=null){
				if (!line.isEmpty()){
					materialList.add(new Material(line));
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addMaterial(Material m) {
		materialList.add(m);
		fireContentsChanged(materialList, 0, materialList.size());
		FileHandler.saveToFile(m, PATH);
	}
	
	public void removeMaterial(Material m) {
		materialList.remove(m);
		fireContentsChanged(materialList, 0, materialList.size());
		FileHandler.removeFromFile(m, PATH);
	}
	
	@Override
	public int getSize() {
		return materialList.size();
	}

	@Override
	public Material getElementAt(int index) {
		return materialList.get(index);
	}

	public void changeStatus(Material selectedValue) {
		selectedValue.setSelected(!selectedValue.isSelected());
		fireContentsChanged(materialList, 0, getSize());
	}

	public void resetSelection() {
		/* Material guztiak aukeratu gabe jartzeko */
		materialList.forEach(m->m.setSelected(false));
	}
}
