package models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public abstract class FileHandler {

	public static void saveToFile(Saveable item, String PATH) {
		/* Objektua fitxategira gehitzen du */
		
		try (BufferedWriter out = new BufferedWriter(new FileWriter(PATH, true))) {
			out.write(item.toFile());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void removeFromFile(Removeable item, String PATH) {
			/* Emandako erabiltzialea izenarekin bilatzen du eta fitxategiatik kentzen du
			 * Ezabatu den edo ez adierazten duen mezu bat sortu beharko zen
			 */
			
			try {
				File srcFile = new File(PATH);	//Erabiltzaileen fitxategia
				File dstFile = new File("res/files/tmpM");	//Ezabatuko ez diren erabiltzaielak gordetzeko fitxategia
				BufferedWriter dst = new BufferedWriter(new FileWriter(dstFile));
				BufferedReader src = new BufferedReader(new FileReader(srcFile));
				String line;
				
				while ((line = src.readLine()) != null) {
					if (item.isTheSame(line)) {
						continue;
					}
					dst.write(line+"\n");
				}
				src.close();
				dst.close();
				srcFile.delete();	//Fitxategi zaharra ezabatu
				dstFile.renameTo(srcFile);	//Sortutako fitxategi berriari aurreko fitxategiaren izena jarri

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
}
