
package exceldemo;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;


/*
 * @author Mingchen Jia
 * @version 1.0
 * @since JDK 7.2
 * @since 1/19/2014
 */

public class Main_menu extends JFrame {
    private JTextField Searchbar;
    public JLabel info_bar;
    int c;
    Boolean found = false;      //Boolean variable used to determine whether not a search is valid
    String Company_symbol;      //Company stock symbol
    Company company = new Company("AAAAA"); //An empty company array
    public static Boolean no_dividend = true;
    
//Dividends and stock_info is separated because info is on different files
    public void read_dividend_info(String symbol_search){       
        /* Reads all the dividend files until stock symbol you want to find is found
         * then loads all the dividend data related to this stock into the Company class under
         * the date_info array
         * @param symbol_search The stock symbol to be found
         */
        String[] letters = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};   
                        //Reads all data files from A to Z
        int a=0;
        
        try (BufferedReader dividend_info = new BufferedReader(new FileReader("J:\\2013-2014\\DATA//NASDAQ_dividends_" + letters[c]+ ".csv")) )
              {
                  String sCurrentLine = dividend_info.readLine();       //readline is repeated so that some useless data on line 1 is skipped
                  while ((sCurrentLine = dividend_info.readLine()) != null) {
                      String[] result = sCurrentLine.split(",");
                      if (symbol_search.equals(result[1])){         //When symbol_search is found all the dividend data is read into a Company class
                          
                          no_dividend = true;       
                          while (no_dividend==true){
                                if (company.open_dates[a].date.equals(result[2])){
                                    company.open_dates[a].get_dividend(result[3]);
                                    no_dividend = false;
                                    found = true;
                                }
                               a++;
                          }
                      }
                  }
                  
              } catch (IOException e) {
                  e.printStackTrace();
              }
    }
    
    public void read_stock_info(String symbol_search){
        /* Reads all the files until stock symbol you want to find is found
         * then loads all the  data related to this stock into the Company class under
         * the date_info array
         * @param symbol_search The stock symbol to be found
         */
        
        int a=0; 
        String[] letters = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
            try (BufferedReader stock_info = new BufferedReader(new FileReader("J:\\2013-2014\\DATA//NASDAQ_daily_prices_" + letters[c]+ ".csv")))  
                            //Reads all files from A to Z
              {
                  String sCurrentLine = stock_info.readLine();          //Skips first line to eliminate useless information
                  while ((sCurrentLine = stock_info.readLine()) != null) {
                      String[] result = sCurrentLine.split(",");
                      if (symbol_search.equals(result[1])){
                          found = true;
                           company.open_dates[a++] = new Date_info(result[2],result[3],result[4],result[5],result[6],result[7],result[8]);  //Loads all the data onto Date_info arrays
                        }
                  }
              } catch (IOException e) {
                  e.printStackTrace();
              }
    }
    
    public Main_menu(){
        //Code below generates the interface for the main menu
        super("NASDAQ");
        setLayout(new FlowLayout());
        Searchbar = new JTextField(15);
        Searchbar.setToolTipText("Enter the stock symbol here");
        add(Searchbar);
        
        info_bar = new JLabel();
        info_bar.setText("Enter the stock symbol here");
        info_bar.setSize(50, 200);
        add(info_bar);
       
        thehandler handler = new thehandler();
        Searchbar.addActionListener(handler);       //Handler used to enter/read info
      
    }
    
    public class thehandler implements ActionListener{
     
        @Override
        public void actionPerformed(ActionEvent event){

            String string= "";
            if (event.getSource()==Searchbar && Data_Select.Display==false){    //If search bar is entered and data is not already display then perform the following actions
                Company_symbol=String.format("%s", event.getActionCommand());
                Company_symbol = Company_symbol.toUpperCase();          //Converts Company_symbol chars to uppercase
                char[] letters = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','V','X','Y','Z'};
                    //If the first letter is a match then it will read the first letter's file
                    for (int x =0;x<25;x++){
                        if (letters[x] == Company_symbol.charAt(0)){
                            c = x;
                            company.stock_symbol = Company_symbol;
                            read_stock_info(Company_symbol);
                            read_dividend_info(Company_symbol);
                            break;
                        }
                    }
            }else{
                JOptionPane.showMessageDialog(null,"Close the last display first");     //If a display is already open this will pop up
            }
            
            if (found==false && Data_Select.Display==false){    
                JOptionPane.showMessageDialog(null,"INVALID INPUT");    //If the symbol is not found then this will pop up
            }else if (found==true){
                 Data_Select display = new Data_Select(Company_symbol,company.open_dates);  
                 //If the symbol is found, an instance of data_select will be constructed  
                 display.setSize(400, 400);
                 found = false; 
                 Data_Select.Display = true;
                 display.setVisible(true);            
            }
        }
       
    }
}   





