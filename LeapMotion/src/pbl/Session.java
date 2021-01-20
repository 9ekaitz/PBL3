package pbl;

public class Session {
	
	String name, profilePic;
	boolean priviledges;
	
	/* Sesio bat erabiltzaile bat logeatzean sortzen da, erabiltzailearen datuak dauzka, eta baimenen informazioa.
	 * Sesioekin erabiltzaile bakoitzak zer aldaketa edo prozezu egiten dituen erregistratu daiteke */
	
	public Session(String[] data) {
		/* Profil argazkia gehitu daiteke */
		this.name = data[0];
		this.profilePic = data[1];	//Eremu hau pasahitza da, profil argazkia inplementatzeko aldatu behar da beste eremu batera
		this.priviledges = (data[2].equals("T")?true:false);
	}

	public String getName() {
		return name;	//Menuan erbiltzailearen izena agertzeko
	}
	
	public boolean isPriviledged() {
		return priviledges;
	}
	
}
