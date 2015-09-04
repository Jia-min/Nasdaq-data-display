
package exceldemo;



/*
 * @author Mingchen Jia
 * @version 1.0
 * @since JDK 7.2
 * @since 1/19/2014
 */

public class Date_info {
    String[] date_string;   //Stores the separated date string
    String date;
    int year;
    int month;
    int day;
    double stk_price_open;
    double stk_price_high;
    double stk_price_low;
    double stk_price_close;
    int stk_volume;
    float stk_price_adj_close;
    double dividend;

    
    public void get_dividend(String dividend){
        this.dividend = Double.parseDouble(dividend);
    }
    public Date_info(String date, String stk_price_open, String stk_high,String stk_price_low, String stk_price_close, String stk_volume, String stk_price_adj_close){
        //Transforms the inputted string values into doubles and integers
        
        this.date = date;
        this.date_string = date.split("-");
        //Date is separated into year month and day and each is converted to an integer
        this.year = Integer.parseInt(date_string[0]);   
        this.month = Integer.parseInt(date_string[1]);
        this.day = Integer.parseInt(date_string[2]);
        this.stk_price_open = Double.parseDouble(stk_price_open);
        this.stk_price_high = Double.parseDouble(stk_high);
        this.stk_price_low = Double.parseDouble(stk_price_low);
        this.stk_price_close = Double.parseDouble(stk_price_close);
        this.stk_volume = Integer.parseInt(stk_volume);
        this.stk_price_adj_close = Float.parseFloat(stk_price_adj_close);
    }

}
