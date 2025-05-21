package assignment1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TravelGUI extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private WesternSafariTravel travel;
    private JTabbedPane tabs;
    private List<String> flights;

    // Accommodations
    private JTextArea accomOutput;
    private JButton btnDisplayAll, btnDisplayAvailable;

    // Customers
    private JTextField tfName, tfAge, tfEmail;
    private JButton btnAddCustomer, btnListCustomers;
    private JTextArea customerOutput;

    // Packages
    private JButton btnCreatePackage, btnListPackages;
    private JButton btnAddTaxiPass, btnAddScenicFlight;
    private JButton btnSavePackages, btnLoadPackages;

    
    
    public TravelGUI() throws ClassNotFoundException, IOException {
        super("Western Safari Travels");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        
        //setting up data, if theres no initial data (customers and accoms empty, then run the data intiailise command)
        travel = new WesternSafariTravel();
        travel.importPackages();
        if (travel.getAccom().isEmpty() && travel.getCustomers().isEmpty()) {
        	travel.data();
        }
        travel.populateFlights();
        flights = travel.getFlights();        
        
        //tabs
        tabs = new JTabbedPane();
        tabs.addTab("Accommodations", createAccommodationsTab());
        tabs.addTab("Customers", createCustomersTab());
        tabs.addTab("Packages", createPackagesTab());

        add(tabs);
        setVisible(true);
    }

    private JPanel createAccommodationsTab() {
    	//accommodations
        JPanel panel = new JPanel(new BorderLayout(5,5));
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
        //align items to left
        btnDisplayAll = new JButton("Display All Accommodations");
        btnDisplayAvailable = new JButton("Display Available");
        btnDisplayAll.setActionCommand("displayAll");
        btnDisplayAvailable.setActionCommand("displayAvailable");
        btnDisplayAll.addActionListener(this);
        btnDisplayAvailable.addActionListener(this);
        //when clicked it'll either display accommodations or display available 
        top.add(btnDisplayAll);
        top.add(btnDisplayAvailable);
        
        panel.add(top, BorderLayout.NORTH);

        accomOutput = new JTextArea();
        accomOutput.setEditable(false);
        panel.add(new JScrollPane(accomOutput), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createCustomersTab() {
        JPanel panel = new JPanel(new BorderLayout(5,5));
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));

        top.add(new JLabel("Name:")); tfName = new JTextField(10); top.add(tfName);
        top.add(new JLabel("Age:")); tfAge = new JTextField(3); top.add(tfAge);
        top.add(new JLabel("Email:")); tfEmail = new JTextField(15); top.add(tfEmail);
        //no shorter way to do this unfortunately but as u suggested I put them all in one line 
        
        btnAddCustomer = new JButton("Add Customer");
        btnListCustomers = new JButton("Display Customers");
        btnAddCustomer.setActionCommand("addCustomer");
        btnListCustomers.setActionCommand("listCustomers");
        btnAddCustomer.addActionListener(this);
        btnListCustomers.addActionListener(this);
        top.add(btnAddCustomer);
        top.add(btnListCustomers);

        panel.add(top, BorderLayout.NORTH);
        customerOutput = new JTextArea();
        customerOutput.setEditable(false);
        panel.add(new JScrollPane(customerOutput), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createPackagesTab() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
        //TECHNICALLY all this code can be condensed into a loop and list cause of repeat code but this was easier for debugging. 
        btnCreatePackage = new JButton("Create Package");
        btnListPackages = new JButton("Display Packages");
        btnAddTaxiPass = new JButton("Add Taxi Pass");
        btnAddScenicFlight = new JButton("Add Scenic Flight");
        btnSavePackages = new JButton("Save Packages");
        btnLoadPackages = new JButton("Load Packages");

        btnCreatePackage.setActionCommand("createPackage");
        btnListPackages.setActionCommand("listPackages");
        btnAddTaxiPass.setActionCommand("addTaxiPass");
        btnAddScenicFlight.setActionCommand("addScenicFlight");
        btnSavePackages.setActionCommand("savePackages");
        btnLoadPackages.setActionCommand("loadPackages");
        //all the buttons for the packages tab + when u click it it'll action according to the function 

        btnCreatePackage.addActionListener(this);
        btnListPackages.addActionListener(this);
        btnAddTaxiPass.addActionListener(this);
        btnAddScenicFlight.addActionListener(this);
        btnSavePackages.addActionListener(this);
        btnLoadPackages.addActionListener(this);

        panel.add(btnCreatePackage);
        panel.add(btnListPackages);
        panel.add(btnAddTaxiPass);
        panel.add(btnAddScenicFlight);
        panel.add(btnSavePackages);
        panel.add(btnLoadPackages);
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	//when clicked, it'll perform the functions depending on what was clicked. 
        switch (e.getActionCommand()) {
            // Accommodation
            case "displayAll":
                accomOutput.setText("");
                //clear content
                travel.getAccom().forEach(a -> accomOutput.append(a + "\n\n"));
                //lambda version of for (Accommodation a: travel.getAccom()) print out text 
                break;
            case "displayAvailable":
                accomOutput.setText("");
                
                for (Accommodation a : travel.getAccom()) {
                    if (a.getAr() > 0) {
                        accomOutput.append(a + "\n\n");
                    }
                }

                break;

            // Customers
            case "addCustomer":
                try {
                    String name = tfName.getText().trim();
                    if (name.length() <= 1) {
                    	throw new IllegalArgumentException("Please enter a name");
                    }
                    int age = Integer.parseInt(tfAge.getText().trim());
                    if (age < 0) {
                    	throw new NumberFormatException();
                    }
                    
                    String email = tfEmail.getText().trim();
                    
                    if (!email.contains("@") || email.length() < 3) { 
                    	throw new IllegalArgumentException("Invalid email, please enter a proper one.");              
                    }
                    
                    travel.getCustomers().add(new Customer(name, age, email));
                    customerOutput.append("Added: " + name + "\n");
                    //when we've added a new customer the text will be cleared, back to empty. 
                    tfName.setText(""); tfAge.setText(""); tfEmail.setText("");
                    
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this,"Please enter a non-negative integer for age.");
                    
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
                
                break;
                
                
            case "listCustomers":
                customerOutput.setText("");
                travel.getCustomers().forEach(c -> customerOutput.append(c + "\n"));
                //lambda expression for loop again. 
                break;

            // if people click on certain boxes in packages it'll allow them to see the dialog or text form. 
            case "createPackage":
                new CreatePackageDialog(this, travel).setVisible(true);
                break;
                
            case "listPackages":
                new DisplayPackagesDialog(this, travel.getPackages()).setVisible(true);
                break;
                
            case "addTaxiPass":
                new TaxiPassDialog(this, travel.getPackages()).setVisible(true);
                break;
                
            case "addScenicFlight":
                new ScenicFlightDialog(this, travel.getPackages(), flights).setVisible(true);
                break;
                
                
                //saving and loading packages 
            case "savePackages":
                try {
                    travel.savePackagesToFile();
                    JOptionPane.showMessageDialog(this, "Packages saved to file.");
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error saving packages: " + ex.getMessage());
                }
                
                break;
                
                
            case "loadPackages":
                try {
                    travel.readPackagesFromFile(); 
                    JOptionPane.showMessageDialog(this, "Packages loaded from file.");
                }
                
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error loading packages: " + ex.getMessage());
                }
                
                break;
        }
    }

    public static void main(String[] args) {
    	
        SwingUtilities.invokeLater(() -> {
			try {
				new TravelGUI();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
    }
}

// DIALOG CLASSES



// Customer
class CustomerDialog extends JDialog {
    public CustomerDialog(Frame owner, List<Customer> customers) {
        super(owner,"Create Customer",true);
        //constructor of JDialog takes 3 parameters 
        setLayout(new GridLayout(4,2,10,10));
        //4 rows, 2 columns and 10 px gaps 
        JTextField tfN = new JTextField(), tfA = new JTextField(), tfE = new JTextField();
        //initialising the textfields 
        
        add(new JLabel("Name:")); add(tfN);
        add(new JLabel("Age:")); add(tfA);
        add(new JLabel("Email:")); add(tfE);
        
        
        JButton btn = new JButton("Add");
        //lambda action listener for add 
        btn.addActionListener(ev->{
            try {
                String name = tfN.getText().trim();                             
                int age = Integer.parseInt(tfA.getText().trim());                
                String email = tfE.getText().trim();
                
                customers.add(new Customer(name, age, email));
                dispose();
                
            } catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,"Enter a non-negative integer for age.");
                
            } catch(IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this,ex.getMessage());
            }
            catch (Exception e) {
            	JOptionPane.showMessageDialog(this, e);
            }
            //tries to get the variables, if they're invalid (i,e someone entered -1 as their age) then it'll prompt them to try again. 
        });
        add(btn);
        pack(); 
        setLocationRelativeTo(owner);
    }
}

