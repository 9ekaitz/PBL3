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

public class MaterialList extends AbstractListModel<String>{

	private final static String PATH = "res/files/materials.txt";
	List<String> materialList;
	
	public MaterialList() {
		materialList = new ArrayList<>();
		loadFromFile();
	}
	
	private void loadFromFile() {
		String line;
		
		try (BufferedReader in = new BufferedReader(new FileReader(PATH))) {
			while((line = in.readLine())!=null){
				if (!line.isEmpty()){
					materialList.add(line);
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
				for (String material : materialList) {
					out.write(material+"\n");
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}		
	}
	
	public void addMaterial(String materialName) {
		materialList.add(materialName);
		fireContentsChanged(materialList, 0, materialList.size());
		saveToFile();
	}
	
	@Override
	public int getSize() {
		return materialList.size();
	}

	@Override
	public String getElementAt(int index) {
		return materialList.get(index);
	}

	

}
