package models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

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
	
	private void saveToFile() {
			try (BufferedWriter out = new BufferedWriter(new FileWriter(PATH))) {
				for (Material material : materialList) {
					out.write(material+"\n");
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}		
	}
	
	public void addMaterial(Material materialName) {
		materialList.add(materialName);
		fireContentsChanged(materialList, 0, materialList.size());
		saveToFile();
	}
	
	public void removeMaterial(Material materialName) {
		materialList.remove(materialName);
		fireContentsChanged(materialList, 0, materialList.size());
		saveToFile();
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

	

}
