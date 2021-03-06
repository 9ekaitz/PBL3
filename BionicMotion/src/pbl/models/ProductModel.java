package pbl.models;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import pbl.io.FileHandler;

@SuppressWarnings("serial")
public class ProductModel extends AbstractListModel<Product>{
	
	private final static String PATH = "res/files/products.txt";	//Produktuak gordetzeko fitxategia
	List<Product> lst;
	
	public ProductModel() {
		lst = new ArrayList<>();
		loadFromFile();	//Produktuak fitxategitik kargatu
	}
	
	private void loadFromFile() {
		String line;
		try (BufferedReader in = new BufferedReader(new FileReader(PATH))) {
			while((line = in.readLine())!=null){
				if (!line.isEmpty()) lst.add(new Product(line));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addProduct(Product p) {
		lst.add(p);
		fireContentsChanged(lst, 0, getSize());
		FileHandler.saveToFile(p, PATH);	//Produktu bakarra fitxategian gordetzeko
	}
	
	public void removeProduct(Product m) {
		lst.remove(m);
		fireContentsChanged(lst, 0, getSize());
	}
	
	@Override
	public Product getElementAt(int arg0) {
		return lst.get(arg0);
	}

	@Override
	public int getSize() {
		return lst.size();
	}

}
