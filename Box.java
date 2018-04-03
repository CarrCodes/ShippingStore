package assign5_tac91;

import static java.lang.System.out;
import java.util.Scanner;

/**
 * Subclass of Package Superclass
 * @author Taylor Carr
 */
public class Box extends Package{
    private int dimensions;
    private int volume;
    
    /**
     * Constructor used to initialize the class fields of the class with the
     * provided values.
     * @param ptn
     * @param specification
     * @param mailingClass
     * @param height
     * @param width
     */
    public Box(String ptn, String specification, String mailingClass, int d, int v) {
        super(ptn, specification, mailingClass);
        this.dimensions = d;
        this.volume = v;
    }
    
    /**
     * Adds Box object to Package ArrayList
     */
    public void newBox(){
        // Package newBox = new Package();
        
        out.println("Enter Box Information:");
        out.println("Tracking Number, Specificaition, Mailing Class, Dimensions & Volume");
        
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
            this.dimensions = Integer.parseInt(words[3]);
            this.volume = Integer.parseInt(words[4]);
        }
    }
    
    /**
     *
     * @return dimensions
     */
    public int getDimensions(){
        return dimensions;
    }

    /**
     *
     * @return volume
     */
    public int getVolume(){
        return volume;
    }
}
