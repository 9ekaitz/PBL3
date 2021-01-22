package models;

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
	
	public static void saveMaterialToFile(Material m) {
		/* Materiala fitxategira gehitzen du */
		
		try (BufferedWriter out = new BufferedWriter(new FileWriter(PATH, true))) {
			out.write(m+"\n");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addMaterial(Material m) {
		materialList.add(m);
		fireContentsChanged(materialList, 0, materialList.size());
		saveMaterialToFile(m);
	}
	
	public void removeMaterial(Material m) {
		materialList.remove(m);
		fireContentsChanged(materialList, 0, materialList.size());
		removeMaterialFromFile(m.toString());
	}
	
	public static void removeMaterialFromFile(String m) {
		/* Emandako erabiltzialea izenarekin bilatzen du eta fitxategiatik kentzen du
		 * Ezabatu den edo ez adierazten duen mezu bat sortu beharko zen
		 */
		
		try {
			File srcFile = new File(PATH);	//Erabiltzaileen fitxategia
			File dstFile = new File("res/files/tmpM");	//Ezabatuko ez diren erabiltzaielak gordetzeko fitxategia
			BufferedWriter dst = new BufferedWriter(new FileWriter(dstFile));
			BufferedReader src = new BufferedReader(new FileReader(srcFile));
			String line;
			
			while ((line = src.readLine()) != null) {
				if (line.equals(m)) {
					System.out.println("Material: "+m+" has been deleted");
					continue;
				}
				dst.write(line+"\n");
			}
			src.close();
			dst.close();
			srcFile.delete();	//Fitxategi zaharra ezabatu
			dstFile.renameTo(srcFile);	//Sortutako fitxategi berriari aurreko fitxategiaren izena jarri

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		materialList.forEach(m->m.setSelected(false));
	}
}
