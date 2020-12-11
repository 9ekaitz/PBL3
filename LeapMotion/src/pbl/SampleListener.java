package pbl;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.HandList;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Pointable;
import com.leapmotion.leap.PointableList;

public class SampleListener extends Listener {

	public void onFrame(Controller controller) {
		Frame frame = controller.frame();

		float inclitationHand[][]=new float[3][2];
		
		//TODO: crear metodo
		//detectamos mano y entramos al bucle
		for (Hand hand : frame.hands()) {
			
			
			HandList hands = frame.hands();
			Hand hand0 = hands.get(0);
			Hand hand1 = hands.get(1);

			float pitch_Izquierda = 0;
			float yaw_Izquierda = 0;
			float roll_Izquierda = 0;

			float pitch_derecha = 0;
			float yaw_derecha = 0;
			float roll_derecha = 0;

			// 1 mano
			if (hands.count() == 1) {
				if (hand0.isLeft()) {
					Hand manoIzquierda = hand0;
					pitch_Izquierda = manoIzquierda.direction().pitch();
					yaw_Izquierda = manoIzquierda.direction().yaw();
					roll_Izquierda = manoIzquierda.palmNormal().roll();
				} else {
					Hand manoderecha = hand0;
					pitch_derecha = manoderecha.direction().pitch();
					yaw_derecha = manoderecha.direction().yaw();
					roll_derecha = manoderecha.palmNormal().roll();
				}
			}
			// 2 manos
			if (hands.count() == 2) {

				if (hand0.isLeft()) {
					Hand manoIzquierda = hand0;

					pitch_Izquierda = manoIzquierda.direction().pitch();
					yaw_Izquierda = manoIzquierda.direction().yaw();
					roll_Izquierda = manoIzquierda.palmNormal().roll();
				} else {

					Hand manoderecha = hand0;
					pitch_derecha = manoderecha.direction().pitch();
					yaw_derecha = manoderecha.direction().yaw();
					roll_derecha = manoderecha.palmNormal().roll();

				}
				if (hand1.isLeft()) {
					Hand manoIzquierda = hand1;

					pitch_Izquierda = manoIzquierda.direction().pitch();
					yaw_Izquierda = manoIzquierda.direction().yaw();
					roll_Izquierda = manoIzquierda.palmNormal().roll();
				} else {
					Hand manoderecha = hand1;
					pitch_derecha = manoderecha.direction().pitch();
					yaw_derecha = manoderecha.direction().yaw();
					roll_derecha = manoderecha.palmNormal().roll();
				}
			}



			System.out.println("--------------");
			System.out.println("pitch_Izquierda: " + pitch_Izquierda + " yaw_Izquierda: " + yaw_Izquierda
					+ " roll_Izquierda: " + roll_Izquierda);

			System.out.println("pitch_derecha: " + pitch_derecha + " yaw_derecha: " + yaw_derecha + " roll_derecha: "
					+ roll_derecha);

			

		}
		Pointable pointable = frame.pointables().frontmost();

		}

		public void onInit(Controller controller) {
			System.out.println("Iniciado");
		}
	
		public void onConnect(Controller controller) {
			System.out.println("Connected");
		}
	
		public void onDisconnect(Controller controller) {
			System.out.println("Desconectado");

	}
}
