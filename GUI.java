/*
 * @author Taylor Carr
 * @version 1.0
 */
package assign5_tac91;

import static assign5_tac91.Menu.completedList;
import static assign5_tac91.Menu.packageList;
import static assign5_tac91.Menu.saveDatabase;
import static assign5_tac91.Menu.sortPackageList;
import static assign5_tac91.Menu.userList;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import java.util.logging.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Carr
 */
public class GUI extends JFrame implements ItemListener, Runnable {
    
    // Globals
    JFrame frame = new JFrame("Shipping Store");
    JPanel container = new JPanel(new BorderLayout());
    JPanel cards = new JPanel(new CardLayout());
    CardLayout cl = (CardLayout)(cards.getLayout());
    String[] menuOptions = {"Packages", "Users", "Transactions"}; 
    JComboBox menu = new JComboBox(menuOptions);
    
    // Tabbed Pane
    JTabbedPane tabbedPane = new JTabbedPane();
    
    JPanel packagesManager;
    JRadioButton showPackagesButton;
    JRadioButton addPackageButton;
    JRadioButton deletePackageButton;
    JRadioButton searchPackageButton;
    JButton managePackages;
    
    JPanel usersManager;
    JRadioButton showUsersButton;
    JRadioButton addUserButton;
    JRadioButton updateUserButton;
    JButton manageUsers;
    
    JPanel transactionsManager;
    JRadioButton showTransactionsButton;
    JRadioButton deliverPackageButton;
    JButton manageTransactions;
    
    GUI () {
        //initializeGUI();
        //initializeEvents();
    }
    
