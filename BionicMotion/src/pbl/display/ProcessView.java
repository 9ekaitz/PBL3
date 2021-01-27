package pbl.display;

import java.awt.Dimension;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.EmptyBorder;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;

import pbl.controllers.ViewController;
import pbl.io.PortManager;
import pbl.io.Serial;
import pbl.leapmotion.SampleListener;
import pbl.models.Material;
import pbl.models.Product;

import javax.swing.JList;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class ProcessView extends JPanel{

	private ViewController viewController;
	private Product product;
	private ListRenderer renderer;
	private JList<Material> materialLst;
	private JProgressBar progressBar;
	private JButton startButton, nextButton;
	private JLabel labelProducts;
	private PortManager portManager;
	private SampleListener listener;
	private Controller controller;
	private Serial serial;
	private boolean motionCapture;
	
	
	public ProcessView(ViewController controller, Product product) {	
		this.product = product;
		this.renderer = new ListRenderer();
		this.viewController = controller;
		this.motionCapture = false;
		createPanel();			
	}

	private void createPanel() {
		setBorder(new EmptyBorder(30, 30, 30, 30));
		setPreferredSize(new Dimension(1024, 600));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWidths = new int[]{0, 532, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, 0.0};
		setLayout(gridBagLayout);
		
		JLabel logo = new JLabel(new ImageIcon("res/img/Logo-vista.png"));
		GridBagConstraints gbc_logo = new GridBagConstraints();
		gbc_logo.anchor = GridBagConstraints.WEST;
		gbc_logo.insets = new Insets(0, 0, 5, 5);
		gbc_logo.gridx = 1;
		gbc_logo.gridy = 1;
		add(logo, gbc_logo);
		
		JLabel lblNewLabel = new JLabel("Product name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 10);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 1;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblProductName = new JLabel(product.toString());
		lblProductName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblProductName = new GridBagConstraints();
		gbc_lblProductName.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblProductName.insets = new Insets(0, 0, 5, 0);
		gbc_lblProductName.gridx = 3;
		gbc_lblProductName.gridy = 1;
		add(lblProductName, gbc_lblProductName);
		
		JLabel lblNewLabel_1 = new JLabel("Processes:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 15, 10);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 2;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		labelProducts = new JLabel(0+" out of "+product.getMaterials().size());
		labelProducts.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblProcessCount = new GridBagConstraints();
		gbc_lblProcessCount.anchor = GridBagConstraints.WEST;
		gbc_lblProcessCount.insets = new Insets(0, 0, 15, 0);
		gbc_lblProcessCount.gridx = 3;
		gbc_lblProcessCount.gridy = 2;
		add(labelProducts, gbc_lblProcessCount);
		
		JScrollPane listPanel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		createProcessList();
		listPanel.setViewportView(materialLst);
		
		GridBagConstraints gbc_listPanel = new GridBagConstraints();
		gbc_listPanel.insets = new Insets(0, 0, 20, 5);
		gbc_listPanel.fill = GridBagConstraints.BOTH;
		gbc_listPanel.gridx = 1;
		gbc_listPanel.gridy = 4;
		add(listPanel, gbc_listPanel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(3, 1, 0, 20));
		GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
		gbc_buttonPanel.gridwidth = 2;
		gbc_buttonPanel.fill = GridBagConstraints.BOTH;
		gbc_buttonPanel.anchor = GridBagConstraints.EAST;
		gbc_buttonPanel.insets = new Insets(0, 30, 30, 30);
		gbc_buttonPanel.gridx = 2;
		gbc_buttonPanel.gridy = 4;
		add(buttonPanel, gbc_buttonPanel);
		
		/*Prozesua hasteko botoia*/
		startButton = new JButton("Start");
		startButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		startButton.setForeground(Color.WHITE);
		startButton.setBackground(new Color(36, 123, 160));
		startButton.setActionCommand("start");
		startButton.addActionListener(viewController);
		buttonPanel.add(startButton);
		
		/*Hurrengo materialera pasatzeko botoia*/
		nextButton = new JButton("Next");
		nextButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nextButton.setForeground(Color.WHITE);
		nextButton.setBackground(new Color(36, 123, 160));
		nextButton.setEnabled(false);
		nextButton.setActionCommand("next");
		nextButton.addActionListener(viewController);
		buttonPanel.add(nextButton);
		
		/*Prozesua geldiarazteko botoia*/
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(new Color(36, 123, 160));
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancel.setActionCommand("cancel");
		btnCancel.addActionListener(viewController);
		buttonPanel.add(btnCancel);
		
		JLabel lblNewLabel_2 = new JLabel("Progress: ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 5;
		add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		
		/* ProgressBar */
		progressBar = new JProgressBar();
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		progressBar.setForeground(new Color(36, 123, 160));
		progressBar.setIndeterminate(false);
		
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.gridwidth = 2;
		gbc_progressBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar.insets = new Insets(0, 25, 0, 25);
		gbc_progressBar.gridx = 1;
		gbc_progressBar.gridy = 5;
		add(progressBar, gbc_progressBar);		
	}


	private void createProcessList() { // Produktuen zerrenda sortzeko
		materialLst = new JList<>();
		materialLst.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		materialLst.addListSelectionListener(viewController);
		materialLst.setModel(product);
		materialLst.setCellRenderer(renderer);
	}
	
	public JList<Material> getMaterialLst(){
		return materialLst;
	}
	
	public JProgressBar getBar() {
		return progressBar;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void startProcess() {
		startButton.setEnabled(false);
		nextButton.setEnabled(true);
		listener = new SampleListener();
		controller = new Controller();
		
		Frame frame = controller.frame(); // LeapMotion sentsoreko fotogramak jasotzen ditu
		
		controller.addListener(listener);
		this.portManager = viewController.getPortManager();
		serial = new Serial(portManager.getPort(), listener); // Serial puertotik bidalketak egiteko thread bat sortu
		serial.start(); // Sortu dugun thread-a martxan jarrtzeko
		motionCapture = true;
	}
	
	public void finishProgress() {
		progressBar.setValue(100); // Barraren azken balioa
		labelProducts.setText(product.getMaterials().size()+" out of "+product.getMaterials().size());// Material kopurua maximoan jartzeko 
		if (motionCapture) {
			controller.removeListener(listener); 
			controller=null;
			serial.finish(); // Serialaren thread-a gelditzeko
		}
	
		
	}
	
	public void updateProgress() {
		
		materialLst.setSelectedIndex(materialLst.getSelectedIndex()+1); //Zerrendako hurrengo elementua aukeratu
		float a = materialLst.getSelectedIndex();
		float b = materialLst.getModel().getSize();
		progressBar.setValue((int)((a/b)*100)); // Barraren balioa kalkulatzeko
		
		labelProducts.setText(materialLst.getSelectedIndex()+" out of "+product.getMaterials().size()); // Material kopurua eguneratu
	}
}
