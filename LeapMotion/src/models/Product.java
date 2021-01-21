package models;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

public class Product extends AbstractListModel<Material>{

	private List<Material> lst;
	private String name;
	
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

}
