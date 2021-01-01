package pbl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewController implements ActionListener{
	
	MainView view;
	//necesita modelo
	
	public ViewController(MainView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String command = arg0.getActionCommand();
		
		switch (command) {
		case "loginSuccess":
			view.setActualPanel(new AppMenu(this));
			break;
		case "manualManipulation":
			view.setActualPanel(new TestPanel(this));
		break;
		default:
			break;
		}
		
	}
}
