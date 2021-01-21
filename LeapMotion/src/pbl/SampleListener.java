package pbl;

import com.leapmotion.leap.Bone;
import com.leapmotion.leap.Bone.Type;
import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Vector;

public class SampleListener extends Listener {

	public void onFrame(Controller controller) {
		Frame frame = controller.frame();

		// detectamos mano y entramos al bucle

		Hand leftHand = null, rightHand = null;

		for (Hand hand : frame.hands()) { // Detektatutako eskuak sailkatu
			if (hand.isLeft())
				leftHand = hand;
			else if (hand.isRight())
				rightHand = hand;
		}

		if (leftHand != null) {
			System.out.println("Eskua");
			for (Finger leftFinger : leftHand.fingers()) {
				double totalAngle = 0;
				for (int i = 0; i < 2; i++) {
					Type[] bones = Type.values();
					
					Bone bone = leftFinger.bone(bones[i]);
					
					Vector p1 = bone.prevJoint();
					Vector p2 = bone.nextJoint();
					
					Vector v = new Vector();
					v.setX(p2.getX()-p1.getX());
					v.setY(p2.getY()-p1.getY());
					v.setZ(p2.getZ()-p1.getZ());
					
					Bone nextBone = leftFinger.bone(bones[i+1]);
					
					Vector q1 = nextBone.prevJoint();
					Vector q2 = nextBone.nextJoint();
					
					Vector u = new Vector();
					u.setX(q2.getX()-q1.getX());
					u.setY(q2.getY()-q1.getY());
					u.setZ(q2.getZ()-q1.getZ());
					
					totalAngle += Math.toDegrees(v.angleTo(u));
				}
				if (leftFinger.type() == com.leapmotion.leap.Finger.Type.values()[1]) {
					totalAngle = (totalAngle*60)/160;
					if (totalAngle > 60) totalAngle = 60;
					System.out.println(leftFinger.type()+" Angulo: "+totalAngle);
				}
				
			}
		}

		}
		// Pointable pointable = frame.pointables().frontmost();

	public void onInit(Controller controller) {
		System.out.println("Iniciado");
	}

	public void onConnect(Controller controller) {
		System.out.println("Connected");
	}

	public void onDisconnect(Controller controller) {
		// Gailua dekonektaztean exekutatzen da
		System.out.println("Desconectado");

	}

	private double calculateAngle(Vector v, Vector u) {

		double biderketa = (v.getX() * u.getX()) + (v.getY() * u.getY()) + (v.getZ() * u.getZ());

		double moduloV = Math.sqrt(Math.pow(v.getX(), 2) + Math.pow(v.getY(), 2) + Math.pow(v.getZ(), 2));

		double moduloU = Math.sqrt(Math.pow(u.getX(), 2) + Math.pow(u.getY(), 2) + Math.pow(u.getZ(), 2));

		double angulo = biderketa / (moduloV * moduloU);

		angulo = Math.acos(angulo);

		return Math.toDegrees(angulo);
	}

}
