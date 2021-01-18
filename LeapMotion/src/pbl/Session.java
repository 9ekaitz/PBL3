package pbl;

public class Session {
	
	String name, profilePic;
	boolean priviledges;
	
	public Session(String[] data) {
		/* Profil argazkia gehitu daiteke */
		this.name = data[0];
		this.profilePic = data[1];
		this.priviledges = (data[2].equals("T")?true:false);
	}

	public String getName() {
		return name;
	}
	
}