    /**
     *
     */
    public void initializeGUI () {
        
        JPanel comboBoxPane = new JPanel();
        menu.setEditable(false);
        menu.addItemListener((ItemListener) this);
        comboBoxPane.add(menu);
        
        // Packages Card
        packagesManager = new JPanel(new GridLayout(0, 1));
        showPackagesButton =  new JRadioButton("Show all packages");
        showPackagesButton.setSelected(true);
        addPackageButton = new JRadioButton("Add a package");
        deletePackageButton = new JRadioButton("Delete a package");
        searchPackageButton = new JRadioButton("Search for a package");
        
            // Button Group
            ButtonGroup packagesBG = new ButtonGroup();
            packagesBG.add(showPackagesButton);
            packagesBG.add(addPackageButton);
            packagesBG.add(deletePackageButton);
            packagesBG.add(searchPackageButton);
        
        packagesManager.add(showPackagesButton);
        packagesManager.add(addPackageButton);
        packagesManager.add(deletePackageButton);
        packagesManager.add(searchPackageButton);
        
        managePackages = new JButton("Next");
        packagesManager.add(managePackages);
        
        
        // Users Card
        usersManager = new JPanel(new GridLayout(0, 1));
        showUsersButton =  new JRadioButton("Show all users");
        showUsersButton.setSelected(true);
        addUserButton = new JRadioButton("Add a new user");
        updateUserButton = new JRadioButton("Update user");
        
            // Button Group
            ButtonGroup usersBG = new ButtonGroup();
            usersBG.add(showUsersButton);
            usersBG.add(addUserButton);
            usersBG.add(updateUserButton);
        
        usersManager.add(showUsersButton);
        usersManager.add(addUserButton);
        usersManager.add(updateUserButton);
        
        manageUsers = new JButton("Next");
        usersManager.add(manageUsers);
        
        // Transactions Card
        transactionsManager = new JPanel(new GridLayout(0,1));
        showTransactionsButton = new JRadioButton("Show all transactions");
        showTransactionsButton.setSelected(true);
        deliverPackageButton = new JRadioButton("Deliver a package");
        
            // Button Group
            ButtonGroup transactionsBG = new ButtonGroup();
            transactionsBG.add(showTransactionsButton);
            transactionsBG.add(deliverPackageButton);
        
        transactionsManager.add(showTransactionsButton);
        transactionsManager.add(deliverPackageButton);
        
        manageTransactions = new JButton("Next");
        transactionsManager.add(manageTransactions);
        
        
        tabbedPane.add(packagesManager, menuOptions[0]);
        tabbedPane.add(usersManager, menuOptions[1]);
        tabbedPane.add(transactionsManager, menuOptions[2]);
        container.add(tabbedPane, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.add(container);
        decor();
    }
    
    Menu main = new Menu();
    private void initializeEvents(){
        
        managePackages.addActionListener(new ActionListener () { 
            public void actionPerformed(ActionEvent e) {
                if (showPackagesButton.isSelected()) {
                    displayDatabase();
                }
                else if (addPackageButton.isSelected()) {
                    try {
                        addPackage();
                    } 
                    catch (Exception ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if (deletePackageButton.isSelected()) {
                    deletePackage();
                }
                else if (searchPackageButton.isSelected()) {
                    search();
                }
            }
        });
        manageUsers.addActionListener(new ActionListener () { 
            public void actionPerformed(ActionEvent e) {
                if (showUsersButton.isSelected())
                    showUsers();
                else if (addUserButton.isSelected())
                    addUser();
                else if (updateUserButton.isSelected())
                    try {
                        updateUser();
                } catch (Exception ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        manageTransactions.addActionListener(new ActionListener () { 
        public void actionPerformed(ActionEvent e) {

                if (showTransactionsButton.isSelected())
                    showCompleted();
                else if (deliverPackageButton.isSelected())
                    try {
                        completeTransaction();
                    } catch (Exception ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        });
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == menu) {
            if (menu.getSelectedItem().equals("Packages")){
                //cl.show(packagesManager, "Packages");
                frame.setContentPane(packagesManager);
                decor();
            }
            else if(menu.getSelectedItem().equals("Users")) {
                //cl.show(usersManager, "Users");
                frame.setContentPane(usersManager);
                decor();
            }
            else if (menu.getSelectedIndex() == 2) {
                //cl.show(transactionsManager, "Transactions");
                frame.setContentPane(transactionsManager);
                decor();
            }
        }
    }
    
    private void decor() {
        frame.validate();
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     *
     */
    public void displayDatabase(){
        JFrame f = new JFrame("Packages Database");
        JPanel topPanel = new JPanel();
        topPanel.setSize(400, 300);
        topPanel.setLayout(new BorderLayout());
        getContentPane().add(topPanel);
        

        // Create a new table instance
        String[] columnNames = {"Tracking Number", "Specification", "Class", "Other", ""};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        
        Package displayObj;
        Object[] data = new Object[5];
        for (int i = 0; i < packageList.size(); i++){
            displayObj = packageList.get(i);
            data[0] = displayObj.getTrackingNumber();
            data[1] = displayObj.getSpec();
            data[2] = displayObj.getMC();
            
            if (displayObj instanceof Envelope){
                data[3] = "Height " + ((Envelope) displayObj).getHeight();
                data[4] = " Width: " + ((Envelope) displayObj).getWidth();
            }
            else if (displayObj instanceof Box){
                data[3] = "Dimensions: " + ((Box) displayObj).getDimensions();
                data[4] = " Volume: " + ((Box) displayObj).getVolume();
            }
            else if (displayObj instanceof Crate){
                data[3] = "Content: " + ((Crate) displayObj).getContent();
                data[4] = " Weight: " + ((Crate) displayObj).getWeight();
            }
            else { // Drum
                data[3] = "Material: " + ((Drum) displayObj).getMaterial();
                data[4] = " Diameter: " + ((Drum) displayObj).getDiameter();
            }
            tableModel.addRow(data);
            table.revalidate();
        }
        table.setModel(tableModel);
        table.setFillsViewportHeight(true);
        // Add the table to a scrolling pane
        JScrollPane scrollPane = new JScrollPane(table);
        topPanel.add(scrollPane, BorderLayout.CENTER);
        f.add(topPanel);
        f.setSize(600,600);
        f.pack();
        f.validate();
        f.setVisible(true);
    }
    
    /**
     *
     */
    public void addPackage() {
        JFrame npFrame = new JFrame();
        JPanel outer = new JPanel(new BorderLayout());
        JPanel radioPanel = new JPanel();
        JPanel form = new JPanel();
        outer.setSize(500, 400);
        getContentPane().add(outer);
        form.setLayout(new GridLayout(5,2));
        outer.add(form, BorderLayout.CENTER);
        
        String[] specOptions = {"Fragile", "Books", "Catalogs", "Do-not-bend", "N/A"};
        JComboBox specification = new JComboBox(specOptions);
        String[] classOptions = {"First-Class", "Priority", "Retail", "Ground", "Metro"};
        JComboBox c = new JComboBox(classOptions);
        
        JRadioButton envelope = new JRadioButton("Envelope");
        JRadioButton box = new JRadioButton("Box");
        JRadioButton crate = new JRadioButton("Crate");
        JRadioButton drum = new JRadioButton("Drum");
        envelope.setSelected(true);
        ButtonGroup type = new ButtonGroup();
        type.add(envelope);
        type.add(box);
        type.add(crate);
        type.add(drum);
        radioPanel.add(envelope);
        radioPanel.add(box);
        radioPanel.add(crate);
        radioPanel.add(drum);
        outer.add(radioPanel, BorderLayout.NORTH);
        
        JLabel tnLabel = new JLabel("Tracking #: ");
        JTextField tnField = new JTextField(5);
        tnLabel.setLabelFor(tnField);
        form.add(tnLabel);
        form.add(tnField);
        
        JLabel specLabel = new JLabel("Specification: ");
       // JTextField specField = new JTextField(20);
        specLabel.setLabelFor(specification);
        form.add(specLabel);
        form.add(specification);
        
        JLabel classLabel = new JLabel("Class: ");
        //JTextField classField = new JTextField(20);
        classLabel.setLabelFor(c);
        form.add(classLabel);
        form.add(c);
        
        JLabel otherLabel = new JLabel("Other 1:");
        JTextField otherTF = new JTextField("Height/Dimensions/Content/Material",20);
        otherLabel.setLabelFor(otherTF);
        form.add(otherLabel);
        form.add(otherTF);
        otherTF.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                otherTF.setText("");
            }
        });
        
        JLabel otherLabel2 = new JLabel("Other 2:");
        JTextField otherTF2 = new JTextField("Width/Volume/Weight/Diameter",20);
        otherLabel.setLabelFor(otherTF2);
        form.add(otherLabel2);
        form.add(otherTF2);
        otherTF2.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                otherTF2.setText("");
            }
        });
        
        JButton finished = new JButton("DONE");
        outer.add(finished, BorderLayout.SOUTH);
        finished.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (envelope.isSelected()){
                    Envelope en = new Envelope(tnField.getText(), specification.getSelectedItem().toString(), c.getSelectedItem().toString(), Integer.parseInt(otherTF.getText()), Integer.parseInt(otherTF2.getText()));
                    packageList.add(en);
                    npFrame.dispose();
                }
                else if (box.isSelected()) {
                    Box b = new Box(tnField.getText(), specification.getSelectedItem().toString(), c.getSelectedItem().toString(), Integer.parseInt(otherTF.getText()), Integer.parseInt(otherTF2.getText()));
                    packageList.add(b);
                    npFrame.dispose();
                }
                else if (crate.isSelected()) {
                    Crate cr = new Crate (tnField.getText(), specification.getSelectedItem().toString(), c.getSelectedItem().toString(), Float.parseFloat(otherTF.getText()), otherTF2.getText());
                    packageList.add(cr);
                    npFrame.dispose();
                }
                else if (drum.isSelected()) {
                    Drum d = new Drum(tnField.getText(), specification.getSelectedItem().toString(), c.getSelectedItem().toString(), otherTF.getText(), Float.parseFloat(otherTF2.getText()));
                    packageList.add(d);
                    npFrame.dispose();
                }
                saveDatabase();
            }
            
        });
        
        npFrame.add(outer);
        npFrame.setSize(500,400);
        npFrame.pack();
        npFrame.validate();
        npFrame.setVisible(true);
    }
    
