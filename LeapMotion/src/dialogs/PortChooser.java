package dialogs;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PortChooser extends JDialog {

	JFrame frame;
	
	public PortChooser(JFrame frame, String title, boolean mode) {
		super(frame, title, mode);
		this.frame = frame;
		this.setSize(400,320);
		this.setLocation(352,150); ///////// TODO: PONER EN EL CENTRO DEL JPANEL
		this.setContentPane(createPanel());
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private Container createPanel() {
		
		JPanel panel = new JPanel(new GridBagLayout());
		createAccountSection(panel);
		
		return panel;
	}
	
	private void createAccountSection(JPanel panel) {
		/* Erabiltzaileen kontuekin erlazionatutako ekintzak */
		
		/* Kategoriaren goiburura */
		JLabel label = new JLabel("Account");
		label.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		GridBagConstraints labelKonst = new GridBagConstraints();
		labelKonst.insets = new Insets(10,10,10,10);
		labelKonst.gridx = 0;
		labelKonst.gridy = 0;
		labelKonst.weightx = 0.3;
		labelKonst.weighty = 0.3;
		labelKonst.anchor = GridBagConstraints.NORTHWEST;
		
		panel.add(label, labelKonst);


		JPanel subPanel = new JPanel();
		
		/* 1 Aukera -- kontu bat sortu, bakarrik administratzileentzako */
		JButton btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.setForeground(Color.WHITE);
		btnCreateAccount.setBackground(new Color(36, 123, 160));
		btnCreateAccount.setActionCommand("createAccount");
//		btnCreateAccount.addActionListener(controller);
		
		subPanel.add(btnCreateAccount);
		
		/* 2 Aukera -- sesio hasita daukan kontu ezabatu, dialogo bat erakutsi daiteke konfirmatzeko */
		JButton btnLogout = new JButton("Logout");	//Botoia sortu
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setBackground(new Color(36, 123, 160));
		btnLogout.setActionCommand("logout");
//		btnLogout.addActionListener(controller);
		
		subPanel.add(btnLogout);
		
		GridBagConstraints subPanelKonst = new GridBagConstraints();	//Botoiaren konstraintak sortu
		subPanelKonst.insets = new Insets(0, 0, 5, 0);
		subPanelKonst.gridx = 1;
		subPanelKonst.gridy = 1;
		subPanelKonst.weightx = 0.7;
		subPanelKonst.weighty = 0.7;
		subPanelKonst.anchor = GridBagConstraints.WEST;
//		subPanelKonst.weightx = 1;
		
		panel.add(subPanel, subPanelKonst);
	}
	
	
}
