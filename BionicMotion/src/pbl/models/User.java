package pbl.models;

import pbl.io.Removeable;
import pbl.io.Saveable;

public class User implements Saveable, Removeable{

	private String name, password, priviledge;
	
	public User(String[] data) {
		this.name = data[0];
		this.password = data[1];
		this.priviledge = data[2];
	}

	@Override
	public String toFile() {
		/* Erabiltzailea fitxategi batean gordetzeko formatua sortzen du */
		
		return name+"$"+password+"$"+priviledge;
	}

	@Override
	public boolean isTheSame(String line) {
		/* Fitxategi batetik irakurritako erabiltzailea berdina den konprobatzeko */
		
		String param[] = line.split("[$]");
		return name.equals(param[0]);
	}
}
