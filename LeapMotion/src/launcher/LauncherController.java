package launcher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pbl.MainViewFrame;
import pbl.Session;

public class LauncherController implements ActionListener {

	Authenticator authModel;
	Launcher view;
	
	public LauncherController(Launcher view, Authenticator authenticator) {
		this.view = view;
		this.authModel = authenticator;
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
			Session session = authModel.authenticate(view.getUsername(), view.getPassword());
			if (session != null) {
				MainViewFrame app = new MainViewFrame(session);
				view.close();
			} else {
				view.showErrorMsg();
			}
			break;
		default:
			break;
		}
	}

}
