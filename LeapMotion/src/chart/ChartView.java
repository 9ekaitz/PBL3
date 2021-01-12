package chart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.event.ChartProgressEvent;
import org.jfree.chart.event.ChartProgressListener;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.Range;
import org.jfree.data.time.Minute;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.TimeSeriesDataItem;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.DateCellRenderer;
import org.jfree.ui.NumberCellRenderer;
import org.jfree.ui.RectangleInsets;

public class ChartView extends JPanel implements ChangeListener, ChartProgressListener{
	
	TimeSeries series;   
    ChartPanel chartPanel;   
    ChartTableModel model;   
    JFreeChart chart;   
    JSlider slider;   

    private JFreeChart createChart()  {   
        XYDataset xydataset = createDataset("Production", 100D, new Minute(), 200);   
        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("Production", "Time of Day", "Products", xydataset, true, true, false);   
        jfreechart.setBackgroundPaint(Color.white);   
        XYPlot xyplot = jfreechart.getXYPlot();   
        xyplot.setOrientation(PlotOrientation.VERTICAL);   
        xyplot.setBackgroundPaint(Color.lightGray);   
        xyplot.setDomainGridlinePaint(Color.white);   
        xyplot.setRangeGridlinePaint(Color.white);   
        xyplot.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D));   
        xyplot.setDomainCrosshairVisible(true);   
        xyplot.setDomainCrosshairLockedOnData(false);   
        xyplot.setRangeCrosshairVisible(false);   
        XYItemRenderer xyitemrenderer = xyplot.getRenderer();   
        xyitemrenderer.setPaint(new Color(36, 123, 160));
        
        return jfreechart;   
    }   

    private XYDataset createDataset(String s, double d, RegularTimePeriod regulartimeperiod, int i) {   
        series = new TimeSeries(s, regulartimeperiod.getClass());   
        RegularTimePeriod regulartimeperiod1 = regulartimeperiod;   
        double d1 = d;   
        for(int j = 0; j < i; j++)   
        {   
            series.add(regulartimeperiod1, d1);   
            regulartimeperiod1 = regulartimeperiod1.next();   
            d1 *= 1.0D + (Math.random() - 0.495D) / 10D;   
        }   

        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();   
        timeseriescollection.addSeries(series);   
        return timeseriescollection;   
    }   

    public void stateChanged(ChangeEvent changeevent)   
    {   
        int i = slider.getValue();   
        XYPlot xyplot = chart.getXYPlot();   
        ValueAxis valueaxis = xyplot.getDomainAxis();   
        Range range = valueaxis.getRange();   
        double d = valueaxis.getLowerBound() + ((double)i / 100D) * range.getLength();   
        xyplot.setDomainCrosshairValue(d);   
    }   

    public void chartProgress(ChartProgressEvent chartprogressevent)   
    {   
        if(chartprogressevent.getType() != 2)   
            return;   
        if(chartPanel != null)   
        {   
            JFreeChart jfreechart = chartPanel.getChart();   
            if(jfreechart != null)   
            {   
                XYPlot xyplot = jfreechart.getXYPlot();   
                XYDataset xydataset = xyplot.getDataset();   
                Comparable comparable = xydataset.getSeriesKey(0);   
                double d = xyplot.getDomainCrosshairValue();   
                model.setValueAt(comparable, 0, 0);   
                long l = (long)d;   
                model.setValueAt(new Long(l), 0, 1);   
                int i = series.getIndex(new Minute(new Date(l)));   
                if(i >= 0)   
                {   
                    TimeSeriesDataItem timeseriesdataitem = series.getDataItem(Math.min(199, Math.max(0, i)));   
                    TimeSeriesDataItem timeseriesdataitem1 = series.getDataItem(Math.max(0, i - 1));   
                    TimeSeriesDataItem timeseriesdataitem2 = series.getDataItem(Math.min(199, i + 1));   
                    long l1 = timeseriesdataitem.getPeriod().getMiddleMillisecond();   
                    double d1 = timeseriesdataitem.getValue().doubleValue();   
                    long l2 = timeseriesdataitem1.getPeriod().getMiddleMillisecond();   
                    double d2 = timeseriesdataitem1.getValue().doubleValue();   
                    long l3 = timeseriesdataitem2.getPeriod().getMiddleMillisecond();   
                    double d3 = timeseriesdataitem2.getValue().doubleValue();   
                    model.setValueAt(new Long(l1), 0, 1);   
                    model.setValueAt(new Double(d1), 0, 2);   
                    model.setValueAt(new Long(l2), 0, 3);   
                    model.setValueAt(new Double(d2), 0, 4);   
                    model.setValueAt(new Long(l3), 0, 5);   
                    model.setValueAt(new Double(d3), 0, 6);   
                }   
            }   
        }   
    }   

    public ChartView()   
    {   
        super(new BorderLayout());   
        chart = createChart();   
        chart.addProgressListener(this);   
        chartPanel = new ChartPanel(chart);   
        chartPanel.setPreferredSize(new Dimension(600, 270));   
        chartPanel.setDomainZoomable(true);   
        chartPanel.setRangeZoomable(true);   
        javax.swing.border.CompoundBorder compoundborder = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4), BorderFactory.createEtchedBorder());   
        chartPanel.setBorder(compoundborder);   
        add(chartPanel);   
        JPanel jpanel = new JPanel(new BorderLayout());   
        jpanel.setPreferredSize(new Dimension(400, 60));   
        jpanel.setBorder(BorderFactory.createEmptyBorder(0, 4, 4, 4));   
        model = new ChartTableModel(3);   
        model.setValueAt(chart.getXYPlot().getDataset().getSeriesKey(0), 0, 0);   
        model.setValueAt(new Double("0.00"), 0, 1);   
        model.setValueAt(new Double("0.00"), 0, 2);   
        model.setValueAt(new Double("0.00"), 0, 3);   
        model.setValueAt(new Double("0.00"), 0, 4);   
        model.setValueAt(new Double("0.00"), 0, 5);   
        model.setValueAt(new Double("0.00"), 0, 6);   
        JTable jtable = new JTable(model);   
        DateCellRenderer datecellrenderer = new DateCellRenderer(new SimpleDateFormat("HH:mm"));   
        NumberCellRenderer numbercellrenderer = new NumberCellRenderer();   
        jtable.getColumnModel().getColumn(1).setCellRenderer(datecellrenderer);   
        jtable.getColumnModel().getColumn(2).setCellRenderer(numbercellrenderer);   
        jtable.getColumnModel().getColumn(3).setCellRenderer(datecellrenderer);   
        jtable.getColumnModel().getColumn(4).setCellRenderer(numbercellrenderer);   
        jtable.getColumnModel().getColumn(5).setCellRenderer(datecellrenderer);   
        jtable.getColumnModel().getColumn(6).setCellRenderer(numbercellrenderer);   
        jpanel.add(new JScrollPane(jtable));   
        slider = new JSlider(0, 100, 50);   
        slider.addChangeListener(this);   
        jpanel.add(slider, "South");   
        add(jpanel, "South");   
    }   
}
