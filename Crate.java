package assign5_tac91;

import static java.lang.System.out;
import java.util.Scanner;

/**
 * Subclass of Package Superclass
 * @author Taylor Carr
 */
public class Crate extends Package {
    private float weight;
    private String content;
    
    public Crate (String ptn, String specification, String mailingClass, Float w, String c){
        super(ptn, specification, mailingClass);
        this.weight = w;
        this.content = c;
    }
    
    /**
     * Adds Crate object to Package ArrayList
     */
    public void newCrate(){
        // Package newCrate = new Package();
        
        out.println("Enter Crate Information:");
        out.println("Tracking Number, Specificaition, Mailing Class, Weight & Content");
        
        Scanner cin = new Scanner(System.in);
        String input = cin.nextLine();
        String[] words = input.split(" ");
        
        if (words.length != 5){
            out.println();
            out.println("Invalid number of fields");
            out.println();
        }
        else {
            this.setTrackingNumber(words[0]); 
            this.setSpec(words[1]);
            this.setMC(words[2]);
            this.weight = Float.parseFloat(words[3]);
            this.content = words[4];
        }
    }
    
    /**
     *
     * @return weight
     */
    public float getWeight(){
        return weight;
    }
    
    /**
     *
     * @return content
     */
    public String getContent(){
        return content;
    }
}
