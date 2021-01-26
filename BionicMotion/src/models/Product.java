package models;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import pbl.io.Saveable;

@SuppressWarnings("serial")
public class Product extends AbstractListModel<Material> implements Saveable{

	private List<Material> lst;
	private String name;
	
	public Product(String line) {
		lst = new ArrayList<>();
		String[] data = line.split("&");
		this.name = data[0];
		String[] materials = data[1].split("[%]");
		for (String material : materials) {
			lst.add(new Material(material));
		}
	}
	
	public Product() {
		lst = new ArrayList<>();
	}
	
	@Override
	public Material getElementAt(int arg0) {
		return lst.get(arg0);
	}

	@Override
	public int getSize() {
		return lst.size();
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}

	public void addMaterial(Material m) {
		lst.add(m);
		fireContentsChanged(lst, 0, getSize());
	}

	public void removeMaterial(Material m) {
		lst.remove(m);
		fireContentsChanged(lst, 0, getSize());
	}
	
	public List<Material> getMaterials(){
		return lst;
	}
	
	@Override
	public String toFile() {
		StringBuilder materials = new StringBuilder();
		lst.forEach((p)->materials.append(p.toFile()+"%"));
		return name+"&"+materials;
	}

}
