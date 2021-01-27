package pbl.models;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import pbl.io.FileHandler;

public abstract class UserHandler {

	private static String PATH = "";//Fitxategia, klasea estatikoa denez hau aldatzeko aukera egon beharko litzateke

	public static void setPath(String path) {
		PATH = path;
	}
	
	public static void saveUserToFile(String[] user) {
		FileHandler.saveToFile(new User(user), PATH);
	}
	
	public static void removeUserFromFile(String user) {
		String[] aux = {user, "hash", "F"};
		FileHandler.removeFromFile(new User(aux), PATH);
	}

	public static BufferedReader getUsersFile() {
		/* Erbiltzaileak gordeta dauden fitxategia irakurtzeko bueltatzen du */
		
		BufferedReader file = null;
		try {
			file = new BufferedReader(new FileReader(PATH));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return file;
	}
	
}
