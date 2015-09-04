
package exceldemo;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.RectangleInsets;

/*
 * @author Mingchen Jia
 * @version 1.0
 * @since JDK 7.2
 * @since 1/19/2014
 */

public class Adjusted_Closing extends JFrame{
 
  private static final long serialVersionUID = 1L;
  public String title;
  public static Boolean displayed = false;
  public TimeSeriesCollection dataset = new TimeSeriesCollection();
  TimeSeries Adjusted_Closing = new TimeSeries("Adjusted Closing",Day.class);
  Date_info[] date;
  JFreeChart chart;
  
  public Adjusted_Closing(String Title, String chartTitle,String x_axis, String y_axis, Date_info[] date_info) {
        super(Title);   
        this.title = Title;
        this.date = date_info;
        chart = createChart(CreateDataset(), chartTitle,x_axis,y_axis );

        ChartPanel chartPanel = new ChartPanel(chart);

        chartPanel.setPreferredSize(new java.awt.Dimension(400, 400));
  
        setContentPane(chartPanel);
        
        addWindowListener(new Adjusted_Closing.closing());
    }
  
     public  class closing extends WindowAdapter{
         
                @Override
                public void windowClosing(WindowEvent e) {
                    dispose();
                }
        }
  private XYDataset CreateDataset(){
       int a =0;
      
       while (date[a]!= null){  //Adds data to dataset Adjusted_Closing until data runs out
            Adjusted_Closing.add(new Day(date[a].day, date[a].month, date[a].year), date[a].stk_price_adj_close);
            a++;
       }
       dataset.addSeries(Adjusted_Closing);

       return dataset;
  }
  
  private JFreeChart createChart(final XYDataset dataset, String chart_title, String x_axis, String y_axis) {
        
        // create the chart...
        final JFreeChart chart = ChartFactory.createTimeSeriesChart(
             "Adjusted Closing over time of "+title,  // title
            x_axis,             // x-axis label
            y_axis,   // y-axis label
            dataset,            // data
            true,               // create legend
            true,               // generate tooltips
            false               // no generate URLS
        );

       

       //Below code sets interface of line chart
        chart.setBackgroundPaint(Color.white);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(1.0, 1.0, 1.0, 1.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
 
        XYItemRenderer r = plot.getRenderer();
        plot.getRenderer().setSeriesPaint(0, Color.green);
        
        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("dd-MMM-yyyy"));    //Sets format of date axis
        
        return chart;    
        
        
    } 
}   
    
    
    