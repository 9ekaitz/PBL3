package models;

import jssc.SerialPortList;

public class PortManager {

	public String[] getAvaiblePorts() {
		return SerialPortList.getPortNames();
	}
	
}
