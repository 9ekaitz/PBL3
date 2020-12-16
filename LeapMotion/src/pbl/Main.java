package pbl;

//master2
import java.io.IOException;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.HandList;

public class Main {

	public Main() {
		SampleListener listener = new SampleListener();
		Controller controller = new Controller();

		Frame frame = controller.frame(); // controller is a Controller object
		HandList hands = frame.hands();
		Hand firstHand = hands.get(0);

		// Have the sample listener receive events from the controller
		controller.addListener(listener);

		// Keep this process running until Enter is pressed
		System.out.println("Press Enter to quit...");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Remove the sample listener when done

		controller.removeListener(listener);
		
		System.out.println("despues");
	}

    public static void main(String[] args) {
    	Login display = new Login();
        Main p = new Main();
    }
}
