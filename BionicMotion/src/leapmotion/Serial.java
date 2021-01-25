package leapmotion;

import jssc.SerialPort;
import jssc.SerialPortException;

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
							port.writeByte((byte)(i+1));
							Thread.sleep(20);
							System.out.println("Sended");
							port.writeByte((byte)listener.getAngle(i));
							Thread.sleep(20);
						}
					}
				} catch (SerialPortException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void finish() {
		end = true;
	}
	
	
}
