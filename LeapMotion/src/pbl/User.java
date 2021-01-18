package pbl;

public class User {

	String username;
	int userPassword;
	boolean admin;
	
	public User(String user) {
		String[] args = user.split("[$]");
		this.username = args[0];
		this.userPassword = Integer.parseInt(args[1]);
		this.admin = Boolean.parseBoolean(args[2]);
	}
	
	public User(String user, int password, boolean admin) {
		this.username = user;
		this.userPassword = password;
		this.admin = admin;
	}
	
	public String getUserName() {
		return username;
	}

	public int getUserPasswordHash() {
		return userPassword;
	}

	public boolean isAdmin() {
		return admin;
	}

	@Override
	public String toString() {
		return "User [UserName=" + username + ", Password=" + userPassword + ", admin=" + admin + "]";
	}
	
	
	
}
