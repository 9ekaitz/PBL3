package launcher;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Authenticator {

	private final static String PATH = "files/users.txt";
	
	public Session authenticate(String user, String password) {
		
		String line;
		Session session = null;
		
		try (BufferedReader in = new BufferedReader(new FileReader(PATH))) {
			while ((line = in.readLine()) != null) {
				String[] data = line.split("[$]");
				if (data[0].equals(user) && data[1].equals(String.valueOf(password.hashCode()))) {
					session = new Session(data);
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return session;
	}
		
}
