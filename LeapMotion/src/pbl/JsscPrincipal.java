package pbl;

import java.security.DomainCombiner;
import java.util.Scanner;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class JsscPrincipal {

	
	public static void main(String[] args) {
		
		
		final int BAUDRATE_9600 = 9600;
		final int DATABITS_8 = 8;
		final int STOPBITS_1 = 1;
		final int PARITY_NONE = 0;
		
		int indexOfPort;
		Scanner index = new Scanner(System.in);
		
		String[] ports = SerialPortList.getPortNames();
		
		for(int i = 0; i < ports.length; i++) {
		   System.out.println(ports[i]);
		}
		
		System.out.print("Choose the port: ");
		indexOfPort = index.nextInt();

		SerialPort port = new SerialPort(ports[indexOfPort]);
		try {
			
			port.openPort();
//			port.setParams(9600, 8, 1, 0);
			// port.setParams(9600, 8, 1, 0); // alternate technique
//			byte[] buffer = port.readBytes(10 /* read first 10 bytes */);

			port.setParams(BAUDRATE_9600,  DATABITS_8, STOPBITS_1, PARITY_NONE);
			// port.setParams(9600, 8, 1, 0); // alternate technique
			port.writeBytes("1".getBytes());
			port.closePort();
			
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	
	
	}
	
	
	
}
