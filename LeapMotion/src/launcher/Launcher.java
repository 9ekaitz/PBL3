package launcher;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Launcher extends JFrame {

	LauncherController launcherController;
	JTextField username;
	JPasswordField password;
	int width, height;

	public Launcher() {
		super("Login");
		launcherController = new LauncherController(this);
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

		return panel;
	}

	private void createCloseBar(JPanel panel) {
		
		/* Panel bat sortu bertan txikitzeko eta izteko botoiak sartzeko */
		
		JPanel header = new JPanel(new GridBagLayout());
		
		JButton button = new JButton(new ImageIcon("res/minimize.png"));
		button.setActionCommand("minimize");
		button.addActionListener(launcherController);
		button.setBackground(Color.WHITE);
		button.setBorder(BorderFactory.createEmptyBorder(5,5,5,7));
		button.setOpaque(false);
		button.setFocusPainted(false);
		header.add(button);

		button = new JButton(new ImageIcon("res/close.png"));
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
		buttonConst.insets = new Insets(20, 0, height/10, 0);
		buttonConst.gridwidth = 2;
		buttonConst.gridx = 0;
		buttonConst.gridy = 4;

		panel.add(button, buttonConst); // Panelera nagusira gehitu
	}

	private void createBanner(JPanel panel) {
		JLabel logo = new JLabel(new ImageIcon("res/Logo-vista.png"));
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
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.insets = new Insets(0, width/10, 5, 0);
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 3;
		panel.add(label, gbc_lblPassword);

		password = new JPasswordField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.insets = new Insets(0, 0, 5, width/10);
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 3;
		panel.add(password, gbc_textField_1);
		
		password.setColumns(10);

	}

	private void createUserField(JPanel panel) {
		/* Erabiltzailearen eremua sortu */
		
		JLabel label = new JLabel("User: ");
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblUser = new GridBagConstraints();
		gbc_lblUser.insets = new Insets(0, width/10, 5, 0);
		gbc_lblUser.anchor = GridBagConstraints.EAST;
		gbc_lblUser.gridx = 0;
		gbc_lblUser.gridy = 2;
		panel.add(label, gbc_lblUser);

		username = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, width/10);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 2;
		panel.add(username, gbc_textField);
		username.setColumns(10);

	}
	
	public void minimize() {
		this.setState(JFrame.ICONIFIED);
	}
	
	public void close() {
		this.dispose();
	}
}
