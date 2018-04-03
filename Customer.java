package assign2_tac91;

import static java.lang.System.out;
import java.util.Random;
import java.util.Scanner;

/**
 * Subclass of Users Superclass
 * 
 * @author Taylor Carr
 */
public class Customer extends Users {
    private String phoneNumber;
    private String address;
    
    /**
     * Adds a new customer to the users ArrayList
     */
    public void newCustomer(){
        out.println();
        out.println("Enter Customer Information:");
        out.println("Example: John Doe 555-555-5555 ");
        out.print("\nFirst Name, Last Name, Phone Number: ");
        
        Scanner cin = new Scanner(System.in);
        String input = cin.nextLine();
        String[] words = input.split(" ");
        
        if (words.length != 3){
            out.println();
            out.println("Invalid number of fields");
            out.println();
        }
        else {
            Random rand = new Random();
            this.setID(rand.nextInt(99999) + 11111);
            this.setName(words[0], words[1]);
            this.phoneNumber=words[2];
            out.print("Enter the address: ");
            this.address=cin.nextLine();
        }
    }
    
    /**
     *
     * @param newPN
     */
    public void setPhoneNumber(String newPN){
        phoneNumber = newPN;
    }
    
    /**
     *
     * @param newAddr
     */
    public void setAddress(String newAddr){
        address = newAddr;
    }

    /**
     *
     * @return phoneNumber
     */
    public String getPhoneNumber(){
        return phoneNumber;
    }
    
    /**
     *
     * @return address
     */
    public String getAddress(){
        return address;
    }

}