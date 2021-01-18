package models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public abstract class UserHandler {

	private static String PATH = "";	//Fitxategia, klasea estatikoa denez hau aldatzeko aukera egon beharko lirateke

	public static void setPath(String path) {
		PATH = path;
	}
	
	public static void saveUserToFile(String[] user) {
		/* Erabiltzailearen datuak fitxategira gehitzen ditu */
		
		try (BufferedWriter out = new BufferedWriter(new FileWriter(PATH, true))) {
			out.write(user[0] + "$" + user[1] + "$" + user[2] + "\n");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void removeUserFromFile(String user) {
		/* Emandako erabiltzialea izenarekin bilatzen du eta fitxategiatik kentzen du, probatu behar da */
		
		try {
			File srcFile = new File(PATH);	//Erabiltzaileen fitxategia
			File dstFile = new File("files/tmp");	//Ezabatuko ez diren erabiltzaielak gordetzeko fitxategia
			BufferedWriter dst = new BufferedWriter(new FileWriter(dstFile));
			BufferedReader src = new BufferedReader(new FileReader(srcFile));
			String line;
			
			while ((line = src.readLine()) != null) {
				if (line.split("[$]")[0].equals(user)) {
					System.out.println("User: "+user+" has been deleted");
					continue;
				}
				dst.write(line+"\n");
			}
			src.close();
			dst.close();
			srcFile.delete();	//
			dstFile.renameTo(srcFile);	//Sortutako fitxategi berriari aurreko fitxategiaren izena jarri

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static BufferedReader getUsersFile() {
		/* Erbiltzaileak gordeta dauden fitxategia irakurtzeko bueltatzen du */
		
		BufferedReader file = null;
		try {
			file = new BufferedReader(new FileReader(PATH));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file;
	}
	
}
