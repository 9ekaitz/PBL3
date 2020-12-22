package pbl;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.leapmotion.leap.Bone;
import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.KeyTapGesture;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Pointable;
import com.leapmotion.leap.Vector;

public class SampleListener extends Listener {

	VentanaGrafica ventana;
	
	public SampleListener(JFrame frame) {
		ventana = new VentanaGrafica(frame);
	}

	public void onFrame(Controller controller) {
		Frame frame = controller.frame();

		// detectamos mano y entramos al bucle
		
		Hand leftHand = null, rightHand = null;
		
		for (Hand hand : frame.hands()) {	//Detektatutako eskuak sailkatu
			if (hand.isLeft()) leftHand = hand;
			else if (hand.isRight()) rightHand = hand;
			brazoExtendido(hand, frame);
		}
		
		
		
		
		
		
		

		
		
		if (leftHand != null) {
			System.out.println("Left hand\tPitch: "+leftHand.direction().pitch()
					+"  Yaw: "+Math.toDegrees(leftHand.direction().yaw())
					+"  Roll: "+Math.toDegrees(leftHand.direction().roll())
					+ "PalmPosition: "+ leftHand.palmPosition());
			
			for(Finger leftFinger: leftHand.fingers()) {
				System.out.println("--------------------------------");

				System.out.println("ID"+leftFinger.id()
									+ "Finger Type:" + leftFinger.type()
									+ "Finger Lenght:" + leftFinger.length()
									+ "Finger Width:" + leftFinger.width());
				
				Vector puntosInicio = null; 	
				Vector puntosFinal = null; 
				
				
				if(!leftFinger.isExtended()) {
					
					
					switch(leftFinger.type())
					{
					
						case TYPE_THUMB: 
							System.out.println("no: TYPE_PINKY");
							break;
						case TYPE_INDEX:
							System.out.println("no: TYPE_INDEX");

							break;
						case TYPE_MIDDLE: 
							System.out.println("no: TYPE_MIDDLE");

							for(Bone.Type boneType : Bone.Type.values()) {
								
								Bone bone = leftFinger.bone(boneType);
									if(boneType.TYPE_METACARPAL!=null) {
									System.out.println("1estoy dendro");
										puntosInicio=bone.prevJoint();
										
									} 
									if (boneType.TYPE_DISTAL!=null) {
										System.out.println("2estoy dendro");

										puntosFinal=bone.prevJoint();
									}
								
							}
							
					double multiplicacionDeVectores	=	(puntosInicio.getX()*puntosFinal.getX())+(puntosInicio.getY()*puntosFinal.getY())+(puntosInicio.getZ()*puntosFinal.getZ());
							
					double a =Math.sqrt(Math.pow(puntosInicio.getX(), 2)+Math.pow(puntosInicio.getY(), 2)+Math.pow(puntosInicio.getZ(), 2));
					
					double b =Math.sqrt(Math.pow(puntosFinal.getX(), 2)+Math.pow(puntosFinal.getY(), 2)+Math.pow(puntosFinal.getZ(), 2));
					
					
					double angulo = multiplicacionDeVectores/(a*b);
					
					angulo=Math.acos(angulo);
					
							System.out.println("estoy fuera: "+angulo);
							
							
							break;
						case TYPE_RING: 
							System.out.println("no: TYPE_RING");

							break;
						case TYPE_PINKY:
							

							break;
						
						
					}
					
//					for(Bone.Type boneType : Bone.Type.values()) {
//						
//						
//						Bone bone = leftFinger.bone(boneType);
//						System.out.println("Bone Type:" + bone.type()
//											+ "Start:" + bone.prevJoint()
//											+ "End:"+ bone.nextJoint()
//											+ "Direction: " + bone.direction());
//					
//						
//						
//						
//						
//						
//						/*if(finger.type().TYPE_THUMB != null) {
//							System.out.println("jajajaajajajajajajaaajajja");
//							fingerPrueba=finger;
//						}*/
//					}
				}	
			}
		}
		
		if (rightHand != null) {
//			System.out.println("Right hand\tPitch: "+rightHand.direction().pitch()
//					+"  Yaw: "+rightHand.direction().yaw()
//					+"  Roll: "+rightHand.direction().roll()
//					+ "PalmPosition: "+ rightHand.palmPosition());
//			
//			for(Finger rightFinger: rightHand.fingers()) {
//				System.out.println("--------------------------------");
//				System.out.println("Finger is finger: "+ rightFinger.isFinger());
//				System.out.println("ID"+rightFinger.id()
//									+ "Finger Type:" + rightFinger.type()
//									+ "Finger Lenght:" + rightFinger.length()
//									+ "Finger Width:" + rightFinger.width());
//				
//				System.out.println("Abierto: " + rightFinger.isExtended());
//				for(Bone.Type boneType : Bone.Type.values()) {
//					Bone bone = rightFinger.bone(boneType);
//					System.out.println("Bone Type:" + bone.type()
//										+ "Start:" + bone.prevJoint()
//										+ "End:"+ bone.nextJoint()
//										+ "Direction: " + bone.direction());
//					System.out.println("Distancia: " + bone.prevJoint().distanceTo(bone.nextJoint()));
//					/*if(finger.type().TYPE_THUMB != null) {
//						System.out.println("jajajaajajajajajajaaajajja");
//						fingerPrueba=finger;
//					}*/
//				}
//					
//			}
		}
		
		//cosa qu aiqu investigar
		for(Gesture gesture : frame.gestures()){
			  if(gesture.type() == KeyTapGesture.classType()){
			    KeyTapGesture keytap = new KeyTapGesture(gesture);
			    Pointable tappingPointable = keytap.pointable();
			    if(tappingPointable.isFinger()){
			      Finger tappingFinger = new Finger(tappingPointable);
			      System.out.println("Tapper: " + tappingFinger.type());
			    }
			  }
		}
		//Pointable pointable = frame.pointables().frontmost();
		
		//Peloten posizioa aldatu
		
		if (leftHand != null) {
			ventana.getBola1().setX((int) leftHand.palmPosition().getX());
			ventana.getBola1().setY((int) leftHand.palmPosition().getZ());
		}else if(rightHand != null) {
			ventana.getBola2().setX((int) rightHand.palmPosition().getX());
			ventana.getBola1().setY((int) rightHand.palmPosition().getZ());
		}
			
		//Eskurekin posizioarekin lehio bat mugitu
		ventana.canvas.repaint();
		
	//if(finger.type().equals(fingerPrueba)
	
	}

	public void brazoExtendido(Hand hand,Frame frame) {
		int kont = 0;
		if(hand.isRight()) {
			for(Finger finger : frame.fingers()) if(finger.isExtended()) kont++;
			
			if(kont == 5) System.out.println("Mano Derecha Extendida");
			
		}else if(hand.isLeft()) {
			for(Finger finger : frame.fingers()) if(finger.isExtended()) kont++;

			if(kont == 5) System.out.println("Mano Izquierda Extendida");
		}
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
