package pbl;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import com.fazecast.jSerialComm.*;

public class Principal {

	public static void main(String[] args) {
		
		int i = 0;	//Counter
		int indexOfPort;
		Scanner index = new Scanner(System.in);
		SerialPort ports[] = SerialPort.getCommPorts();
		
		/*	Portu guztiak zerrendatu */
		
		System.out.println("Ports: ");
		for (SerialPort port : ports) {
			System.out.println(i+": "+port.getSystemPortName());
			i++;
		}
		
		System.out.print("Choose the port: ");
		indexOfPort = index.nextInt();
		
		SerialPort choosedPort = ports[indexOfPort];
		
		if (choosedPort.openPort()) {
			System.out.println("Succesfully opened the port!");
		} else {
			System.out.println("Unable to opne the port :(");
			return;
		}
	
			
		BufferedInputStream input = new BufferedInputStream(choosedPort.getInputStream());
		PrintStream output = new PrintStream(choosedPort.getOutputStream());
		
		try {
			output.write(45);
			System.out.println(input.read());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
