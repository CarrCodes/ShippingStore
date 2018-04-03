/**
 * @author Taylor Carr
 */

package assign2_tac91;

import  java.io.*;

/**
 * This class holds all of the information and methods for storing packages 
 * in the database.
 */
public class Package implements Serializable {

    private String trackingNumber;
    private String specification;
    private String mailingClass;
    
    /**
     * Set the tracking number for the package
     * @param newTN 
     */
    public void setTrackingNumber(String newTN){
        trackingNumber = newTN;
    }

    /**
     * Set the specification
     * @param newSpec 
     */
    public void setSpec(String newSpec){
        specification = newSpec;
    }
    
    /**
     * Set the mailing class
     * @param newMC 
     */
    public void setMC(String newMC){
        mailingClass = newMC;
    }
    
    
    /**
     * @return trackingNumber
     */
    public String getTrackingNumber(){
        return trackingNumber;
    }

    /**
     * @return specification
     */
    public String getSpec(){
        return specification;
    }
    
    /**
     * @return mailingClass
     */
    public String getMC(){
        return mailingClass;
    }
}
