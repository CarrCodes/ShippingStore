package assign5_tac91;

import static java.lang.System.out;
import java.util.*;
/**
 * Subclass of Package Superclass
 * @author Taylor Carr
 */
public class Envelope extends Package {
    private int height;
    private int width;

    /**
     * Constructor used to initialize the class fields of the class with the
     * provided values.
     * @param ptn
     * @param specification
     * @param mailingClass
     * @param height
     * @param width
     */
    public Envelope(String ptn, String specification, String mailingClass, Integer height, Integer width) {
        super(ptn, specification, mailingClass);
        this.height = height;
        this.width = width;
    }
    
    /**
     * Adds Envelope object to Package ArrayList
     */
    public void newEnvelope(){
        // Package newEnvelope = new Package();
        
        out.println("Enter Envelope Information:");
        out.println("Tracking Number, Specificaition, Mailing Class, Height & Width");
        
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
            this.height = Integer.parseInt(words[3]);
            this.width = Integer.parseInt(words[4]);
        }
    }
    
    /**
     *
     * @return height
     */
    public int getHeight(){
        return height;
    }
    
    /**
     *
     * @return width
     */
    public int getWidth(){
        return width;
    }
}


