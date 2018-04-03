package assign5_tac91;

import static java.lang.System.out;
import java.util.Scanner;

/**
 * Subclass of Package SUperclass
 * @author Taylor Carr
 */
public class Drum extends Package {
    private String material;
    private float diameter;
    
    public Drum(String ptn, String specification, String mailingClass, String m, Float d) {
        super(ptn, specification, mailingClass);
        this.material = m;
        this.diameter = d;
    }
    
    /**
     * Adds Drum object to Package ArrayList
     */
    public void newDrum(){
        // Package newDrum = new Package();
        
        out.println("Enter Drum Information:");
        out.println("Tracking Number, Specificaition, Mailing Class, Material & Diameter");
        
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
            this.material = words[3];
            this.diameter = Float.parseFloat(words[4]);
        }
    }
    
    /**
     *
     * @return material
     */
    public String getMaterial(){
        return material;
    }
    
    /**
     *
     * @return diameter
     */
    public float getDiameter(){
        return diameter;
    }
}
