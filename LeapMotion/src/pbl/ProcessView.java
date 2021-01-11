package pbl;

import java.awt.Dimension;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.ImageIcon;

public class ProcessView extends JPanel{

	ViewController controller;
	
	public ProcessView(ViewController controller) {
		setBorder(new EmptyBorder(30, 30, 30, 30));
		this.controller = controller;
		setPreferredSize(new Dimension(1024, 600));
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWidths = new int[]{0, 532, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, 0.0};
		setLayout(gridBagLayout);
		
		JLabel logo = new JLabel(new ImageIcon("img/Logo-vista.png"));
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
		
		JLabel lblProductName = new JLabel("Anestesia local");
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
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 10);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 2;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblProcessCount = new JLabel("0 out of 15");
		lblProcessCount.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblProcessCount = new GridBagConstraints();
		gbc_lblProcessCount.anchor = GridBagConstraints.WEST;
		gbc_lblProcessCount.insets = new Insets(0, 0, 5, 0);
		gbc_lblProcessCount.gridx = 3;
		gbc_lblProcessCount.gridy = 2;
		add(lblProcessCount, gbc_lblProcessCount);
		
		JLabel lblTimeSpent = new JLabel("Time spent:");
		lblTimeSpent.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_lblTimeSpent = new GridBagConstraints();
		gbc_lblTimeSpent.anchor = GridBagConstraints.EAST;
		gbc_lblTimeSpent.insets = new Insets(0, 0, 20, 10);
		gbc_lblTimeSpent.gridx = 2;
		gbc_lblTimeSpent.gridy = 3;
		add(lblTimeSpent, gbc_lblTimeSpent);
		
		JLabel lblTimeCount = new JLabel("00:35 min");
		lblTimeCount.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblTimeCount = new GridBagConstraints();
		gbc_lblTimeCount.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblTimeCount.insets = new Insets(0, 0, 5, 0);
		gbc_lblTimeCount.gridx = 3;
		gbc_lblTimeCount.gridy = 3;
		add(lblTimeCount, gbc_lblTimeCount);
		
		JList<String> list = new JList<>();
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 20, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 1;
		gbc_list.gridy = 4;
		add(list, gbc_list);
		
		JPanel buttonPanel = new JPanel();
		GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
		gbc_buttonPanel.gridwidth = 2;
		gbc_buttonPanel.fill = GridBagConstraints.BOTH;
		gbc_buttonPanel.anchor = GridBagConstraints.EAST;
		gbc_buttonPanel.insets = new Insets(0, 30, 30, 30);
		gbc_buttonPanel.gridx = 2;
		gbc_buttonPanel.gridy = 4;
		add(buttonPanel, gbc_buttonPanel);
		
		GridBagLayout gbl_buttonPanel = new GridBagLayout();
		gbl_buttonPanel.columnWeights = new double[]{1.0, 0.0};
		gbl_buttonPanel.rowWeights = new double[]{0.0, 0.0};
		buttonPanel.setLayout(gbl_buttonPanel);
		
		JButton btnStart = new JButton("Start");
		GridBagConstraints gbc_btnStart = new GridBagConstraints();
		gbc_btnStart.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnStart.anchor = GridBagConstraints.NORTH;
		gbc_btnStart.insets = new Insets(0, 0, 5, 5);
		gbc_btnStart.gridx = 0;
		gbc_btnStart.gridy = 0;
		buttonPanel.add(btnStart, gbc_btnStart);
		
		JButton btnCancel = new JButton("Cancel");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancel.anchor = GridBagConstraints.NORTH;
		gbc_btnCancel.insets = new Insets(0, 0, 5, 0);
		gbc_btnCancel.gridx = 1;
		gbc_btnCancel.gridy = 0;
		buttonPanel.add(btnCancel, gbc_btnCancel);
		
		JButton btnNextProcess = new JButton("Next process");
		GridBagConstraints gbc_btnNextProcess = new GridBagConstraints();
		gbc_btnNextProcess.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNextProcess.anchor = GridBagConstraints.NORTH;
		gbc_btnNextProcess.gridwidth = 2;
		gbc_btnNextProcess.insets = new Insets(0, 0, 0, 5);
		gbc_btnNextProcess.gridx = 0;
		gbc_btnNextProcess.gridy = 1;
		buttonPanel.add(btnNextProcess, gbc_btnNextProcess);
		
		JLabel lblNewLabel_2 = new JLabel("Progress: ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 5;
		add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setForeground(new Color(36, 123, 160));
		progressBar.setIndeterminate(true);
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.gridwidth = 2;
		gbc_progressBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar.insets = new Insets(0, 25, 0, 25);
		gbc_progressBar.gridx = 1;
		gbc_progressBar.gridy = 5;
		add(progressBar, gbc_progressBar);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBackground(new Color(36, 123, 160));
		btnBack.setForeground(Color.WHITE);
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.gridx = 3;
		gbc_btnBack.gridy = 5;
		add(btnBack, gbc_btnBack);
		
	}
}
