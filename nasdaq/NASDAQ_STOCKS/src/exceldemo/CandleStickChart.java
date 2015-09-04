package exceldemo;


import java.awt.Color;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.DefaultHighLowDataset;
import org.jfree.ui.RectangleInsets;

/*
 * @author Mingchen Jia
 * @version 1.0
 * @since JDK 7.2
 * @since 1/19/2014
 */

public class CandleStickChart extends JFrame {
  private static final long serialVersionUID = 1L;
  JFreeChart chart;
  
  public CandleStickChart(String title,Date_info[] info) {  
      //The following below is a constuctor for the chart that sets the lables,shape and colour of the chart
      
        super(title);
        
        chart = ChartFactory.createCandlestickChart("High-Low Chart", "Time", "Value", createDataset(info), false);
        chart.setBackgroundPaint(Color.white);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(1.0, 1.0, 1.0, 1.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);      
        
        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd")); //x-axis date format
        
        ChartPanel chartPanel = new ChartPanel(chart,400,400,400,400,400,400,true,true,false,false,false,true,true);
        JScrollPane pane = new JScrollPane();
        pane.setPreferredSize(new java.awt.Dimension(2500, 500));
        pane.getViewport().add(chartPanel);
        setContentPane(pane);
        addWindowListener(new closing());
  }
     public  class closing extends WindowAdapter{
                @Override
                public void windowClosing(WindowEvent e) {
                    chart.fireChartChanged();   //When the window is closed the chart will be changeable
                    dispose();
                }
        }
  
  private DefaultHighLowDataset createDataset(Date_info[] info){
    int arrays = 0;
    for (int i=0;i<7800;i++){   //Finds the number of instances in the info array
        arrays = i;             
        if (info[i]==null){
            break;
        }
    }
   
    Date[] date = new Date[arrays];
    double[] high = new double[arrays];
    double[] low = new double[arrays];
    double[] open = new double[arrays];
    double[] close = new double[arrays];
    double[] volume = new double[arrays];
    
    Calendar calendar = Calendar.getInstance();
    calendar.set(2008, 5, 1);
    int a =0;

   for (int i = 0; i < 7800; i++) {     //Stores all the values in "info" into the following arrays
        date[i] = createData(info[i].year-1900, info[i].month, info[i].day);        //Creates an instance of a date
        high[i] = info[i].stk_price_high;   
        low[i] = info[i].stk_price_low;
        open[i] = info[i].stk_price_open;  
        close[i] = info[i].stk_price_close;
        volume[i] = 0.0;
        if (info[i+1]==null){
            break;
        }
    }

        DefaultHighLowDataset data = new DefaultHighLowDataset(     //Creates HighLow Dataset for Candlestick Chart
        "Value", date, high, low, open, close, volume);
       
        return data;    //Returns dataset
  }

  private Date createData(int year, int month, int date) {      //Function used to created a "Date"
        Date day = new Date(year,month,date);
        return day;
        }
}
