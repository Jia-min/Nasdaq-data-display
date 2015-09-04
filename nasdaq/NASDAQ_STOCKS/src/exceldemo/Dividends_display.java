
package exceldemo;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.data.statistics.BoxAndWhiskerItem;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import org.jfree.ui.RectangleInsets;


/*
 * @author Mingchen Jia
 * @version 1.0
 * @since JDK 7.2
 * @since 1/19/2014
 */
public class Dividends_display extends JFrame{
 
  private static final long serialVersionUID = 1L;
  public String title;

  public Dividends_display(String Title, String chartTitle,String category_label, String value_label, Date_info[] date_info) {
        super(Title);   
        this.title = Title;
        int b =0;
        int e=0;
        int count=0;
        for (int x=0;x<7800;x++){
            if (date_info[x]==null){
                    break;
                }else if (date_info[x].dividend!=0.0){
                    count=count+1;
                }
        }
        //Dividends aren't paid on every day, this code is done to ensure that only paid dividends are implemented
        //since on ordinary days when dividends aren't paid the value is 0.0
        double[] dividends = new double[count];
        
        while (date_info[b]!=null){
            if (date_info[b].dividend!=0.0){
                dividends[e++]= date_info[b].dividend;
            }
            b++;
        }
        
        quickSort(dividends,0,e-1);
        for (int f =0;f<dividends.length;f++){
            
        }
        double mean=0,q1,q3,median,minregvalue,maxregvalue;
        
        for (int a=0;a<dividends.length;a++){
            mean = mean + dividends[a];
        }
        
        //Finds the values that are necessary in the box and whisker plot
        mean = (mean/dividends.length);
        int quartile = ((dividends.length-1)/4);
        median = dividends[(dividends.length-1)/2];
        q1 = dividends[quartile];
        q3 = dividends[3*quartile];
        minregvalue = dividends[0];
        maxregvalue = dividends[dividends.length-1];   
        ArrayList list = new ArrayList(3);
        BoxAndWhiskerItem item = new BoxAndWhiskerItem(mean,median,q1,q3,minregvalue,maxregvalue,minregvalue,maxregvalue,list); //Creates Dataset
        
        
        DefaultBoxAndWhiskerCategoryDataset set = new DefaultBoxAndWhiskerCategoryDataset();
        set.add(item, Title, quartile);
        
        JFreeChart chart = ChartFactory.createBoxAndWhiskerChart(Title,category_label, value_label,set,true);   //Constructs the chart
        
        //Code below sets the interface for the box and whisker plot
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(1.0, 1.0, 1.0, 1.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        BoxAndWhiskerRenderer r = new BoxAndWhiskerRenderer();
        plot.getRenderer().setSeriesPaint(0, Color.orange);
        plot.setRenderer(r);
        r.setMedianVisible(false);
        r.setMaximumBarWidth(mean);
        r.setUseOutlinePaintForWhiskers(true);        
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(400, 400));
        setContentPane(chartPanel);
        addWindowListener(new closing());
    }
     public  class closing extends WindowAdapter{
                @Override
                public void windowClosing(WindowEvent e) {
                    dispose();  //Allows the dividends page to be reused for another stock
                }
        }

  float partition(double array[], int leftindex, int rightindex){   //QuickSort used to arrange values of dividends in order
      
      int z = leftindex, y = rightindex;
      double temp;
      double centre = array[(leftindex + rightindex) / 2];
     
      while (z <= y) {
            while (array[z] < centre)
                  z++;
            while (array[y] > centre)
                  y--;
            if (z <= y) {
                  temp = array[z];
                  array[z] = array[y];
                  array[y] = temp;
                  z++;
                  y--;
            }
      }
     
      return z;
}
 
void quickSort(double array[], int leftnum, int rightnum) {
      int index = (int) partition(array, leftnum, rightnum);
      if (leftnum < index - 1)
            quickSort(array, leftnum, index - 1);
      if (index < rightnum)
            quickSort(array, index, rightnum);
}

  
  
}   
    
    
    
    

