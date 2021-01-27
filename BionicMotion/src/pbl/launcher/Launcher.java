package pbl.launcher;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import models.UserHandler;
import pbl.Session;
import pbl.display.MainFrame;

@SuppressWarnings("serial")
public class Launcher extends JFrame {

	private Authenticator authenticator;
	private LauncherController launcherController;
	private JTextField username;
	private JPasswordField password;
	private JLabel errorMsg;
	private int width, height;

	public Launcher() {
		super("Login");
		authenticator = new Authenticator();
		launcherController = new LauncherController(this, authenticator);
		Toolkit toolKit = Toolkit.getDefaultToolkit();
		width = (int) toolKit.getScreenSize().getWidth();
		height = (int) toolKit.getScreenSize().getHeight();
		/*
		 * Linux-en pantaila bat baino gehigo erabiltzean dena pantaila zabal bat bezela
		 * hartzen da, beraz zabalera handiegia da. Pantaila konkretu batentzako egina
		 * dago, beraz erroreak sortu ditzazke
		 * 
		 * TODO: Errebisatu pantaila ezberdinetan daukan eragina erabili baino lehen
		 */
		if (width > 1920) width = 1920;
		
		/* UserHandler klaseak erbiltzaileen fitxategia aurki dezan path ezarri behar da */
		UserHandler.setPath("res/files/users.txt");
		
		this.setContentPane(createMainContentPanel());
		this.setSize(new Dimension((int) (width / 3), (int) (height / 2.5)));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
		this.setVisible(true);
	}

	private Container createMainContentPanel() {
		JPanel panel = new JPanel(new GridBagLayout());

		/* Leihoko elementuak sortu eta panelerea gehitu */

		createCloseBar(panel);
		createBanner(panel); // Argazki nagusia
		createUserField(panel); // Erabiltzailea
		createPasswordField(panel); // Pasahitza
		createButton(panel); // Login botoia
		createErrorMessage(panel);

		return panel;
	}

	private void createCloseBar(JPanel panel) {
		
		/* Panel bat sortu bertan txikitzeko eta izteko botoiak sartzeko */
		
		JPanel header = new JPanel(new GridBagLayout());
		
		/* Panel horretan joango diren bi botoiak sortu */
		
		JButton button = new JButton(new ImageIcon("res/icons/minimize.png"));
		button.setActionCommand("minimize");
		button.addActionListener(launcherController);
		button.setBackground(Color.WHITE);
		button.setBorder(BorderFactory.createEmptyBorder(5,5,5,7));
		button.setOpaque(false);
		button.setFocusPainted(false);
		header.add(button);

		button = new JButton(new ImageIcon("res/icons/close.png"));
		button.setActionCommand("close");
		button.addActionListener(launcherController);
		button.setBackground(Color.WHITE);
		button.setBorder(BorderFactory.createEmptyBorder(5,7,5,5));
		button.setOpaque(false);
		button.setFocusPainted(false);
		header.add(button);
		
		/* Constraintak sortu */
		
		GridBagConstraints headerConst = new GridBagConstraints();
		headerConst.insets = new Insets(0, 0, 0, 0);	//Marginik gabe, barruko elementuek dauzkate margin propioak
		headerConst.gridwidth = 2;	//Panelaren luzera guztia hartzen du
		headerConst.gridx = 0;
		headerConst.gridy = 0;
		headerConst.anchor = GridBagConstraints.NORTHEAST;	//Goiko eskuin eskinean kokatuta
		panel.add(header, headerConst); // Panelera nagusira gehitu
	}

	private void createBanner(JPanel panel) {
		/* Leihoko argazia */
		
		JLabel logo = new JLabel(new ImageIcon("res/img/Logo-vista.png"));
		GridBagConstraints logoConst = new GridBagConstraints();
		logoConst.gridwidth = 2;
		logoConst.insets = new Insets(0, 0, 20, 0);
		logoConst.weightx = 1;
		logoConst.weighty = 1;
		logoConst.gridx = 0;
		logoConst.gridy = 1;
		panel.add(logo, logoConst);
	}

