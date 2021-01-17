package models;

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

import pbl.User;

public class UsersModel {

	private final static String PATH = "files/users.txt";
	List<User> users;
	
	public UsersModel() {
		users = new ArrayList<>();
		loadFromFile();
	}
	
	public void addUser(User user) {
		users.add(user);
		saveToFile();
	}
	
	private void loadFromFile() {	
		String line;
		
		try (BufferedReader in = new BufferedReader(new FileReader(PATH))) {
			while((line = in.readLine())!=null){
				User user = new User(line);
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
	
	private void saveToFile() {
		int userHash;
		int passwordHash;
		
			try (BufferedWriter out = new BufferedWriter(new FileWriter(PATH))) {
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
