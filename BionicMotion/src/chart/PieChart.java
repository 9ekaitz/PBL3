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

    private JFreeChart createChart()  {
        JFreeChart jfreechart = ChartFactory.createPieChart3D("Test", createDataset());
        jfreechart.setBackgroundPaint(Color.white);   
        
        return jfreechart;   
    }   

    private static PieDataset createDataset( ) {
        DefaultPieDataset dataset = new DefaultPieDataset( );
        dataset.setValue( "IPhone 5s" , new Double( 20 ) );  
        dataset.setValue( "SamSung Grand" , new Double( 20 ) );   
        dataset.setValue( "MotoG" , new Double( 40 ) );    
        dataset.setValue( "Nokia Lumia" , new Double( 10 ) );  
        return dataset;         
     }
    
    public PieChart()   
    {   
        super(new BorderLayout());   
        chart = createChart();   
        chartPanel = new ChartPanel(chart);   
        chartPanel.setDomainZoomable(true);   
        chartPanel.setRangeZoomable(true);   
        javax.swing.border.CompoundBorder compoundborder = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4), BorderFactory.createEtchedBorder());   
        chartPanel.setBorder(compoundborder);   
        add(chartPanel);   
    }

}