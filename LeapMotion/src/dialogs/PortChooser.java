package dialogs;

import java.awt.Container;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import jssc.SerialPortList;

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
		
		JPanel panel = new JPanel();
		String[] ports = SerialPortList.getPortNames();
		
		JComboBox<String> aukerak = new JComboBox<>(ports); 
		
		panel.add(aukerak);
		
		return panel;
	}
	
	
}
