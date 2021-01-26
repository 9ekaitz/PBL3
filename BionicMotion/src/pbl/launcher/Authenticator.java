package pbl.launcher;

import java.io.BufferedReader;
import java.io.IOException;

import models.UserHandler;
import pbl.Session;


public class Authenticator {

	public Session authenticate(String user, String password) {
		
		String line;
		Session session = null;
		
		BufferedReader in = UserHandler.getUsersFile();	//Erabiltzaileen fitxategia eskatzen du
		
		if (in != null) {
			try {
				while ((line = in.readLine()) != null) {
					String[] data = line.split("[$]");
					if (data[0].equals(user) && data[1].equals(String.valueOf(password.hashCode()))) {
						session = new Session(data);
						break;
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		
		return session;
	}
		
}
