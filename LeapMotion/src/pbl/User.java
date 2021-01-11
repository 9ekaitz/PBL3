package pbl;

public class User {

	int userName, userPassword;
	boolean admin;
	
	public User(String user) {
		String[] args = user.split("[$]");
		this.userName = Integer.parseInt(args[0]);
		this.userPassword = Integer.parseInt(args[1]);
		this.admin = Boolean.parseBoolean(args[2]);
	}
	
	public User(int user, int password, boolean admin) {
		this.userName = user;
		this.userPassword = password;
		this.admin = admin;
	}
	
	public int getUserNameHash() {
		return userName;
	}
	
	public int getUserPasswordHash() {
		return userPassword;
	}

	public boolean isAdmin() {
		return admin;
	}

	@Override
	public String toString() {
		return "User [UserName=" + userName + ", Password=" + userPassword + ", admin=" + admin + "]";
	}
	
	
	
}
