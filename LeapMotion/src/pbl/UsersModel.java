package pbl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class UsersModel {

	List<User> users;
	final static String FILE_NAME = "files/users.txt";
	
	public UsersModel() {
		users = new ArrayList<>();
		loadUsersFromFile();
	}
	
	public void addUser(User user) {
		users.add(user);
	}
	
	public void loadUsersFromFile() {
		String line = null;
		User user = null;
		
		
		try (BufferedReader in = new BufferedReader(new FileReader(FILE_NAME))) {
			while((line = in.readLine())!=null){
				user = new User(line);
				if (user != null){
					users.add(user);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveUsersOnFile() {
		int userHash;
		int passwordHash;
		
			try (BufferedWriter out = new BufferedWriter(new FileWriter(FILE_NAME))) {
				for (User user : users) {
					userHash = user.getUserNameHash();
					passwordHash = user.getUserPasswordHash();
					out.write(userHash+"$"+passwordHash+"$"+user.isAdmin()+"\n");
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	
			
	}
	
	public boolean authorizedUser(int userHash, int passwordHash) {
		for (User user : users) {
			if ((user.getUserNameHash() == userHash) && (user.getUserPasswordHash() == passwordHash)) {
				return true;
			}
		}
		return false;
	}
}
