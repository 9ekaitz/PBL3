package pbl.models;

import pbl.io.Removeable;
import pbl.io.Saveable;

public class Material implements Saveable, Removeable{

	private String name;
	private MaterialType type;
	boolean selected;
	
	public Material(String line) {
		String[] data = line.split("[$]");
		this.name = data[0];
		this.type = MaterialType.valueOf(data[1]);
		selected = false;
	}
	
	public Material(String name, String type) {
		this.name = name;
		this.type = MaterialType.valueOf(type.toUpperCase());
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
		return name+"$"+type;
	}

	public String getName() {
		return name;
	}
	
	
	
	public String getType() {
		return String.valueOf(type);
	}

	@Override
	public boolean isTheSame(String line) {
		return line.equals(name);
	}
}
