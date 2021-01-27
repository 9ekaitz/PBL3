package pbl.models;

public class Session {
	
	String name;
	boolean priviledges;
	
	/* Sesio bat erabiltzaile bat logeatzean sortzen da, erabiltzailearen datuak dauzka, eta baimenen informazioa.
	 * Sesioekin erabiltzaile bakoitzak zer aldaketa edo prozesu egiten dituen erregistratu daiteke */
	
	public Session(String[] data) {
		this.name = data[0];
		this.priviledges = (data[2].equals("T")?true:false);
	}

	public String getName() {
		return name;	//Menuan erbiltzailearen izena agertzeko
	}
	
	public boolean isPriviledged() {
		return priviledges;
	}
	
}
