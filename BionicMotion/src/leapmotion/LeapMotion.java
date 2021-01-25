package leapmotion;

import java.io.IOException;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;

public class LeapMotion extends Thread{

	private boolean end;
	private SampleListener listener;
	Controller controller;
	Frame frame;
	
	public LeapMotion() {
		end = false;
	}
	
	@Override
	public void run() {
		listener = new SampleListener();
		controller = new Controller();
		frame = controller.frame();
		// Have the sample listener receive events from the controller
		controller.addListener(listener);
		
		while (!end) {
		}
		// Remove the sample listener when done
		controller.removeListener(listener);
	}
	
}
