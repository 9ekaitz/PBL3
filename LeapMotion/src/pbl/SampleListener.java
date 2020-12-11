package pbl;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.Listener;

public class SampleListener extends Listener{


    public void onFrame(Controller controller) {
         Frame frame = controller.frame();

            System.out.println("Frame id: " + frame.id()
                           + ", timestamp: " + frame.timestamp()
                           + ", hands: " + frame.hands().count()
                           + ", fingers: " + frame.fingers().count());
            for (Hand hand:frame.hands()) {
                String handType =hand.isLeft() ? "Izquieda":"derecha";

                System.out.println(handType+"posicion de la mano:"+(hand.palmPosition()));

            }
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
