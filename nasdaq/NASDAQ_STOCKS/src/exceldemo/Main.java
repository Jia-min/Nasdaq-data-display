

package exceldemo;
import javax.swing.JFrame;

/*
 * @author Mingchen Jia
 * @version 1.0
 * @since JDK 7.2
 * @since 1/19/2014
 */

public class Main{
    public static Main_menu start_menu = new Main_menu();
  
    public static void main(String[] args){
        //Creates instance of start_menu and sets size of menu
        start_menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        start_menu.setSize(400, 400);
        start_menu.setVisible(true);
        
        if (start_menu.found ==true){
            start_menu.setVisible(false);
        }
        
    }
        
}
