package launcher;

public class Session {
	
	String name, profilePic;
	boolean priviledges;
	
	public Session(String[] data) {
		this.name = data[0];
		this.profilePic = data[2];
		this.priviledges = (data[3].equals("T")?true:false);
	}
	
}
