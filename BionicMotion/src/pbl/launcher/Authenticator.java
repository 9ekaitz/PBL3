package pbl.launcher;

import java.io.BufferedReader;
import java.io.IOException;

import pbl.models.Session;
import pbl.models.UserHandler;


public class Authenticator {

	public Session authenticate(String user, String password) {
		
		String line;
		Session session = null;
		
		BufferedReader in = UserHandler.getUsersFile();	//Erabiltzaileen fitxategia eskatzen du
		
		if (in != null) {
			try {
				while ((line = in.readLine()) != null) {
					String[] data = line.split("[$]"); // sartutako datuak gordetzen ditu
					if (data[0].equals(user) && data[1].equals(String.valueOf(password.hashCode()))) {
						session = new Session(data); //Datuak zuzenak direla begiratu ondoren sesio berria sortzeko
						break;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
		
		return session;
	}
		
}
