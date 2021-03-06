package pbl.io;

import jssc.SerialPort;
import jssc.SerialPortException;
import pbl.leapmotion.SampleListener;

public class Serial extends Thread{

	private boolean end;
	private SerialPort port;
	private SampleListener listener;
	
	public Serial(SerialPort port, SampleListener listener) {
		this.port = port;
		this.listener = listener;
		end = false;
	}
	
	@Override
	public void run() {
		while (!end) {
			if (!end) {
				try {
					if (listener != null) {
						for (int i = 0; i < 5; i++) {
							port.writeByte((byte)(i+100));// 100-104 tarteko zenbaki bat bidaltzen du identifikatzaile gisa
							Thread.sleep(20);
							port.writeByte((byte)listener.getAngle(i));// behatzen angeluak bidaltzen ditu
							Thread.sleep(20);
						}
					}
				} catch (SerialPortException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void finish() {
		end = true;
	}
	
	
}
