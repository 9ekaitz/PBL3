package pbl;

public class User {

	String userName, userPassword;
	boolean admin;
	
	public User(String user) {
		String[] args = user.split("[$]");
		this.userName = args[0];
		this.userPassword = args[1];
		this.admin = Boolean.parseBoolean(args[2]);
	}
	
	public User(String user, String password, boolean admin) {
		this.userName = user;
		this.userPassword = password;
		this.admin = admin;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public boolean isAdmin() {
		return admin;
	}
	
	
}
