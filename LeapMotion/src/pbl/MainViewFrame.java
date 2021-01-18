package pbl;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import launcher.Launcher;
import models.MaterialList;
import views.AppMenu;
import views.MaterialView;

public class MainViewFrame extends JFrame{

	Session session;
	JPanel actualPanel;
	ViewController controller;
	MenuActions logout, exit;

	public MainViewFrame(Session session) {
		super("Leap Motion");
		this.session = session;
		this.setSize(1024,600);	//Toolkit-en ordez aldatu behar da, edozein pantailatan funtzionatzeko
		this.setLocationRelativeTo(null);	//Ordenagialuaren erdian agertzeko
		this.setIconImage(new ImageIcon("img/Logo-icon.png").getImage());
		this.initializeVariables();
		this.setJMenuBar(createMenuBar());
		this.setContentPane(actualPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setUndecorated(true);
		this.setVisible(true);
	}

	private void initializeVariables() {
    	createActions();
    	controller = new ViewController(this);
		actualPanel = new AppMenu(controller, new MaterialList());
	}
    
    private void createActions() {	
		logout = new MenuActions("Logout", new ImageIcon("icons/logout.png"), "Logout from the app");
		exit = new MenuActions("Exit app", new ImageIcon("icons/exitapp.png"), "Exit the app");
	}
    
    private JMenuBar createMenuBar() {
    	JMenuBar bar = new JMenuBar();
		bar.add(Box.createHorizontalGlue());
		bar.add(createExitMenu());
		return bar;
	}

	private JMenu createExitMenu() {
		JMenu menu = new JMenu(session.getName());
		menu.add(logout);
		menu.add(exit);		
		return menu;
	}

	public void setActualPanel(Object actualPanel) {
		this.actualPanel = (JPanel) actualPanel;
		refresh();
	}
	
	private void refresh() {
		this.setContentPane(actualPanel);
		this.invalidate();
		this.validate();
		this.repaint();
	}
	
	public Object getActualObject() {
		return actualPanel;
	}
	
	public MaterialView getActualPanelMaterial() {
		return (MaterialView) actualPanel;
	}
	
	private class MenuActions extends AbstractAction{
		String command;
		public MenuActions(String command, Icon img, String desc) {
			super(command, img);
			this.command = command;
			this.putValue(Action.SHORT_DESCRIPTION, desc);
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			switch (command) {
			case "Logout":
				Launcher l = new Launcher();
				MainViewFrame.this.dispose();	
				return;
			case "Exit app":
				System.exit(0);
				return;
			default:
				break;
			}
		}		
	}

	public void close() {
		this.dispose();
	}
}
