package models;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class PortManager {

	private SerialPort port;
	private final int BAUDRATE_9600 = 9600, DATABITS_8 = 8, STOPBITS_1 = 1, PARITY_NONE = 0;
	
	/* Eskuragarri dauden portuak erakusten ditu */
	public String[] getAvaiblePorts() {
		return SerialPortList.getPortNames();
	}

	/* Pasatutako portua irekitzen du */
	public void openConnection(String selectedPort) throws SerialPortException {
		if (port != null) port.closePort();
		port = new SerialPort(selectedPort);
		port.openPort();
		port.setParams(BAUDRATE_9600,  DATABITS_8, STOPBITS_1, PARITY_NONE);
	}
	
	public SerialPort getPort() {
		return port;
	}
}
