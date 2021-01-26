package chart;

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
        JFreeChart jfreechart = ChartFactory.createPieChart3D("Material Types", dataset);
        jfreechart.getPlot().setBackgroundPaint(Color.white);  
        jfreechart.getPlot().setForegroundAlpha( 0.60f );
        
        return jfreechart;   
    }   

    private PieDataset createDataset( ) {
        dataset.setValue( "" , new Double( 1 ) );  
        return dataset;         
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
        javax.swing.border.CompoundBorder compoundborder = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4), BorderFactory.createEtchedBorder());   
        chartPanel.setBorder(compoundborder);   
        add(chartPanel);   
    }

}