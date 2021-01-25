package pbl;

import java.io.IOException;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.HandList;

import jssc.SerialPort;
import jssc.SerialPortException;

public class Principal {

	String port;
	SerialPort serialPort;
	SerialIO serial;
	SampleListener listener;

	public Principal() {
		port = Serial.lstPorts();
		serialPort = Serial.openPort(port);

		if (serialPort != null) {
			serial = new SerialIO(serialPort);
			serial.start();
			runLeapMotion();
		}
	}

	private void runLeapMotion() {
		listener = new SampleListener();
		Controller controller = new Controller();

		Frame frame = controller.frame(); // controller is a Controller object

		// Have the sample listener receive events from the controller
		controller.addListener(listener);

		// Keep this process running until Enter is pressed
//		System.out.println("Press Enter to quit...");
//		try {
//			System.in.read();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		if (false){
			controller.removeListener(listener);	// Remove the sample listener when done
		}
		
	}

	public class SerialIO extends Thread {
		boolean end = false;

		SerialPort port;

		public SerialIO(SerialPort port) {
			this.port = port;
		}

		@Override
		public void run() {
			while (!end) {
				if (!end) {
					try {
						if (listener != null) {
//							System.out.println((byte)listener.getTotalAngle());
							for (int i = 0; i < 5; i++) {
								port.writeByte((byte)(i+100));
								Thread.sleep(100);
								port.writeByte((byte)listener.getAngle(i));
								Thread.sleep(100);
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

	public static void main(String[] args) {
		Principal p = new Principal();
	}

}