	private void createPasswordField(JPanel panel) {
		/* Pasahitzaren eremuak sortu */
		
		JLabel label = new JLabel("Password: ");
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints labelConst = new GridBagConstraints();
		labelConst.insets = new Insets(0, width/10, 5, 0);
		labelConst.anchor = GridBagConstraints.EAST;
		labelConst.gridx = 0;
		labelConst.gridy = 3;
		panel.add(label, labelConst);

		password = new JPasswordField();
		GridBagConstraints passwordConst = new GridBagConstraints();
		passwordConst.fill = GridBagConstraints.HORIZONTAL;
		passwordConst.insets = new Insets(0, 0, 5, width/10);
		passwordConst.gridx = 1;
		passwordConst.gridy = 3;
		panel.add(password, passwordConst);
		
		password.setColumns(10);

	}

	private void createUserField(JPanel panel) {
		/* Erabiltzailearen eremua sortu */
		
		JLabel label = new JLabel("User: ");
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints labelConst = new GridBagConstraints();
		labelConst.insets = new Insets(0, width/10, 5, 0);
		labelConst.anchor = GridBagConstraints.EAST;
		labelConst.gridx = 0;
		labelConst.gridy = 2;
		panel.add(label, labelConst);

		username = new JTextField();
		GridBagConstraints usernameConst = new GridBagConstraints();
		usernameConst.fill = GridBagConstraints.HORIZONTAL;
		usernameConst.insets = new Insets(0, 0, 5, width/10);
		usernameConst.gridx = 1;
		usernameConst.gridy = 2;
		panel.add(username, usernameConst);
		username.setColumns(10);
	}
	
	private void createButton(JPanel panel) {

		/* Login botoia sortu, itzura eman eta kontroladorea gehitu */

		JButton button = new JButton("Login");
		button.setActionCommand("login");
		button.addActionListener(launcherController);
		button.setFont(new Font("Tahoma", Font.BOLD, 13));
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(36, 123, 160));

		/* Botoiarentzat constraint-ak sortu eta goi margina ezarri */

		GridBagConstraints buttonConst = new GridBagConstraints();
		buttonConst.insets = new Insets(20, 0, 10, 0);
		buttonConst.gridwidth = 2;
		buttonConst.gridx = 0;
		buttonConst.gridy = 4;

		panel.add(button, buttonConst); // Panelera nagusira gehitu
	}
	
	private void createErrorMessage(JPanel panel) {
		/* Label bat sortu bertan login oker bat gertau dela adierazteko, hasieran ez da ikusten */
		
		errorMsg = new JLabel("Wrong credentials, try again or contact the administrator");
		errorMsg.setFont(new Font("Tahoma", Font.BOLD, 13));
		errorMsg.setForeground(errorMsg.getBackground());	//Letrak atzeko kolorekoak jartzen dira ez ikusteko
		
		GridBagConstraints labelConst = new GridBagConstraints();
		labelConst.insets = new Insets(10, 0, height/20, 0);
		labelConst.gridwidth = 2;
		labelConst.gridx = 0;
		labelConst.gridy = 5;
		
		panel.add(errorMsg, labelConst);
	}
	
	/* Leihoa txikitxeko */
	public void minimize() {
		this.setState(JFrame.ICONIFIED);
	}
	
	/* Leihoa ixteko */
	public void close() {
		this.dispose();
	}
	
	/* Login errorea erakusteko */
	public void showErrorMsg() {
		errorMsg.setForeground(Color.RED);	//Behin login oker bat eginda letrak gorriz jartzen dira ikusi daitezen
	}

	public String getUsername() {
		return username.getText();
	}

	public String getPassword() {
		String pass = new String(password.getPassword());
		return pass;
	}
	
	public static void main(String[] args) {
		String[] data = {"user", "dddd", "T"};
		MainFrame m = new MainFrame(new Session(data));
//		Launcher l = new Launcher();
    }
}
