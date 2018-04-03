package assign5_tac91;

import java.io.Serializable;

/**
 * @author Taylor Carr
 * @version 1.3
 */
public class Users implements Serializable {
    private int ID;
    private String firstName;
    private String lastName;

    /**
     *
     * @param id
     * @param firstName
     * @param lastName
     */
    public Users(int id, String firstName, String lastName) {
        this.ID = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
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
    
    /**
     *
     * @return
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     *
     * @return
     */
    public String getLastName() {
        return lastName;
    }

}
