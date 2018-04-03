package assign5_tac91;

import java.io.Serializable;
import static java.lang.System.out;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

/**
 *
 * @author Taylor Carr
 * 
 * Contains get and set methods for the variable fields in the class
 */
public class Completed implements Serializable {
    private int customerID;
    private String trackingNumber;
    Date shippingDate = new Date();
    Date deliveryDate = new Date();
    private float cost;
    private int employeeID;
    
    /**
     *
     * @param cid
     * @param tn
     * @param sd
     * @param dd
     * @param c
     * @param eid
     */
    public Completed(int cid, String tn, Date sd, Date dd, float c, int eid) {
        this.customerID = cid;
        this.trackingNumber = tn;
        this.shippingDate = sd;
        this.deliveryDate = dd;
        this.cost = c;
        this.employeeID = eid;
    }

    /**
     *
     * @param custID
     */
    public void setCustomerID(int custID){
        customerID = custID;
    }
    
    /**
     *
     * @param TN
     */
    public void setTrackingNumber(String TN){
        trackingNumber = TN;
    }
    
    /**
     * Sets Delivery Date for completed transaction
     */
    public void setShippingDate(){
        Scanner cin = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat ("MM/dd/yyyy");
        out.println("Date Format MM/dd/yyyy");
        out.print("Enter the shipping date: ");
        String date = cin.nextLine();
        try {
            shippingDate = dateFormat.parse(date);
            dateFormat.format(shippingDate);
        }
        catch (ParseException e){
        }
    }
    
    /**
     * Sets Delivery Date for completed transaction
     */
    public void setDeliveryDate(){
        Scanner cin = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat ("MM/dd/yyyy");
        out.println("Date Format MM/dd/yyyy");
        out.print("Enter the delivery date: ");
        String date = cin.nextLine();
        try {
            deliveryDate = dateFormat.parse(date);
            dateFormat.format(deliveryDate);
        }
        catch (ParseException e){
        }
    }
    
    /**
     *
     * @param newCost
     */
    public void setCost(float newCost){
        cost = newCost;
    }
    
    /**
     *
     * @param empID
     */
    public void setEmployeeID (int empID){
        employeeID = empID;
    }
    
    /**
     *
     * @return customerID
     */
    public int getCustomerID(){
        return customerID;
    }
    
    /**
     *
     * @return trackingNumber
     */
    public String getTrackingNumber(){
        return trackingNumber;
    }
    
    /**
     *
     * @return shippingDate
     */
    public Date getShippingDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat ("MM/dd/yyyy");
        dateFormat.format(shippingDate);
        return shippingDate;
    }
    
    /**
     *
     * @return deliveryDate
     */
    public Date getDeliveryDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat ("MM/dd/yyyy");
        dateFormat.format(deliveryDate);
        return deliveryDate;
    }
    
    /**
     *
     * @return cost
     */
    public float getCost(){
        return cost;
    }
    
    /**
     *
     * @return employeeID
     */
    public int getEmployeeID(){
        return employeeID;
    }

}