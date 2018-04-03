package assign5_tac91;

import static java.lang.System.out;
import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author Taylor Carr
 * 
 * Subclass of Users Superclass
 */
public class Employee extends Users {
    private int SSN;
    private float salary;
    private int bankNumber;
    
    /**
     * Constructor initializes an employee object with the provided values.
     * @param id
     * @param firstName
     * @param lastName
     * @param socialSecurityNumber
     * @param monthlySalary
     * @param bankAccountNumber
     */
    public Employee(int id, String firstName, String lastName, int socialSecurityNumber, 
            float monthlySalary, int bankAccountNumber) {
        super(id, firstName, lastName);
        this.SSN = socialSecurityNumber;
        this.salary = monthlySalary;
        this.bankNumber = bankAccountNumber;
    }
    
    /**
     * Adds a new employee to the ArrayList
     */
    public void newEmployee(){
        out.println();
        out.println("Enter Employee Information:");
        out.println("Example: John Doe 123456789 12.50 1234567890");
        out.print("\nFirst Name, Last Name, SSN, Salary & Bank Number: ");
        
        Scanner cin = new Scanner(System.in);
        String input = cin.nextLine();
        String[] words = input.split(" ");
        
        if (words.length != 5){
            out.println();
            out.println("Invalid number of fields");
            out.println();
        }
        else {
            Random rand = new Random();
            this.setID(rand.nextInt(99999) + 11111);
            this.setName(words[0], words[1]);
            int temp = Integer.parseInt(words[2]);
            this.SSN =temp;
            float temp2 = Float.parseFloat(words[3]);
            this.salary=temp2;
            temp = Integer.parseInt(words[4]);
            this.bankNumber= temp;
        }
    }
    
    /**
     *
     * @param newSSN
     */
    public void setSSN(int newSSN){
        SSN = newSSN;
    }
    
    /**
     *
     * @param newSalary
     */
    public void setSalary(float newSalary){
        salary = newSalary;
    }
    
    /**
     *
     * @param newBN
     */
    public void setBankNumber(int newBN){
        bankNumber = newBN;
    }
    
    /**
     *
     * @return SSN
     */
    public int getSSN(){
        return SSN;
    }
    
    /**
     *
     * @return salary
     */
    public float getSalary(){
        return salary;
    }
    
    /**
     *
     * @return bankNumber
     */
    public int getBankNumber(){
        return bankNumber;
    }
}
