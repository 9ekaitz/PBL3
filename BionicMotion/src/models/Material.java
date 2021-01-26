package models;

import pbl.io.Removeable;
import pbl.io.Saveable;

public class Material implements Saveable, Removeable{

	private String name;
	boolean selected;
	
	public Material(String name) {
		this.name = name;
		selected = false;
	}

	@Override
	public String toString() {
		return name;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	@Override
	public String toFile() {
		return name+"\n";
	}

	public String getName() {
		return name;
	}
	
	@Override
	public boolean isTheSame(String line) {
		return line.equals(name);
	}
}
