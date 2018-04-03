/**
 * @author Taylor Carr
 */

package assign2_tac91;

import java.io.*;
import java.util.*;
import static java.lang.System.out;
import java.text.SimpleDateFormat;

/**
 * This menu handles all user input.
 */
public class Menu {
    
    static ArrayList<Package> packageList = new ArrayList<Package>();
    static ArrayList<Users> userList = new ArrayList<>();
    static ArrayList<Completed> completedList = new ArrayList<>();
    
    /**
     * <h2> Read Serialization File </h2>
     * 
     * This method reads the ser file (if one exists) and adds that 
     * information to the database.
     */
    public static void readFile() {
        String filename = "packages.ser";
        String filename2 = "users.ser";
        String filename3 = "completed.ser";
        
        try {     
            FileInputStream fin = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fin);
            packageList = (ArrayList<Package>) ois.readObject();
            
            FileInputStream fin2 = new FileInputStream(filename2);
            ObjectInputStream ois2 = new ObjectInputStream(fin2);
            userList = (ArrayList<Users>) ois2.readObject();
            
            FileInputStream fin3 = new FileInputStream(filename3);
            ObjectInputStream ois3 = new ObjectInputStream(fin3);
            completedList = (ArrayList<Completed>) ois3.readObject();
        }
        catch (EOFException exc){
            // End of file
            out.println("EOF");
        }
        catch (IOException ioe){
            out.println("IO Error");
        }
        catch (ClassNotFoundException cnfe){
            out.println("Class not found");
        }
    } 
    
    /**
     * <h2> Display Menu </h2>
     * 
     * This method displays the menu to the user.
     * @return boolean
     */
    public static boolean displayMenu(){
            System.out.println("---------------------------------------------------------");
            System.out.println("|                          Menu                         |");
            System.out.println("---------------------------------------------------------");
            System.out.println("|1. Show all existing package records in the database.  |");
            System.out.println("|2. Add new package record to the database.             |");
            System.out.println("|3. Delete a package record from database.              |");
            System.out.println("|4. Search for a package using tracking number.         |");
            System.out.println("|5. Show a list of users.                               |");
            System.out.println("|6. Add a new user to the database.                     |");
            System.out.println("|7. Update user info.                                   |");
            System.out.println("|8. Complete a shipping transaction.                    |");
            System.out.println("|9. Show completed shipping transactions.               |");
            System.out.println("|10. Exit program.                                      |");
            System.out.println("---------------------------------------------------------");
            System.out.println();
            System.out.print("Select an action: ");        
            
            Scanner cin = new Scanner(System.in);
            int choice = cin.nextInt();
            
            switch(choice){
                    case 1:
                        displayDatabase();
                        break;
                    case 2:
                        addPackage();
                        break;
                    case 3:
                        deletePackage();
                        break;
                    case 4:
                        search();
                        break;
                    case 5:
                        showUsers();
                        // showRange();
                        break;
                    case 6:
                        addUser();
                        break;
                    case 7:
                        updateUser();
                        break;
                    case 8:
                        completeTransaction();
                        break;
                    case 9:
                        showCompleted();
                        break;
                    case 10:
                        try {
                            saveDatabase();
                        }
                        catch (Exception e) {
                            out.println("Error: Couldn't save database");
                        }
                        return false;
                    default: 
                        System.out.print("Invalid input");
                        break;
            }
        return true;
    }
    
    /**
     * <h2> Display Database </h2>
     * 
     * This method displays the current database of packages to the user.
     */
    // Option 1
    public static void displayDatabase(){
        
        sortPackageList(); // Sort before display
        Package displayObj;
        
            out.println("------------------------------------------------------------------------------");
            out.println("|                                  Database                                  |");
            out.println("------------------------------------------------------------------------------");
            out.println("| Tracking # | Specification |  Class  |                 Other               |");
            out.println("------------------------------------------------------------------------------");
            
        for (int i = 0; i < packageList.size(); i++){
            displayObj = packageList.get(i);
            out.format("|%12s|", displayObj.getTrackingNumber());
            out.format("%15s|", displayObj.getSpec());
            out.format("%9s|", displayObj.getMC());
            
            if (displayObj instanceof Envelope){
                out.print(" Height: ");
                out.format("%12d|", ((Envelope) displayObj).getHeight());
                out.print(" Width: ");
                out.format("%7d|", ((Envelope) displayObj).getWidth());
            }
            else if (displayObj instanceof Box){
                out.print(" Dimensions: ");
                out.format("%8d|", ((Box) displayObj).getDimensions());
                out.print(" Volume: ");
                out.format("%6d|", ((Box) displayObj).getVolume());
            }
            else if (displayObj instanceof Crate){
                out.print(" Content: ");
                out.format("%11s|", ((Crate) displayObj).getContent());
                out.print(" Weight: ");
                out.format("  %2.1f|", ((Crate) displayObj).getWeight());
                
            }
            else { // Drum
                out.print(" Material: ");
                out.format("%10s|", ((Drum) displayObj).getMaterial());
                out.print(" Diameter: ");
                out.format("%3.1f|", ((Drum) displayObj).getDiameter());
            }
            out.println();
            out.println("------------------------------------------------------------------------------");
            // out.println();
        }
    }
    
    /**
     * <h2> Add a Package </h2>
     * 
     * This method adds a package to the database.
     */
    // Option 2
    public static void addPackage(){
  
        Package newPackage = new Package();
        out.println("What type of package is it?");
        out.println("Envelope | Box | Crate | Drum");
        out.println();
        
        Scanner cin = new Scanner(System.in);
        String input = cin.nextLine();
        switch (input) {
            case "Envelope":
            case "envelope":
                Envelope envelope = new Envelope();
                envelope.newEnvelope();
                packageList.add(envelope);
                break;
            case "Box":
            case "box":
                Box box = new Box();
                box.newBox();
                packageList.add(box);
                break;
            case "Crate":
            case "crate":
                Crate crate = new Crate();
                crate.newCrate();
                packageList.add(crate);
                break;
            case "Drum":
            case "drum":
                Drum drum = new Drum();
                drum.newDrum();
                packageList.add(drum);
                break;
            default:
                out.println("Invalid type");
                break;
        }
        sortPackageList(); // Sort list after insert;
    }
    
    /**
     * <h2> Remove a Package </h2>
     * 
     * This method deletes a package from the database using the tracking number.
     */
    // Option 3
    public static void deletePackage(){
        
        boolean found = false;
        int i = 0;
        
        out.print("Enter the tracking number of the package you want to delete: ");
        Scanner cin = new Scanner(System.in);
        String input = cin.nextLine();
        
        
        while (i < packageList.size() && found == false){
            Package deleteObj = packageList.get(i);
            
            if (input.length() < 5){
                out.println();
                out.println("Invalid tracking number");
                out.println();
                i = packageList.size();
            }
     
            if (deleteObj.getTrackingNumber().equals(input)){
                packageList.remove(i);
                out.println();
                out.println("Package " + input + " removed");
                out.println();
                found = true;
            }
            else if (i == packageList.size()-1){
                out.println();
                out.println("No package matches the given tracking number");
                out.println();
                i++;
            }
            else {
                i++;
            }
        }
    }
    
    
    /**
     * <h2> Search for a Package </h2>
     * 
     * This method asks the user for a tracking number and searches for a 
     * package in the database with the matching number.
     */
    // Option 4
    public static void search(){
        boolean found = false;
        int i = 0;
        
        out.print("Enter the tracking number of the package you want to find: ");
        Scanner cin = new Scanner(System.in);
        String input = cin.nextLine();
        
        while (i < packageList.size() && found == false){
            Package foundObj = packageList.get(i);
            
            if (input.length() != 5){
                out.println();
                out.println("Invalid tracking number");
                out.println();
                i = packageList.size();
            }
     
            if (foundObj.getTrackingNumber().equals(input)){
                out.println();
                out.println("------------------------------------------------------------------------------");
                out.println("| Tracking # | Specification |  Class  |                 Other               |");
                out.println("------------------------------------------------------------------------------");
                out.format("|%12s|", foundObj.getTrackingNumber());
                out.format("%15s|", foundObj.getSpec());
                out.format("%9s|", foundObj.getMC());
                if (foundObj instanceof Envelope){
                    out.print(" Height: ");
                    out.format("%12d|", ((Envelope) foundObj).getHeight());
                    out.print(" Width: ");
                    out.format("%7d|", ((Envelope) foundObj).getWidth());
                }
                else if (foundObj instanceof Box){
                    out.print(" Dimensions: ");
                    out.format("%8d|", ((Box) foundObj).getDimensions());
                    out.print(" Volume: ");
                    out.format("%6d|", ((Box) foundObj).getVolume());
                }
                else if (foundObj instanceof Crate){
                    out.print(" Content: ");
                    out.format("%11s|", ((Crate) foundObj).getContent());
                    out.print(" Weight: ");
                    out.format("  %2.1f|", ((Crate) foundObj).getWeight());
                    
                }
                else {
                    out.print(" Material: ");
                    out.format("%10s|", ((Drum) foundObj).getMaterial());
                    out.print(" Diameter: ");
                    out.format("%3.1f|", ((Drum) foundObj).getDiameter());
                }
                out.println();
                out.println("------------------------------------------------------------------------------");
                found = true;
            }
            else if (i == packageList.size()-1){
                out.println();
                out.println("No package matches the given tracking number");
                out.println();
                i++;
            }
            else {
                i++;
            }
        }
    }
    
    /**
     * <h2> Show Users </h2>
     * 
     * This method reads shows a list of users (employees and customers
     * currently in the database.
     */
    // Option 5
    public static void showUsers(){
        out.println();
        out.println("-------------------------------------------------------------------------------------");
        out.println("|                                       Users                                       |");
        out.println("-------------------------------------------------------------------------------------");
        out.println("| ID # |      Name      |                           Other                           |");
        out.println("-------------------------------------------------------------------------------------");
        
        for (Users userList1 : userList) {
            out.format("|%6d|", userList1.getID());
            out.format("%16s|", userList1.getName());
            if (userList1 instanceof Employee){
                out.print(" SSN: ");
                out.format("%10d|", ((Employee) userList1).getSSN());
                out.print(" Salary: ");
                out.format("$%2.2f|", ((Employee) userList1).getSalary());
                out.print(" Bank #: ");
                out.format("%17d|", ((Employee) userList1).getBankNumber());
            }
            else {
                out.print(" Phone #: ");
                out.format("%13s|", ((Customer) userList1).getPhoneNumber());
                out.print(" Address: ");
                out.format("%25s|", ((Customer) userList1).getAddress());
            }
            out.println();
            out.println("-------------------------------------------------------------------------------------");
        }
    }
    
    /**
     * <h2> Add User </h2>
     * 
     * This method adds a user to the database/ArrayList
     */
    // Option 6
    public static void addUser(){
        out.println();
        out.println("Is the new user an employee or a customer? ");
        Scanner cin = new Scanner(System.in);
        String input = cin.nextLine();
        switch (input){
            case "Employee":
            case "employee":
                Employee employee = new Employee();
                employee.newEmployee();
                userList.add(employee);
                break;
            case "Customer":
            case "customer":
                Customer customer = new Customer();
                customer.newCustomer();
                userList.add(customer);
                break;
            default:
                out.println("Invalid user type.");
                break;
        }
    }
    
    /**
     * <h2> Update User </h2>
     * 
     * This method allows the someone to update a data field for a user 
     * in the database.
     */
    // Option 7
    public static void updateUser(){
        boolean found = false;
        int i = 0;
        
        out.print("Enter the ID of the user you want to update: ");
        Scanner cin = new Scanner(System.in);
        int input = cin.nextInt();
        
        while (i < userList.size() && found == false){
            Users foundUser = userList.get(i);
            
            if (input < 11111 || input > 99999){
                out.println();
                out.println("Invalid User ID number");
                out.println();
                i = packageList.size();
            }
           
            if (input == foundUser.getID()) {
                out.println("What do you want to change?");
                if (foundUser instanceof Employee){
                    out.println("Name, SSN, Salary, Bank Number");
                    cin.nextLine();
                    String answer = cin.nextLine();
                    switch(answer){
                        case "Name":
                        case "name":
                            out.print("Enter the full name: ");
                            answer = cin.nextLine();
                            String[] name = answer.split(" ");
                            foundUser.setName(name[0], name[1]);
                            break;
                        case "SSN":
                        case "ssn":
                            out.print("Enter the SSN: ");
                            int SSN = cin.nextInt();
                            ((Employee) foundUser).setSSN(SSN);
                            break;
                        case "Salary":
                        case "salary":
                            out.print("Enter the Salary: ");
                            float salary = cin.nextFloat();
                            ((Employee) foundUser).setSalary(salary);
                            break;
                        case "Bank Number":
                        case "bank number":
                            out.print("Enter the Bank Number: ");
                            int bankNum = cin.nextInt();
                            ((Employee) foundUser).setSSN(bankNum);
                            break;
                        default:
                            out.println("Invalid answer.");
                            break;
                    }
                }
                else {
                    out.println("Name, Phone Number, Address");
                    cin.nextLine();
                    String answer = cin.nextLine();
                    switch(answer){
                        case "Name":
                        case "name":
                            out.print("Enter the full name: ");
                            answer = cin.nextLine();
                            String[] name = answer.split(" ");
                            foundUser.setName(name[0], name[1]);
                            break;
                        case "Phone Number":
                        case "phone number":
                            out.print("Enter the phone number: ");
                            answer = cin.nextLine();
                            ((Customer) foundUser).setPhoneNumber(answer);
                            break;
                        case "Address":
                        case "address":
                            out.print("Enter the address: ");
                            answer = cin.nextLine();
                            ((Customer) foundUser).setAddress(answer);
                            break;
                        default:
                            out.println("Invalid answer.");
                            break;
                    }
                }
                out.println();
                out.println("Field Updated.");
                out.println();
                found = true;
                
            }
            else if (i == userList.size()-1){
                out.println();
                out.println("No user matches the given ID number");
                out.println();
                i++;
            }
            else {
                i++;
            }
        }
    }
    
    /**
     * <h2> Complete Transaction </h2>
     * 
     * This method takes a package from the package list and moves it 
     * over to the completed list, with the addition of customer, employee, 
     * shipping and cost information.
     */
    //Option 8
    public static void completeTransaction(){
        out.print("Enter customer ID number: ");
        Scanner cin = new Scanner(System.in);
        int customerID = cin.nextInt();
        Users searchObj;
        Package packageObj;
        boolean valid = false;
        int employeeID = 0;
        String trackingNum = null;
        
        for (int i = 0; i < userList.size(); i++) {
            searchObj = userList.get(i);
            if (customerID == searchObj.getID() && searchObj instanceof Customer){
                valid = true;
            }
            else if (valid == false && i == userList.size() - 1){
                out.println("Customer not found.");
            }
        }
        
        
        if (valid == true){
            valid = false;
            out.print("Enter employee ID number: ");
            employeeID = cin.nextInt();
            
            for (int i = 0; i < userList.size(); i++) {
            searchObj = userList.get(i);
                if (employeeID == searchObj.getID() && searchObj instanceof Employee){
                    valid = true;
                }
                else if (valid == false && i == userList.size() - 1){
                    out.println("Employee not found.");
                }
            }
        }
        
        if (valid == true){
            valid = false;
            out.print("Enter the tracking number: ");
            cin.nextLine();
            trackingNum = cin.nextLine();
            
            for(int i = 0; i < packageList.size(); i++){
                packageObj = packageList.get(i);
                if (packageObj.getTrackingNumber().equals(trackingNum)){
                    valid = true;
                    packageList.remove(i);
                }
                else if (valid == false && i == packageList.size() - 1){
                    out.println("Package not found.");
                }
            }
        }
        
        if (valid == true){
            Completed completedObj = new Completed();
            
            completedObj.setCustomerID(customerID);
            completedObj.setEmployeeID(employeeID);
            completedObj.setTrackingNumber(trackingNum);
            
            out.print("What was the shipping cost? $");
            float cost = cin.nextFloat();
            completedObj.setCost(cost);
            completedObj.setShippingDate();
            completedObj.setDeliveryDate();
            
            
            completedList.add(completedObj);
        }
    }
    
    /**
     * <h2> Show Completed Transactions </h2>
     * 
     * This method displays all of the completed transactions recorded 
     * in the database.
     */
    // Option 9
    public static void showCompleted(){
        Completed completedObj;
        SimpleDateFormat dateFormat = new SimpleDateFormat ("MM/dd/yyyy");
        
        out.println("------------------------------------------------------------------");
        out.println("|                    Completed Transactions                      |");
        out.println("------------------------------------------------------------------");
        out.println("| Cust # | Tracking # |  Ship Date  |  Del Date  | Cost  | Emp # |");
        out.println("------------------------------------------------------------------");
        
        for (int i = 0; i < completedList.size(); i++){
            completedObj = completedList.get(i);
            out.format("|%8d|", completedObj.getCustomerID());
            out.format("%12s|  ", completedObj.getTrackingNumber());
            Date shipDate = completedObj.getShippingDate();
            out.print(dateFormat.format(shipDate));
            out.print(" | ");
            Date delDate = completedObj.getDeliveryDate();
            out.print(dateFormat.format(delDate));
            out.print(" | ");
            out.format("$%2.2f", completedObj.getCost());
            out.format("|%7d|", completedObj.getEmployeeID());
            out.println();
            out.println("------------------------------------------------------------------");
        }
        out.println();
        
    }
    
    /**
     * <h2> Store the Databases </h2>
     * 
     * This method saves the databases to ser files before the program closes.
     */
    //Option 10
    public static void saveDatabase() {
        sortPackageList(); // Sort List before Saving
        try {
            FileOutputStream fout = new FileOutputStream("packages.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(packageList);
            fout.close();
            
            FileOutputStream fout2 = new FileOutputStream("users.ser");
            ObjectOutputStream oos2 = new ObjectOutputStream(fout2);
            oos2.writeObject(userList);
            fout2.close(); 
                   
            FileOutputStream fout3 = new FileOutputStream("completed.ser");
            ObjectOutputStream oos3 = new ObjectOutputStream(fout3);
            oos3.writeObject(completedList);
            fout3.close(); 
            
            out.println("Exiting...");
        }
        catch (IOException e){
            out.println("IO error");
        }
    }
    
    /**
     *
     */
    public static void sortPackageList(){
        Package sortObj, sortObj2, temp;
        int compare;
        
        for (int i = 0; i < packageList.size(); i++){
            for (int j = 0; j < packageList.size() - 1; j++){
                sortObj = packageList.get(j);
                sortObj2 = packageList.get(j+1);
                compare = sortObj.getTrackingNumber().compareTo(sortObj2.getTrackingNumber());
                if (compare > 0){
                    temp = packageList.get(j);
                    packageList.set(j, sortObj2);
                    packageList.set(j+1, temp);
                }
            }
        }
    }
}