//Display Customers
class DisplayCustomersDialog extends JDialog {
    public DisplayCustomersDialog(Frame owner, List<Customer> customers) {
        super(owner,"Customers List",true);
        JTextArea area = new JTextArea(); area.setEditable(false);
        customers.forEach(c->area.append(c+"\n"));
        //lambda for loop 
        
        add(new JScrollPane(area));
        setSize(400,300);
        setLocationRelativeTo(owner);
    }
}

// Create Package
class CreatePackageDialog extends JDialog {
    public CreatePackageDialog(Frame owner, WesternSafariTravel travel) {
        super(owner,"Create Package",true);
        setLayout(new GridLayout(5,2,10,10));
        
        //you can technically downsize it by creating a helper function for this since it is also used to display accom (although i'd need to change that up a bit) but as of right now it's fine. 
        List<Accommodation> availableAccom = new ArrayList<>();        
        for (Accommodation a : travel.getAccom()) {
            if (a.getAr() > 0) {
                availableAccom.add(a);
            }
        }        
        
        
        JComboBox<Accommodation> cbA = new JComboBox<>();
        for (Accommodation a : availableAccom) {
            cbA.addItem(a);
        }
        //gets the available accommodations then presents them in a jcombo box so people can choose which one. 
        
        	
        cbA.setPreferredSize(new Dimension(150,25));
        cbA.setMaximumRowCount(5);
        add(new JLabel("Accommodation:")); 
        add(cbA);

        JList<Customer> listC = new JList<>(travel.getCustomers().toArray(new Customer[0]));
        //almost same as last code except its done on one line except youd nbeed to create empty list model for same code. 
        listC.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        //presents a list with multiple selections, just hold down ctrl and select for multiple. 
        add(new JLabel("Customers:")); 
        add(new JScrollPane(listC));

        add(new JLabel("Start Date:"));
        JSpinner spD = new JSpinner(new SpinnerDateModel());
        //adds up and down arrows 
        spD.setEditor(new JSpinner.DateEditor(spD,"dd/MM/yyyy"));
        add(spD);
        //even if u try to enter like 400 in the days section, it automatically increments the time by 400 days which i think is pretty neat. 

        add(new JLabel("Duration:")); 
        JTextField tfDays = new JTextField();
        add(tfDays);

        JButton btn=new JButton("Create");
        btn.addActionListener(ev->{
            try {
                Accommodation a = (Accommodation)cbA.getSelectedItem();
                Date d = (Date)spD.getValue();
                //takes the object the user selects from the jcombo box and casts it to type (Accommodation) or (Date) 
                
                Date today = new Date();  
                //today 
                if (d.before(today)) {
                    throw new IllegalArgumentException("Please choose a start date that in the future (eg. tomorrow)");
                }
                //if user enters a date in the past. 

                int days = Integer.parseInt(tfDays.getText().trim());
                
                if(days < 0) {
                	throw new IllegalArgumentException("Please enter a positive integer for the duration");
                }
                
                //tp gets created, accommodation chosen gets -= 1 in available rooms 
                TravelPackage tp = new TravelPackage();
                tp.addDate(d);
                tp.addDuration(days);
                tp.addAccommodation(a, a.getppN()*days);
                a.setAR(a.getAr()-1);
                
                //if they dont select anyone 
                if (listC.isSelectionEmpty()) {
                	throw new IllegalArgumentException("Please select a customer");
                }
                //for each customer selected, add that customer to the travel package.
                for(Customer c : listC.getSelectedValuesList()) {
                	tp.addCustomer(c);
                }
                
                //adds that package to the travel safari package list 
                travel.getPackages().add(tp);
                new ExtrasDialog((Frame)SwingUtilities.getWindowAncestor(this), tp, travel.getFlights(), travel).setVisible(true);
                dispose();
                
                
            } 
            catch(NumberFormatException ex) {
            	JOptionPane.showMessageDialog(this, "Please enter a number for duration.");
            }
            
            catch(IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex);
            } 
            
            catch(Exception ex) {
                JOptionPane.showMessageDialog(this,"Invalid package data.");
            }
        });
        
