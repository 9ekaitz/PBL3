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
//						System.out.println((byte)listener.getTotalAngle());
//						for (int i = 0; i < 5; i++) {
//							port.writeByte((byte)(i+1));
//							Thread.sleep(10);
							port.writeByte((byte)listener.getAngle(1));
							Thread.sleep(100);
//						}
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

	
	
}
