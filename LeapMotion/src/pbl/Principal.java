package pbl;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.Scanner;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
public class Principal {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Enumeration commports = CommPortIdentifier.getPortIdentifiers();
		CommPortIdentifier myCPI= null;
		Scanner myScanner;
		PrintStream myPrintStream;
		
		while(commports.hasMoreElements())
		{
			 myCPI= (CommPortIdentifier) commports.nextElement();
			if(myCPI.getName().equals("COM14")) break;
			
			
		}
			
		CommPort puerto= myCPI.open("Puerto Serie", 2000);
		SerialPort mySerialPort = (SerialPort) puerto;
		
	mySerialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
		
		myScanner = new Scanner(mySerialPort.getInputStream());//flujo de entrada
		myPrintStream = new PrintStream( mySerialPort.getOutputStream());//flujo de salida
			
		
		while (myScanner.hasNextInt()) {//espera a que tenga un int en el puerto
			myScanner.close();
			myScanner=null;
			myScanner= new Scanner(mySerialPort.getInputStream());
		}
		
		int valor = myScanner.nextInt();
		
	//	myPrintStream.print("cosas para mandar");//ESTO MANDA LAS COSAS
		
		
		
		 System.out.println(valor);//
	}

}
