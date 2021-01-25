package leapmotion;

import java.util.Scanner;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class Serial {

	final static int BAUDRATE_9600 = 9600;
	final static int DATABITS_8 = 8;
	final static int STOPBITS_1 = 1;
	final static int PARITY_NONE = 0;

	public static String lstPorts() {

		int port;

		Scanner input = new Scanner(System.in);
		String[] ports = SerialPortList.getPortNames();

		for (int i = 0; i < ports.length; i++) {
			System.out.println(i + ": " + ports[i]);
		}

		System.out.print("Choose the port: ");
		port = input.nextInt();

		return ports[port];
	}

	public static SerialPort openPort(String port) {
		SerialPort serialPort = new SerialPort(port);
		try {

			serialPort.openPort();
			serialPort.setParams(BAUDRATE_9600, DATABITS_8, STOPBITS_1, PARITY_NONE);
			
			return serialPort;

		} catch (SerialPortException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