        add(btn);

        pack(); 
        setLocationRelativeTo(owner);
    }
}





class ExtrasDialog extends JDialog {
    public ExtrasDialog(Frame owner,TravelPackage pkg, List<String> flightOptions,  WesternSafariTravel travel) {
        super(owner, "Add Extras?", true);
        setLayout(new GridLayout(3, 1, 10, 10));

        // Choices to add taxi pass or scenic flight
        JCheckBox cbTaxi = new JCheckBox("Add Taxi Pass");
        JCheckBox cbFlight = new JCheckBox("Add Scenic Flight");
        add(cbTaxi);
        add(cbFlight);

        // Ok button
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnOk = new JButton("OK");
        buttons.add(btnOk);
        add(buttons);


        // “OK” applies whichever extras are checked
        btnOk.addActionListener(e -> {
            if (cbTaxi.isSelected()) {
                List<TravelPackage> single = new ArrayList<>();
                single.add(pkg);
                new TaxiPassDialog(owner, single).setVisible(true);

            }
            if (cbFlight.isSelected()) {
                List<TravelPackage> single = new ArrayList<>();
                single.add(pkg);
                new ScenicFlightDialog(owner, single,flightOptions).setVisible(true);
            }
            JOptionPane.showMessageDialog(this, "Successfully added " + travel.getPackages().get(travel.getPackages().size()-1)); 
            dispose();
        });

        pack();          
        setLocationRelativeTo(owner);
    }
}


