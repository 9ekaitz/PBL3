package pbl.chart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

@SuppressWarnings("serial")
public class PieChart extends JPanel{
	
    ChartPanel chartPanel;   
    JFreeChart chart;  
    DefaultPieDataset dataset;

    private JFreeChart createChart()  {
        JFreeChart jfreechart = ChartFactory.createPieChart3D("Material Types", dataset,true,false,true);	//Izenburua, dataset, legenda, toolkit, autoroll
        jfreechart.getPlot().setBackgroundPaint(Color.white);  //Fondo zuria jartzeko
        jfreechart.getPlot().setForegroundAlpha( 0.60f );	//Transparente izateko
        
        return jfreechart;   
    }   

    public DefaultPieDataset getDataset() {
		return dataset;
	}
    
	public JFreeChart getChart() {
		return chart;
	}

	public PieChart()   
    {   
        super(new BorderLayout());
    	dataset = new DefaultPieDataset( );
        chart = createChart();   
        chartPanel = new ChartPanel(chart);   
        chartPanel.setDomainZoomable(true);   
        chartPanel.setRangeZoomable(true);   
        chartPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4), BorderFactory.createEtchedBorder()));   
        add(chartPanel);   
    }
}