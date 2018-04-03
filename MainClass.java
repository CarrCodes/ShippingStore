/**
 * <h1> Packages Database </h1>
 * 
 * This program keeps a record of packages which can be amended and viewed 
 * then ultimately stored in a ser file for future use. Also holds users and 
 * past (completed) transactions.
 * 
 * @author Taylor Carr
 * @version 1.1
 * @since 2017-09-25
 */

package assign2_tac91;

/**
 * Contains a try-catch for the readFile method in Menu and contains a 
 * while loop that keeps running until the user exits the program.
 */
public class MainClass extends Menu {
    
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
            System.out.println("'packages.ser' does not exist. A file will be created.");
        }
        
        
        
        while (run == true){
            if (displayMenu() == false){
                run = false;
            }
        }
    }
    
}
