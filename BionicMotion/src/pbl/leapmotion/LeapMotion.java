package pbl.leapmotion;

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
		controller.addListener(listener);
		
		while (!end) {
		}
		// Amaitutakoan listener-a kentzeko
		controller.removeListener(listener);
	}
	
}
