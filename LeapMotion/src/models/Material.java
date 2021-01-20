package models;

public class Material {

	String name;
	boolean selected;
	
	public Material(String name) {
		this.name = name;
		selected = false;
	}

	@Override
	public String toString() {
		return name;
	}
	
}
