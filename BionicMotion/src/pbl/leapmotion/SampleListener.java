package pbl.leapmotion;

import com.leapmotion.leap.Bone;
import com.leapmotion.leap.Bone.Type;
import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Vector;

public class SampleListener extends Listener {

	double angles[] = {0,0,0,0,0};
	Type[] bones = Type.values();

	public void onFrame(Controller controller) {
		Frame frame = controller.frame();	//Kontroladoretik fotograma hartzen dugu
	
		Hand leftHand = null, rightHand = null;

		for (Hand hand : frame.hands()) { // Detektatutako eskuak sailkatu
			if (hand.isLeft())
				leftHand = hand;
			else if (hand.isRight())
				rightHand = hand;
		}

		if (leftHand != null) {
			for (int j = 0; j < 5; j++) {
				Finger leftFinger = leftHand.fingers().get(j);
				
				double tmpAngle = 0;
				for (int i = 0; i < 2; i++) {
					Bone bone = leftFinger.bone(bones[i]);
					Vector p1 = bone.prevJoint();
					Vector p2 = bone.nextJoint();
					
					//Lehenengo hezurraren bektorea sortu
					Vector v = new Vector();
					v.setX(p2.getX()-p1.getX());
					v.setY(p2.getY()-p1.getY());
					v.setZ(p2.getZ()-p1.getZ());
					
					
					Bone nextBone = leftFinger.bone(bones[i+1]);
					Vector q1 = nextBone.prevJoint();
					Vector q2 = nextBone.nextJoint();
					
					//Hurrengo hezurraren bektorea sortu
					Vector u = new Vector();
					u.setX(q2.getX()-q1.getX());
					u.setY(q2.getY()-q1.getY());
					u.setZ(q2.getZ()-q1.getZ());
					
					tmpAngle += Math.toDegrees(v.angleTo(u));// Angelua kalkulatu radianetan eta graduetara pasatu
				}
				map(leftFinger, tmpAngle, j);			}
		}

	}

	private void map(Finger finger, double angle, int index) {
		switch (finger.type()) { //Behatzaren arabera angeluak mapeatu
		case TYPE_THUMB:
			angle = (angle*60)/45;
			if (angle > 60) angle = 60;//Maximoa
			else if (angle < 5) angle = 0;//Minimoa
			break;
		case TYPE_INDEX:
			angle = ((angle-20)*60)/110;
			if (angle > 60) angle = 60;//Maximoa
			else if (angle < 5) angle = 0;//Minimoa
			break;
		case TYPE_MIDDLE:
			angle = ((angle-20)*80)/110;
			if (angle > 80) angle = 80;//Maximoa
			else if (angle < 5) angle = 0;//Minimoa
			break;
		case TYPE_RING:
			angle = ((angle-20)*70)/120;
			if (angle > 70) angle = 70;//Maximoa
			else if (angle < 5) angle = 0;//Minimoa
			break;
		case TYPE_PINKY:
			angle = ((angle-20)*60)/110;
			if (angle > 60) angle = 60;//Maximoa
			else if (angle < 1) angle = 0;//Minimoa
			break;
		default:
			break;
		}
		angles[index] = angle;
	}

	public void onInit(Controller controller) {
		System.out.println("Started");
	}

	public void onConnect(Controller controller) {
		System.out.println("Connected");
	}

	public void onDisconnect(Controller controller) {
		// Gailua deskonektatzean exekutatzen da
		System.out.println("Disconnected");

	}

	public double getAngle(int index) {
		return angles[index];
	}
	
}