class DisplayPackagesDialog extends JDialog {
    public DisplayPackagesDialog(Frame owner, List<TravelPackage> pkgs) {
        super(owner,"Packages List",true);
        JTextArea area = new JTextArea(); area.setEditable(false);
        pkgs.forEach(p->area.append(p+"\n----------------\n"));
        add(new JScrollPane(area));
        setSize(500,400);
        setLocationRelativeTo(owner);
    }
}

class TaxiPassDialog extends JDialog {
    public TaxiPassDialog(Frame owner, List<TravelPackage> pkgs) {
        super(owner,"Add Taxi Pass",true);
        
        setLayout(new GridLayout(3,2,10,10));
        
        JComboBox<TravelPackage> cbP = new JComboBox<>(pkgs.toArray(new TravelPackage[0]));
        add(new JLabel("Select Package:")); add(cbP);

        JSpinner spDays = new JSpinner(new SpinnerNumberModel(1, 1, 365, 1));
        add(new JLabel("Days:")); add(spDays);

        JButton btn = new JButton("Add");
        btn.addActionListener(ev->{
            TravelPackage tp = (TravelPackage)cbP.getSelectedItem();
            int days = (Integer)spDays.getValue();
            
            if (tp.getTPLength() > 0) {
            	tp.setTotal(tp.getTotalCost() - tp.BuyTaxiPass(tp.getTPLength(), false));
            
            }
            if (days > tp.getDuration()) {
                String msg = "Warning: the duration you have entered for taxi pass is longer than your vacation duration (" + tp.getDuration() + " days)";
                Object[] options = {"Continue", "Exit"};

                int choice = JOptionPane.showOptionDialog(this, msg, "Duration Exceeded", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);


                if (choice == JOptionPane.NO_OPTION) {
                    dispose();  
                    return;
                } 
            	
            }
            double cost = tp.BuyTaxiPass(days, true);
            JOptionPane.showMessageDialog(this, "Added taxi pass to package #" + tp.getID() + ": $" + cost);
            dispose();
        });
        
        add(btn);

        setSize(900,400);
        setLocationRelativeTo(owner);
    }
}

class ScenicFlightDialog extends JDialog {
    public ScenicFlightDialog(Frame owner, List<TravelPackage> pkgs, List<String> flights) {
        super(owner,"Add Scenic Flight",true);
        setLayout(new GridLayout(3,2,10,10));
        
        //boxes to select package + the flight they wanna book 
        JComboBox<TravelPackage> cbP = new JComboBox<>(pkgs.toArray(new TravelPackage[0]));        
        //populates the jcombobox with the objects within the list. 
        add(new JLabel("Select Package:")); add(cbP);
        
        
        JComboBox<String> cbF = new JComboBox<>(flights.toArray(new String[0]));
        add(new JLabel("Select Flight:")); add(cbF);
        
        JButton btn = new JButton("Add");
        btn.addActionListener(ev->{
            TravelPackage tp = (TravelPackage)cbP.getSelectedItem();
            String flight = (String)cbF.getSelectedItem();
            double cost = 0;
            if (tp.getScenicFlights().contains(flight)) {
                String msg = "Warning: the flight you have selected has already been booked, would you like to continue?";
                Object[] options = {"Continue", "Exit"};
                int choice = JOptionPane.showOptionDialog(this, msg, "Flight already booked", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);

                if (choice == JOptionPane.NO_OPTION) {
                    dispose();  
                    return;
                } 
        
            }
            
            //getting the cost depending on how many customers there are 
            for(Customer c:tp.getCustomers()) {
            	cost += c.getFlightCost();
            }
            
            tp.addScenicFlights(flight,cost);
            JOptionPane.showMessageDialog(this, "Added scenic flight '" + flight + "' to package #" + tp.getID() + ": $" + cost);
            dispose();
        });
        add(btn);

        setSize(900,400);
        setLocationRelativeTo(owner);
    }
}


