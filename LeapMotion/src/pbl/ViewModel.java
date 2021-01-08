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

public class ViewModel {

	List<User> users;
	final static String FILE_NAME = "files/users.txt";
	
	public ViewModel() {
		users = new ArrayList<>();
		loadUsersFromFile();
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
	
	public void saveUsersOnLoad() {
			
			try (BufferedWriter out = new BufferedWriter(new FileWriter(FILE_NAME))) {
				for (User user : users) {
					out.write(user.getUserName()+"$"+user.getUserPassword()+"$"+user.isAdmin()+"\n");
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	
			
	}
	
	public boolean authorizedUser(String username, String password) {
		for (User user : users) {
			if (user.getUserName().equals(username) && user.getUserPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
	
//	public byte[] encrypt(String toEncrypt) throws Exception {
//			byte[] bytes = toEncrypt.getBytes("UTF-8");
//			Cipher aes = getCipher(true);
//			byte[] cifrado = aes.doFinal(bytes);
//			
//			return cifrado;
//	}
//
//	public String decrypt (byte[] encrypted) throws Exception {
//			Cipher aes = getCipher(false);
//			byte[] bytes = aes.doFinal(encrypted);
//			String desencrypted = new String(bytes, "UTF-8");
//			
//			return desencrypted;
//	}
//
//	private Cipher getCipher(boolean encrypt) throws Exception {
//			final String keyString = "FraseLargaConDiferentesLetrasNumerosYCaracteresEspeciales_·¡È…ÌÕÛ”˙⁄¸‹Ò—1234567890!#%$&()=%_NO_USAR_ESTA_FRASE!_";
//			final MessageDigest digest = MessageDigest.getInstance("SHA");
//			digest.update(keyString.getBytes("UTF-8"));
//			final SecretKeySpec key = new SecretKeySpec(digest.digest(), 0, 16, "AES");
//	
//			final Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
//			if (encrypt) {
//				aes.init(Cipher.ENCRYPT_MODE, key);
//			} else {
//				aes.init(Cipher.DECRYPT_MODE, key);
//			}
//	
//			return aes;
//	}
}
