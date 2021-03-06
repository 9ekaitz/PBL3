package pbl.display;

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

import pbl.controllers.ViewController;
import pbl.dialogs.SettingsDialog;
import pbl.launcher.Launcher;
import pbl.models.MaterialModel;
import pbl.models.ProductModel;
import pbl.models.Session;

@SuppressWarnings("serial")
public class MainFrame extends JFrame{

	Session session;
	JPanel actualPanel;
	ViewController controller;
	MenuActions logout, exit, settings;
	MaterialModel materialModel;
	ProductModel productModel;

	public MainFrame(Session session) {
		super("Leap Motion");
		this.session = session;
		
		this.materialModel = new MaterialModel();
		this.productModel = new ProductModel();
		this.controller = new ViewController(this, materialModel, productModel);
		this.actualPanel = new AppMenu(controller, materialModel, productModel);
		
		createActions();
		this.setJMenuBar(createMenuBar());
		this.setContentPane(actualPanel);
		
		this.setSize(1024,600);	//Toolkit-en ordez aldatu behar da, edozein pantailatan funtzionatzeko
		this.setLocationRelativeTo(null);	//Ordenagialuaren erdian agertzeko
		this.setIconImage(new ImageIcon("res/img/Logo-icon.png").getImage());
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	/* Akzioak sortzeko */
    private void createActions() {	
		logout = new MenuActions("Logout", new ImageIcon("res/icons/logout.png"), "Logout from the app");
		settings = new MenuActions("Settings", new ImageIcon("res/icons/settings.png"), "Choose the serial port");
		exit = new MenuActions("Exit app", new ImageIcon("res/icons/exitapp.png"), "Exit the app");
	}
    
    /* MenuBar sorzteko */
    private JMenuBar createMenuBar() {
    	JMenuBar bar = new JMenuBar();
		bar.add(Box.createHorizontalGlue());
		bar.add(createExitMenu());
		return bar;
	}

    /* Exit menu sortzeko */
	private JMenu createExitMenu() {
		JMenu menu = new JMenu(session.getName());
		menu.add(logout);
		menu.add(settings);
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
	
	public JPanel getPanel() {
		return actualPanel;
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
			case "Logout":// Login pantailara itzultzeko
				Launcher l = new Launcher(); 
				MainFrame.this.dispose();	
				break;
			case "Exit app": // Programa amaitzeko
				System.exit(0);
				break;
			case "Settings":
				SettingsDialog d = new SettingsDialog(MainFrame.this, "Settings", true, session, controller.getPortManager());
				controller.setPortManager(d.getPortManager()); // Portuen administratzailea kontroladorean gehitu
				break;
			default:
				break;
			}
		}		
	}

	public void close() {
		this.dispose();
	}
}