    /**
     *
     */
    public void deletePackage() {
        JFrame f = new JFrame();
        JPanel prompt = new JPanel();
        JLabel tnLabel = new JLabel("Tracking #: ");
        JTextField tnField = new JTextField(5);
        tnLabel.setLabelFor(tnField);
        JButton done = new JButton("Done");
        prompt.add(tnLabel);
        prompt.add(tnField);
        prompt.add(done);
        
        done.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                boolean found = false;
                int i = 0;
                String input = tnField.getText();
        
                while (i < packageList.size() && found == false){
                    Package deleteObj = packageList.get(i);
            
                    if (input.length() < 5){
                        Logger.getLogger(GUI.class.getName()).info("Invalid tracking number");
                        i = packageList.size();
                    }
     
                    if (deleteObj.getTrackingNumber().equals(input)){
                        packageList.remove(i);
                        saveDatabase();
                        Logger.getLogger(GUI.class.getName()).info("Package " + input + " removed");
                        found = true;
                    }
                    else if (i == packageList.size()-1){
                        Logger.getLogger(GUI.class.getName()).log(Level.INFO, "No package matches the given tracking number");
                        i++;
                    }
                    else {
                        i++;
                    }
                }
                
                f.dispose();
            }
        });
        
        f.add(prompt);
        f.setSize(200,300);
        f.pack();
        f.validate();
        f.setVisible(true);
    }
    
    /**
     *
     */
    public void search() {
        JFrame f = new JFrame();
        JPanel outer = new JPanel(new BorderLayout());
        JPanel prompt = new JPanel(new FlowLayout());
        JPanel results = new JPanel();
        JLabel tnLabel = new JLabel("Tracking #: ");
        JTextField tnField = new JTextField(5);
        tnLabel.setLabelFor(tnField);
        JButton search = new JButton("Search");
        prompt.add(tnLabel);
        prompt.add(tnField);
        prompt.add(search);
        outer.add(prompt, BorderLayout.NORTH);
        String[] columnNames = {"Tracking Number", "Specification", "Class", "Other", ""};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        results.add(table);
        
        search.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                boolean found = false;
                int i = 0;
                String input = tnField.getText();
                Object[] data = new Object[5];
        
                while (i < packageList.size() && found == false){
                    Package displayObj = packageList.get(i);
            
                    if (input.length() != 5){
                        Logger.getLogger(GUI.class.getName()).info("Invalid tracking number");
                        i = packageList.size();
                    }
     
                    if (displayObj.getTrackingNumber().equals(input)){
                
                        data[0] = displayObj.getTrackingNumber();
                        data[1] = displayObj.getSpec();
                        data[2] = displayObj.getMC();
            
                        if (displayObj instanceof Envelope){
                            data[3] = "Height " + ((Envelope) displayObj).getHeight();
                            data[4] = " Width: " + ((Envelope) displayObj).getWidth();
                        }
                        else if (displayObj instanceof Box){
                            data[3] = "Dimensions: " + ((Box) displayObj).getDimensions();
                            data[4] = " Volume: " + ((Box) displayObj).getVolume();
                        }
                        else if (displayObj instanceof Crate){
                            data[3] = "Content: " + ((Crate) displayObj).getContent();
                            data[4] = " Weight: " + ((Crate) displayObj).getWeight();
                        }
                        else { // Drum
                            data[3] = "Material: " + ((Drum) displayObj).getMaterial();
                            data[4] = " Diameter: " + ((Drum) displayObj).getDiameter();
                        }
                        tableModel.addRow(data);
                        table.revalidate();
                        found = true;
                    }
                    else if (i == packageList.size()-1){
                        Logger.getLogger(GUI.class.getName()).log(Level.INFO, "No package matches the given tracking number");
                        i++;
                    }
                    else {
                        i++;
                    }
                }
            }
        });
        table.setModel(tableModel);
        table.setFillsViewportHeight(true);
        outer.add(results, BorderLayout.SOUTH);
        f.add(outer);
        f.setSize(400,300);
        f.pack();
        f.validate();
        f.setVisible(true);
    }
    
    /**
     *
     */
    public void showUsers() {
        JFrame usersFrame = new JFrame("Users Database");
        JPanel outer = new JPanel();
        JPanel tablePanel = new JPanel(new BorderLayout());
        String[] columnNames = {"ID #", "Name", "Other", "", ""};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        //tablePanel.add(table);
        sortPackageList();
        Object [] data = new Object[5];
        Users user;
        for (int i = 0; i < userList.size(); i++) {
            user = userList.get(i);
            data[0] = user.getID();
            data[1] = user.getName();
            if (user instanceof Employee) {
                data[2] = "SSN: " + ((Employee) user).getSSN();
                data[3] = " Salary: $" + ((Employee) user).getSalary();
                data[4] = " Bank #: " + ((Employee) user).getBankNumber();
            }
            else {
                data[2] = "Phone #: " + ((Customer) user).getPhoneNumber();
                data[3] = "Address: " + ((Customer) user).getAddress();
                data[4] = "";
            }
            tableModel.addRow(data);
            table.revalidate();
        }
        table.setModel(tableModel);
        table.setFillsViewportHeight(true);
        // Add the table to a scrolling pane
        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        outer.add(tablePanel);
        usersFrame.add(outer);
        usersFrame.setSize(500,400);
        usersFrame.pack();
        usersFrame.validate();
        usersFrame.setVisible(true);
    }
    
    /**
     *
     */
    public void addUser(){
        JFrame newUser = new JFrame("New User");
        JPanel container = new JPanel(new BorderLayout());
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel userCards = new JPanel(new CardLayout());
        JPanel empForm = new JPanel(new GridLayout(6, 2));
        JPanel custForm = new JPanel(new GridLayout(5,2));
        tabbedPane.add(empForm, "Employee");
        tabbedPane.add(custForm, "Customer");
        
        
        // Customer Fields
        JLabel idLabel = new JLabel("ID #:");
        JTextField idField = new JTextField();
        idLabel.setLabelFor(idField);
        
        JLabel fnLabel = new JLabel("First Name:");
        JTextField fnField = new JTextField();
        fnLabel.setLabelFor(fnField);
        
        JLabel lnLabel = new JLabel("Last Name:");
        JTextField lnField = new JTextField();
        lnLabel.setLabelFor(lnField);
        
        JLabel pnLabel = new JLabel("Phone Number:");
        JTextField pnField = new JTextField();
        pnLabel.setLabelFor(pnField);
        
        JLabel addrLabel = new JLabel("Address:");
        JTextField addrField = new JTextField();
        addrLabel.setLabelFor(addrField);
        
        // Employee Fields
        JLabel idLabel2 = new JLabel("ID #:");
        JTextField idField2 = new JTextField();
        idLabel2.setLabelFor(idField2);
        
        JLabel fnLabel2 = new JLabel("First Name:");
        JTextField fnField2 = new JTextField();
        fnLabel2.setLabelFor(fnField2);
        
        JLabel lnLabel2 = new JLabel("Last Name:");
        JTextField lnField2 = new JTextField();
        lnLabel2.setLabelFor(lnField2);
        
        JLabel ssnLabel = new JLabel("SSN:");
        JTextField ssnField = new JTextField();
        ssnLabel.setLabelFor(ssnField);
        
        JLabel salLabel = new JLabel("Salary: $");
        JTextField salField = new JTextField();
        salLabel.setLabelFor(salField);
        
        JLabel bankLabel = new JLabel("Bank #:");
        JTextField bankField = new JTextField();
        bankLabel.setLabelFor(bankField);
        
        JButton ok = new JButton("OK");
        container.add(ok, BorderLayout.SOUTH);
        
        // Customer Form
        custForm.add(idLabel);
        custForm.add(idField);
        custForm.add(fnLabel);
        custForm.add(fnField);
        custForm.add(lnLabel);
        custForm.add(lnField);
        custForm.add(pnLabel);
        custForm.add(pnField);
        custForm.add(addrLabel);
        custForm.add(addrField);
        
        // Employee Form
        empForm.add(idLabel2);
        empForm.add(idField2);
        empForm.add(fnLabel2);
        empForm.add(fnField2);
        empForm.add(lnLabel2);
        empForm.add(lnField2);
        empForm.add(ssnLabel);
        empForm.add(ssnField);
        empForm.add(salLabel);
        empForm.add(salField);
        empForm.add(bankLabel);
        empForm.add(bankField);
        
        
        ok.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (tabbedPane.getSelectedIndex() == 0) {
                    Employee emp = new Employee(Integer.parseInt(idField2.getText()), fnField2.getText(), lnField2.getText(), Integer.parseInt(ssnField.getText()), Float.parseFloat(salField.getText()), Integer.parseInt(bankField.getText()));
                    userList.add(emp);
                }
                else if (tabbedPane.getSelectedIndex() == 1) {
                    Customer cust = new Customer(Integer.parseInt(idField.getText()), fnField.getText(), lnField.getText(), pnField.getText(), addrField.getText());
                    userList.add(cust);
                }
                sortPackageList();
                newUser.dispose();
                saveDatabase();
            }
        });
        
        container.add(tabbedPane, BorderLayout.CENTER);
        newUser.add(container);
        newUser.setSize(500,400);
        newUser.pack();
        newUser.validate();
        newUser.setVisible(true);
    }
    
    /**
     *
     */
    public void updateUser() {
        JFrame f = new JFrame("Update User");
        JPanel prompt = new JPanel();
        JLabel idLabel = new JLabel("ID #:");
        JTextField idField = new JTextField(10);
        idLabel.setLabelFor(idField);
        JButton search = new JButton("Search");
        prompt.add(idLabel);
        prompt.add(idField);
        prompt.add(search);
        f.add(prompt);
        f.setSize(300,300);
        f.pack();
        f.validate();
        f.setVisible(true);
        
        search.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                boolean found = false;
                int i = 0;
                int input = Integer.parseInt(idField.getText());
        
                if (userList.size() == 0){
                    Logger.getLogger(GUI.class.getName()).log(Level.INFO, "No user matches the given ID number");
                    f.dispose();
                }
                while (i < userList.size() && found == false){
                    Users foundUser = userList.get(i);
            
                    if (input < 11111 || input > 99999){
                        Logger.getLogger(GUI.class.getName()).log(Level.INFO, "Invalid User ID number");
                        i = packageList.size();
                    }
           
                    if (input == foundUser.getID()) {
                        if (foundUser instanceof Employee){
                            JFrame f2 = new JFrame("Employee Info");
                            JPanel container = new JPanel(new BorderLayout());
                            JPanel form = new JPanel(new GridLayout(6,2));
                            
                            JLabel idLabel = new JLabel("ID #:");
                            JTextField idField = new JTextField(foundUser.getID());
                            idLabel.setLabelFor(idField);
        
                            JLabel fnLabel = new JLabel("First Name:");
                            JTextField fnField = new JTextField(foundUser.getFirstName());
                            fnLabel.setLabelFor(fnField);
        
                            JLabel lnLabel = new JLabel("Last Name:");
                            JTextField lnField = new JTextField(foundUser.getLastName());
                            lnLabel.setLabelFor(lnField);
                            
                            JLabel ssnLabel = new JLabel("SSN:");
                            JTextField ssnField = new JTextField(((Employee) foundUser).getSSN());
                            ssnLabel.setLabelFor(ssnField);
        
                            JLabel salLabel = new JLabel("Salary: $");
                            Float temp = (((Employee) foundUser).getSalary());
                            JTextField salField = new JTextField(temp.toString());
                            salLabel.setLabelFor(salField);
        
                            JLabel bankLabel = new JLabel("Bank #:");
                            JTextField bankField = new JTextField(((Employee) foundUser).getBankNumber());
                            bankLabel.setLabelFor(bankField);
                            
                            JButton ok = new JButton("OK");
                            form.add(idLabel);
                            form.add(idField);
                            form.add(fnLabel);
                            form.add(fnField);
                            form.add(lnLabel);
                            form.add(lnField);
                            form.add(ssnLabel);
                            form.add(ssnField);
                            form.add(salLabel);
                            form.add(salField);
                            form.add(bankLabel);
                            form.add(bankField);
                            container.add(form, BorderLayout.CENTER);
                            container.add(ok, BorderLayout.SOUTH);
                            f2.add(container);
                            f2.pack();
                            f2.setVisible(true);
                            
                            ok.addActionListener(new ActionListener(){
                                public void actionPerformed(ActionEvent e){
                                    foundUser.setID(Integer.parseInt(idField.getText()));
                                    foundUser.setName(fnField.getText(), lnField.getText());
                                    ((Employee) foundUser).setSSN(Integer.parseInt(ssnField.getText()));
                                    ((Employee) foundUser).setSalary(Float.parseFloat(salField.getText()));
                                    ((Employee) foundUser).setBankNumber(Integer.parseInt(bankField.getText()));
                                    f2.dispose();
                                    saveDatabase();
                                }
                            });
                        }
                    
                        else { // Customer
                            JFrame f2 = new JFrame("Customer Info");
                            JPanel container = new JPanel(new BorderLayout());
                            JPanel form = new JPanel(new GridLayout(5,2));
                            
                            JLabel idLabel = new JLabel("ID #:");
                            JTextField idField = new JTextField(foundUser.getID());
                            idLabel.setLabelFor(idField);
        
                            JLabel fnLabel = new JLabel("First Name:");
                            JTextField fnField = new JTextField(foundUser.getFirstName());
                            fnLabel.setLabelFor(fnField);
        
                            JLabel lnLabel = new JLabel("Last Name:");
                            JTextField lnField = new JTextField(foundUser.getLastName());
                            lnLabel.setLabelFor(lnField);
                            
                            JLabel pnLabel = new JLabel("Phone Number:");
                            JTextField pnField = new JTextField(((Customer) foundUser).getPhoneNumber());
                            pnLabel.setLabelFor(pnField);
        
                            JLabel addrLabel = new JLabel("Address:");
                            JTextField addrField = new JTextField(((Customer) foundUser).getAddress());
                            addrLabel.setLabelFor(addrField);
                            
                            JButton ok = new JButton("OK");
                            form.add(idLabel);
                            form.add(idField);
                            form.add(fnLabel);
                            form.add(fnField);
                            form.add(lnLabel);
                            form.add(lnField);
                            form.add(pnLabel);
                            form.add(pnField);
                            form.add(addrLabel);
                            form.add(addrField);
                            container.add(form, BorderLayout.CENTER);
                            container.add(ok, BorderLayout.SOUTH);
                            f2.add(container);
                            f2.pack();
                            f2.setVisible(true);
                            
                            ok.addActionListener(new ActionListener(){
                                public void actionPerformed(ActionEvent e){
                                    foundUser.setID(Integer.parseInt(idField.getText()));
                                    foundUser.setName(fnField.getText(), lnField.getText());
                                    ((Customer) foundUser).setPhoneNumber(pnField.getText());
                                    ((Customer) foundUser).setAddress(addrField.getText());
                                    f2.dispose();
                                }
                            });
                        }
                        Logger.getLogger(GUI.class.getName()).log(Level.INFO, "Field Updated.");
                        found = true;
                    }
                
            
                    else if (i == userList.size()-1){
                        Logger.getLogger(GUI.class.getName()).log(Level.INFO, "No user matches the given ID number");
                        f.dispose();
                        i++;
                    }
                    else {
                        i++;
                    }
                }
            }
        });
    }
    
    /**
     *
     */
    public void showCompleted() {
        JFrame f = new JFrame("Completed Transactions");
        //JPanel container = new JPanel(new BorderLayout());
        JPanel tablePanel = new JPanel(new BorderLayout());
        String[] columnNames = {"Cust #", "Tracking #", "Ship Date", "Del Date", "Cost", "Emp #"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        
        Object [] data = new Object[6];
        
        Completed completedObj;
        SimpleDateFormat dateFormat = new SimpleDateFormat ("MM/dd/yyyy");
        
        for (int i = 0; i < completedList.size(); i++){
            completedObj = completedList.get(i);
            data[0] = completedObj.getCustomerID();
            data[1] = completedObj.getTrackingNumber();
            data[2] = completedObj.getShippingDate();
            //Date shipDate = completedObj.getShippingDate();
            //dateFormat.format(shipDate);
            //Date delDate = completedObj.getDeliveryDate();
            //dateFormat.format(delDate);
            data[3] = completedObj.getDeliveryDate();
            data[4] = completedObj.getCost();
            data[5] = completedObj.getEmployeeID();
            
            tableModel.addRow(data);
            table.revalidate();
        }
        table.setModel(tableModel);
        table.setFillsViewportHeight(true);
        // Add the table to a scrolling pane
        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        //container.add(tablePanel);
        f.add(tablePanel);
        f.setSize(500,400);
        f.pack();
        f.validate();
        f.setVisible(true);
    }
    
    /**
     *
     */
    public void completeTransaction() {
        JFrame f = new JFrame();
        JFrame f2 = new JFrame("Complete Transaction");
        JPanel container = new JPanel(new BorderLayout());
        JPanel prompt = new JPanel(new GridLayout(4,2));
        JLabel cidLabel = new JLabel("Cust ID #:");
        JTextField cidField = new JTextField();
        cidLabel.setLabelFor(cidField);
        JLabel eidLabel = new JLabel("Emp ID #:");
        JTextField eidField = new JTextField();
        eidLabel.setLabelFor(eidField);
        JLabel tnLabel = new JLabel("Tracking #:");
        JTextField tnField = new JTextField(5);
        tnLabel.setLabelFor(tnField);
        JButton enter = new JButton("Enter");
        prompt.add(cidLabel);
        prompt.add(cidField);
        prompt.add(eidLabel);
        prompt.add(eidField);
        prompt.add(tnLabel);
        prompt.add(tnField);
        prompt.add(enter);
        f.add(prompt);
        f.setSize(400,400);
        f.pack();
        f.validate();
        f.setVisible(true);
        
        enter.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int customerID = Integer.parseInt(cidField.getText());
                int employeeID = Integer.parseInt(eidField.getText());
                String trackingNum = tnField.getText();
                Users searchObj;
                Package packageObj;
                boolean valid = false;
                f.dispose();
                
                for (int i = 0; i < userList.size(); i++) {
                    searchObj = userList.get(i);
                    if (customerID == searchObj.getID() && searchObj instanceof Customer){
                        valid = true;
                    }
                    else if (valid == false && i == userList.size() - 1){
                        Logger.getLogger(GUI.class.getName()).log(Level.INFO, "Customer not found.");
                    }
                }
                
                if (valid == true){
                    valid = false;
            
                    for (int i = 0; i < userList.size(); i++) {
                        searchObj = userList.get(i);
                        if (employeeID == searchObj.getID() && searchObj instanceof Employee){
                            valid = true;
                        }
                        else if (valid == false && i == userList.size() - 1){
                            Logger.getLogger(GUI.class.getName()).log(Level.INFO, "Employee not found.");
                        }
                    }
                }
        
                if (valid == true){
                    valid = false;
            
                    for(int i = 0; i < packageList.size(); i++){
                        packageObj = packageList.get(i);
                        if (packageObj.getTrackingNumber().equals(trackingNum)){
                            valid = true;
                            packageList.remove(i);
                            saveDatabase();
                        }
                        else if (valid == false && i == packageList.size() - 1){
                            Logger.getLogger(GUI.class.getName()).log(Level.INFO, "Package not found.");
                        }
                    }
                }
        
                if (valid == true){
                    JFrame f2 = new JFrame("Complete Transaction");
                    JPanel container = new JPanel(new BorderLayout());
                    JPanel form = new JPanel(new GridLayout(3,2));
                    JLabel costLabel = new JLabel("Cost: $");
                    JTextField costField = new JTextField();
                    costLabel.setLabelFor(costField);
                    JLabel sdLabel = new JLabel("Shipping Date:");
                    JTextField sdField = new JTextField("01/31/2017");
                    sdLabel.setLabelFor(sdField);
                    JLabel ddLabel = new JLabel("Delivery Date:");
                    JTextField ddField = new JTextField("01/31/2017");
                    ddLabel.setLabelFor(ddField);
                    JButton done = new JButton("Done");
                    form.add(costLabel);
                    form.add(costField);
                    form.add(sdLabel);
                    form.add(sdField);
                    form.add(ddLabel);
                    form.add(ddField);
                    container.add(form, BorderLayout.CENTER);
                    container.add(done, BorderLayout.SOUTH);
                    f2.add(container);
                    
                    done.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            Float cost = Float.parseFloat(costField.getText());
                            SimpleDateFormat dateFormat = new SimpleDateFormat ("MM/dd/yyyy");
                            String date = sdField.getText();
                            Date shippingDate = null;
                            try {
                                shippingDate = dateFormat.parse(date);
                                dateFormat.format(shippingDate);
                            } catch (ParseException ex) {
                                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            date = ddField.getText();
                            Date deliveryDate = null;
                            try {
                                deliveryDate = dateFormat.parse(date);
                                dateFormat.format(deliveryDate);
                            }
                            catch (ParseException ex) {
                                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            Completed completedObj = new Completed(customerID, trackingNum, shippingDate, deliveryDate, cost, employeeID);
                            completedList.add(completedObj);
                            f2.dispose();
                        }
                    });
            
                    f2.setSize(500,400);
                    f2.pack();
                    f2.validate();
                    f2.setVisible(true);
                }
            }
        });
    }

    @Override
    public void run() {
        initializeGUI();
        initializeEvents();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
