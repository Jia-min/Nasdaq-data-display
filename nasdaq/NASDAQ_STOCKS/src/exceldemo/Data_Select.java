
package exceldemo;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * @author Mingchen Jia
 * @version 1.0
 * @since JDK 7.2
 * @since 1/19/2014
 */

public class Data_Select extends JFrame {
    private JButton Volume; //Button to display volume
    private JButton high_low;   //Button do display candlestick chart
    private JButton dividends;  //Button to display dividend values
    private JButton adjusted_close; //Button to display adjusted closing values;
    public static Boolean Display = false;  //Variable used to determine whether or not the display is already open
    String Company_symbol;
    Date_info[] date_info;


    public Data_Select(String Company_symbol, Date_info[] date_info){
        //Below is the interface for the data_select JFrame
        super("NASDAQ");
        this.Company_symbol = Company_symbol;   //Set Company symbol
        this.date_info = date_info;     //Set Date_info
        setLayout(new FlowLayout(30,30,30));
        
        
        
        Volume = new JButton();
      
        Volume.setPreferredSize(new Dimension(80,80));
        Volume.setToolTipText("This will display a time-series graph of the volume");
        Volume.setText("VOLUME");
        
        add(Volume);
        
        //Sets Candlestick Chart Button
        high_low = new JButton();
        high_low.setPreferredSize(new Dimension(80,80));
        high_low.setText("Candlestick Chart");
        high_low.setToolTipText("This will display a high-low graph of the stock");
        add(high_low);
        
        //Sets Dividend Box and Whisker Plot Button
        dividends = new JButton();
        dividends.setPreferredSize(new Dimension(80,80));
        dividends.setText("Dividend Payments");
        dividends.setToolTipText("This will display the dividends of this company");
        add(dividends);
        
        //Sets Adjusted Closing Chart Button
        adjusted_close = new JButton();
        adjusted_close.setPreferredSize(new Dimension(80,80));
        adjusted_close.setText("Adjusted Close");
        adjusted_close.setToolTipText("This will display the adjusted closing prices");
        add(adjusted_close);
        
        //handlers used for actionlistener when buttons are pressed
        thehandler handler = new thehandler();
        Volume.addActionListener(handler);
        high_low.addActionListener(handler);
        dividends.addActionListener(handler);
        adjusted_close.addActionListener(handler);
        addWindowListener(new closing() );
        
        
    }
   
        public  class closing extends WindowAdapter{
                @Override
                public void windowClosing(WindowEvent e) {  //When the window is closed, every variable is set back to normal
                    Display = false;
                    Main_menu.no_dividend = true;
                    int a =0;
                    while (date_info[a]!= null){    //Empties out date_info array for next stock
                        date_info[a] = null;
                        
                        a++;
                   }
                    dispose();
                }
        }
   

        public class thehandler implements ActionListener{
                //Actions that are implemented when the specified button is clicked
            @Override
            public void actionPerformed (ActionEvent event){

                 if(event.getSource()==Volume ){    
                    Volume_display testDisplay = new Volume_display(Company_symbol,Company_symbol,"DATE","VOLUME",date_info);
                    testDisplay.setSize(400,400);
                    Volume_display.displayed = true;
                    testDisplay.setVisible(true);
                    
                }if(event.getSource()==high_low){
                    
                    CandleStickChart testdisplay = new CandleStickChart(Company_symbol,date_info);
                    testdisplay.pack();
                    testdisplay.setSize(600,600);
                    testdisplay.setVisible(true);
                }if(event.getSource()==dividends){
                    if (Main_menu.no_dividend==true){
                        JOptionPane.showMessageDialog(null,"This company does not pay dividends");
                    }else{
                        Dividends_display dividends = new Dividends_display(Company_symbol,Company_symbol,"The black dot is the average, en.wikipedia.org/wiki/Box_plot to learn more","value",date_info);
                        dividends.setSize(500, 500);
                        dividends.setVisible(true);
                    }
                }if(event.getSource()==adjusted_close){
                        Adjusted_Closing closing = new Adjusted_Closing(Company_symbol,Company_symbol,"DATE","Adjusted Closing",date_info);
                        closing.setSize(500,500);
                        closing.setVisible(true);
                }   
            
            }
    }
  
}
