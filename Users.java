package assign2_tac91;

import java.io.Serializable;

/**
 *
 * @author Taylor Carr
 */
public class Users implements Serializable {
    private int ID;
    private String firstName;
    private String lastName;

    /**
     *
     * @param newID
     */
    public void setID(int newID){
        ID = newID;
    }
    
    /**
     *
     * @param fn
     * @param ln
     */
    public void setName(String fn, String ln){
        firstName = fn;
        lastName = ln;
    }
    
    /**
     *
     * @return ID
     */
    public int getID(){
        return ID;
    }

    /**
     *
     * @return name
     */
    public String getName(){
        String name = firstName + " " + lastName;
        return name;
    }

}
