package pbl;

import java.awt.Container;

import javax.swing.JFrame;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Pointable;

public class SampleListener extends Listener {

	JFrame jframe;
	Canvas canvas;
	bola bola;
	
	public SampleListener(JFrame jframe) {
		this.jframe = jframe;
		this.jframe.setContentPane(createMainPanel());
		this.jframe.pack();
		this.jframe.setLocationRelativeTo(null);
		this.jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.jframe.setVisible(true);
	}
	
	private Container createMainPanel() {
		bola = new bola();
		canvas = new Canvas(bola);
		return canvas;
	}

	public void onFrame(Controller controller) {
		Frame frame = controller.frame();

		float x, y;
		
		float inclitationHand[][] = new float[2][3];

		// TODO: crear metodo
		// detectamos mano y entramos al bucle
		
		Hand leftHand = null, rightHand = null;
		
		for (Hand hand : frame.hands()) {	//Detektatutako eskuak sailkatu
			/*
			 * Konprobatu behar da bi ezker esku detektatu ahal diren
			 */
			if (hand.isLeft()) {
				leftHand = hand;
			}
			else if (hand.isRight()){
				rightHand = hand;
			}
		}
		if (leftHand != null) {
			System.out.println("Left hand\tPitch: "+leftHand.direction().pitch()
					+"  Yaw: "+leftHand.direction().yaw()
					+"  Roll: "+leftHand.direction().roll());
		}
		if (rightHand != null) {
			System.out.println("Right hand\tPitch: "+rightHand.direction().pitch()
					+"  Yaw: "+rightHand.direction().yaw()
					+"  Roll: "+rightHand.direction().roll());
		}
		Pointable pointable = frame.pointables().frontmost();
		if (leftHand != null) {
			bola.x = (int) leftHand.palmPosition().getX();
			bola.y = (int) leftHand.palmPosition().getZ();
		}
		
		//Eskurekin posizioarekin lehio bat mugitu
		
		canvas.repaint();

	}

	public void onInit(Controller controller) {
		System.out.println("Iniciado");
	}

	public void onConnect(Controller controller) {
		System.out.println("Connected");
	}

	public void onDisconnect(Controller controller) {
		//Gailua dekonektaztean exekutatzen da
		System.out.println("Desconectado");

	}
}
