/**
 * <h1> Packages Database </h1>
 * 
 * This program keeps a record of packages which can be amended and viewed 
 * then ultimately stored in a ser file for future use. Also holds users and 
 * past (completed) transactions.
 * 
 * @author Taylor Carr
 * @version 1.3
 * @since 2017-09-25
 */

package assign5_tac91;

import java.awt.Dimension;
import javax.swing.JFrame;
import java.util.logging.*;

/**
 * Contains a try-catch for the readFile method in Menu and contains a 
 * Runnable that keeps a GUI running until the user exits the program.
 */
public class MainClass extends Menu {

    /**
     *
     */
    public static final Logger logger = Logger.getLogger(GUI.class.getName());
    
    /**
     * Sets run flag that tells the program when to quit.
     */
    public static boolean run = true; // run flag
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try{
            readFile();
        }
        catch (Exception e){
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, e);
            //System.out.println("'packages.ser' does not exist. A file will be created.");
        }
        
        Runnable r = new GUI();
        Thread t = new Thread(r);
        t.start();
        try {
            t.join();
            saveDatabase();
        } catch (InterruptedException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
