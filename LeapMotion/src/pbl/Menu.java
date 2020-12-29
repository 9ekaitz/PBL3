package pbl;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Menu extends JFrame {

	Dimension dim;
	
	public Menu() {
		super("Leap Motion");
		this.setSize(1024,600);
		this.setWindowCentered();
		this.setIconImage(new ImageIcon("img/Logo-icon.png").getImage());
		this.setContentPane(createMainWindow());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private Container createMainWindow() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(createLeftButtons(), BorderLayout.WEST);
		panel.add(createGraph());
		return panel;
	}
	
	private Component createGraph() {
		JFreeChart chart = ChartFactory.createXYLineChart("Title", "Days", "Products", createDataset(), PlotOrientation.VERTICAL,
														  true, true, false);
		
		XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
//        renderer.setSeriesPaint(0, Color.RED);
//        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

//        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("Title", new Font("Serif", java.awt.Font.BOLD, 18)));
		
        ChartPanel panel = new ChartPanel(chart);
		panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
		return panel;
	}
	
	private XYDataset createDataset() {

        XYSeries series = new XYSeries("2016");
        series.add(18, 567);
        series.add(20, 612);
        series.add(25, 800);
        series.add(30, 980);
        series.add(40, 1410);
        series.add(50, 2350);

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        return dataset;
    }

	private Component createLeftButtons() {
		JPanel panel = new JPanel(new GridLayout(2,3,20,20));
		
		JButton addMaterialButton = new JButton("Add material");
		JButton deleteMaterialButton = new JButton("Delete material");
		JButton seeMaterialButton = new JButton("See materials");
		
		addMaterialButton.setActionCommand("addMaterial");
		deleteMaterialButton.setActionCommand("deleteMaterial");
		seeMaterialButton.setActionCommand("seeMaterial");
		
		addMaterialButton.setBackground(new Color(36, 123, 160));
		addMaterialButton.setForeground(Color.WHITE);
		deleteMaterialButton.setBackground(new Color(36, 123, 160));
		deleteMaterialButton.setForeground(Color.WHITE);
		seeMaterialButton.setBackground(new Color(36, 123, 160));
		seeMaterialButton.setForeground(Color.WHITE);
		
		panel.add(addMaterialButton);
		panel.add(deleteMaterialButton);
		panel.add(seeMaterialButton);
		
		return panel;
	}

	private void setWindowCentered() {
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
}
