
package exceldemo;

/*
 * @author Mingchen Jia
 * @version 1.0
 * @since JDK 7.2
 * @since 1/19/2014
 */

public class Company {
    String stock_symbol;    //Company's stock symbol
    Date_info[] open_dates = new Date_info[7800];   //Stores all dates for company  

    
    public Company(String stock_symbol){    //Company Constructor
        this.stock_symbol = stock_symbol;
    }

}
