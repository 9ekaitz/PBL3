package launcher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LauncherController implements ActionListener {

	Launcher view;
	
	public LauncherController(Launcher view) {
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "close":
			view.close();
			break;
		case "minimize":
			view.minimize();
			break;
		case "login":
			
			break;
		default:
			break;
		}
	}

}
