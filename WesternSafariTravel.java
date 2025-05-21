package assignment1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class WesternSafariTravel {
	
	private ArrayList<Accommodation> accom;
	private ArrayList<Customer> customers;
	private ArrayList<TravelPackage> packages;
	private ArrayList<String> flights;
	private Scanner imp;
	
	
	public WesternSafariTravel() {
		accom = new ArrayList<>();
		customers = new ArrayList<>();
		packages = new ArrayList<>();
		flights = new ArrayList<>();
		imp = new Scanner(System.in);
		//this.data();
		//to reset the data back to its initial state just uncomment this.data() and remove the try command block underneath int userInp in WesternSafariAdmin 
		
	}
	
	public void populateFlights() {
		flights.add("Miami");
		flights.add("Melbourne");
		flights.add("Munich");
		flights.add("Tokyo");

	}
	
	public void data() {
		//idk if you wanted this in main or in the package but I can do either or however this seems more efficient
		accom.add(new Accommodation("Hotel Marriott",349.99,"Hotel",3));
		accom.add(new Accommodation("Safari Lodge",255.45,"Lodge",1));
		accom.add(new Accommodation("Marias Hostel",59.99,"Hostel",1));
		accom.add(new Accommodation("Deer hunters lodge",222.50,"Lodge",3));
		accom.add(new Accommodation("The Grand Meridian",455.50,"Hotel",1));
		accom.add(new Accommodation("Poppys Capsule Hotels",50.79,"Hotel",1));
		accom.add(new Accommodation("Stark Motel",125.99,"Motel",1));
		accom.add(new Accommodation("Ground Zero",233.15,"Hostel",1));
		accom.add(new Accommodation("P Hotel",260.99,"Hotel",1));
		accom.add(new Accommodation("Zone Motel",129.99,"Motel",1));
		
		
		
		
		customers.add(new Customer("Nikki",19,"nikki@gmail.com"));
		customers.add(new Customer("Caleb",24,"caleb@gmail.com"));
		customers.add(new Customer("Alex",80,"alex@gmail.com"));
		customers.add(new Customer("Kali",11,"Kali@gmail.com"));
		

		
		//initial list of data before it was saved to files 
		
		
		






		
		
	}
	
	//getters
	public ArrayList<TravelPackage> getPackages(){
		return packages;
	}
	
	public ArrayList<Accommodation> getAccom(){
		return accom;
	}
	
	public ArrayList<Customer> getCustomers(){
		return customers;
	}
	
	public ArrayList<String> getFlights(){
		return flights;
	}
	
	
	//printouts 
	public void displayAccom() {
		System.out.println("\n--------------------------\nAll accommodations in this area:");
		for (Accommodation a: accom) {
			System.out.println(a.getName());
		}
		System.out.println("-------------------------------");
	}
	
	public void displayAvailable() {
		boolean accomA = false;
		//initialising the accommodations available variable to false at first
		
		System.out.println("\n----------------------------\nAll available accommodations: ");
		for (Accommodation a: accom) {
			if (a.getAr() >= 1) {
				System.out.println("Hotel name: " + a.getName() + "\nAvailable rooms: " + a.getAr() + "\n---");
				accomA = true;
				//if theres at least 1 available room across all lists then its true, maybe inefficient but i want to add another printout if there isnt any hotels available
			}
		}
		if (accomA == false) {
			System.out.println("No accommodations available at the moment, please check back in later!");
		}
	}
	
	public void addCustomer() {
		try {
			System.out.println("Enter name: ");
			String name = imp.nextLine().trim();
			//trim removes whitespaces from both sides of the string so it ensures that if we want to find that customer name for example there wont be any confusion in case someone added a whitespace by accident
			
			int age = -1;
			
			while (age < 0) {
				System.out.println("Enter age: ");
				try {
					age = imp.nextInt();
					imp.nextLine();
					if (age < 0) {
						System.out.println("Please enter their true age, cannot be below 0");
						
					}
					
				}
				catch (InputMismatchException e) {
					System.out.println("Please enter a number.");
				}
			}
			String email;
			
			while (true) {
				if (age >= 12) {
					System.out.println("Enter email: ");
					email = imp.next().trim();
					imp.nextLine();
					
					if (!email.contains("@")) {
						System.out.println("Please enter a proper email!");
						
					}
					
					else {
						break;
					}
				}
				
				else {
					email = "Child, no email";
					break;
				}	
			}	
			//if customer is a child then they dont have to provide their email. 
			
			
			customers.add(new Customer(name,age,email));
			
			//gets our customers name. 
			System.out.println("Customer " + customers.get(customers.size()-1).getName() + " has been added to the system." );
		} catch (Exception e) {
			System.out.println("Error, please try again");
		}
	}
	
	public void displayCustomers() {
		
		//if the customer list is empty, it prints that out and returns us to the main menu to avoid getting an error for the for loop.
		if (customers.isEmpty()) {
			System.out.println("No customers in the system yet.");
			return;
		}
		
	
		for (Customer c: customers) {
			System.out.println(c);
		}
		
		
	}
	
	public void addPackage() {
		int num;
		Customer customer;
		TravelPackage newTP = new TravelPackage();
		int total = 0;
		double holderN;
		//holder number
		
		while (true) {
			
			try {
				System.out.println("How many people are you looking to book this trip for? ");
				num = imp.nextInt();
				imp.nextLine();
	
				if (num == 1) {						
					//gets customer's id then adds them to the travel package's list of customers 
					customer = checkID(customers, "Customer");
					newTP.addCustomer(customer);
					break;
				
					
					
					
				}
				
				else if (num == -1) {
					return;
				}
				
				else if (num <= 0) {
					System.out.println("Please enter a number higher than 0");
				}
				
				else {
					for (int i = 0; i < num ; i++) {
						customer = checkID(customers, "Customer");
						newTP.addCustomer(customer);
						//prompts the user to enter all the customer's ids and adds them to the TP list of customers 
						
				
						
					}
					break;
				}
		} catch (Exception e) {
			System.out.println("Please enter how many people you're looking to book this trip for in integers. -1 to exit");
			imp.nextLine();
		}
			
		}
		
        Date startDate = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        //so that when someone enters something such as a number higher than 12 in months, then it throws an exception. 
        while (startDate == null) {
            System.out.print("Enter the date you'd like to travel (dd/MM/yyyy): ");
            String dateString = imp.nextLine().trim();
            
            try {
                startDate = dateFormat.parse(dateString);
                
                
                //if the startdate is in the past then it throws an illegal argument exception and tells the person to enter one in the future. 
                if (startDate.before(new Date())){
                	throw new IllegalArgumentException("Date has passed.");
                }
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use dd/MM/yyyy.");
            }
            
            catch (IllegalArgumentException e) {
            	System.out.println("This date has passed, please enter a date in the future.");
            	startDate = null;
            	
            }
        }
        
        int duration = 0;
        while (duration <= 0) {
            System.out.print("Enter the duration of your trip (in days): ");
            try {
                duration = imp.nextInt();
                imp.nextLine(); 
                
                if (duration <= 0) {
                    System.out.println("The duration must be positive, please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                imp.nextLine(); // clear invalid input
            }
        }
        
        newTP.addDate(startDate);
        newTP.addDuration(duration);
        
        Scanner scanner = new Scanner(System.in);
    
        
		System.out.println("What type of accommodation would you like to book? (Hotel, Hostel, Lodge, Motel)");
		ArrayList<Accommodation> rooms = new ArrayList<Accommodation>();
		//make first letter capital
		String answ = "";
		
		while (rooms.isEmpty()) {
			answ = scanner.next();
			answ = answ.substring(0,1).toUpperCase() + answ.substring(1).toLowerCase();
			for (Accommodation a : accom) {
				if (a.getType().equals(answ) && a.getAr() > 0) {
					rooms.add(a);
				}
			}
			
			if (answ.equals("-1")) {
				return;
			}
			
			//for accommodations in accom list if the type is the same as the answer given by the user and its available then it'll be added to rooms list, if rooms list is empty
			//aka no rooms are available, then it asks the user to try again with a different accommodation or -1 to exit. 
			if (rooms.isEmpty()) {
				System.out.println("There are no available rooms for " + answ + " press any number to pick a new type of accommodation,  -1 to exit");
				scanner.nextLine();
				
			}
		}
		
		System.out.println("================" + answ + "s available to book: ================");
		for (Accommodation a : rooms) {
			System.out.println(a);
		}
		
		
		Accommodation accomm = checkID(accom,"Accommodation");
		if (accomm == null) {
			return;
		}
		//if its null, then return to menu 
		
		holderN = accomm.getppN()*duration;
		newTP.addAccommodation(accomm, holderN);
		accomm.setAR(accomm.getAr() - 1);
		total += holderN;
		System.out.println("Total for " + duration + " days at " + accomm.getName() + ": $" + holderN);
		//adds accommodation to the new travel package and decreases the available rooms by 1 
		packages.add(newTP);
				
		
		
	}
	
	public void displayPackages() {
		if (packages.isEmpty()) {
			System.out.println("No current packages in the system yet.");
			return;
		}
		
		for (TravelPackage tp : packages) {
			System.out.println("-------------------------");
			System.out.println(tp);
		
		}
	}
	
	
	//add functions
	
	public void addTaxiPass() {
		int days = 0;	
		//object m = checkID function which asks for the ID then checks if it exists 
		TravelPackage m = checkID(packages, "travelPackage");
		//if it doesnt exist and the customer decides to exit the checkID, then we send them back to the main menu. 
		if (m == null) {
			return;
		}
		
		
		//while days is below or equal to 0, we ask how many days theyd like to book a taxi pass, if they enter a number below or equal to zero, they get that msg and forced to repeat loop.
		while (days <= 0 ) {
			System.out.println("Enter amount of days you'd like to book a taxi pass: ");
			days = imp.nextInt();
			
			if (days <= 0) {
				System.out.println("Please enter a positive integer.");
			}
			
			
		}
		
		double amount = m.BuyTaxiPass(days, true);
		//for total. 

		System.out.println("Your total for " + days + " days of Taxi Pass is: $" + amount );								
		
	}
	
	public void addScenicFlight() {
		String scenicF;
		double total = 0;
		
		//check comment in addTaxiPass() for explanation 
		TravelPackage m = checkID(packages, "travelPackage");
		if (m == null) {
			return;
		}
		
		System.out.println("Enter the name of the scenic flight you'd like to take: ");
		scenicF = imp.next();
		imp.nextLine();
		System.out.println("For a scenic flight to " + scenicF + ": ");
		
		//prints out the prices depending on how many customers are in the travel package then shows the total 
		for (Customer c : m.getCustomers()) {
			System.out.println(c.getCustomerType() + ": $" + c.getFlightCost());
			total += c.getFlightCost();
		}
		m.addScenicFlights(scenicF, total);

		
		System.out.println("-----------\nTotal: $" + total);
		
		
		
		
		
		
	}
	
	
	
    public void savePackagesToFile() {
    	try {
			addListToFile(packages,"travelPackages.dat");
			addListToFile(accom,"accommodations.dat");
			addListToFile(customers,"customers.dat");
			//I know you wanted me to save only travelpackages however I feel as though this would be more practical plus if a customer were to book out an accommodation
			//the available room numbr would stay the same instead of resetting every time. 
			//for this same reason I've decided to run the functions savePackageToFile whenever someone quits the menu, and to read packages from file whenever someone starts up the program. 
			
			System.out.println("Packages successfully saved");
		} catch (IOException e) {
			System.out.println("An error occurred when trying to save the package.");
			e.printStackTrace();
		}
    	
    }
    
    // 10. Read packages from file
    public void readPackagesFromFile() throws ClassNotFoundException, IOException {
    	packages = readListFromFile(packages,"travelPackages.dat");
    	if (!packages.isEmpty()) {
    		//if there are travel packages then set the nextID to the highest ID in the travel package list and + 1 
        	TravelPackage.setNextID(packages.size()+1);
        
    	}
    	
    	for (TravelPackage tp : packages) {
    		System.out.println(tp);
    	}
    	
    
    	
    	
    }

	public void importPackages() throws ClassNotFoundException, IOException {
    	packages = readListFromFile(packages,"travelPackages.dat");
    	if (!packages.isEmpty()) {
    		//if there are travel packages then set the nextID to the highest ID in the travel package list and + 1 
        	TravelPackage.setNextID(packages.size()+1);
        
    	}
    	
    	//reading and appending all the accommodations and customer lists as well 
    	accom = readListFromFile(accom,"accommodations.dat");
    	
    	customers = readListFromFile(customers,"customers.dat");
    	
    	if (!customers.isEmpty()) {
        	Customer.setNextID(customers.size()+1);
    	}
    	
    	//flights = readListFromFile(flights, "flights.dat");
    	
	}
	
	
    //helper methods for saving and getting txt file 
    private <T> void addListToFile(ArrayList<T> objL, String file) throws IOException {
    	FileOutputStream fos = new FileOutputStream(file);
    	ObjectOutputStream oos = new ObjectOutputStream(fos);
    	oos.writeObject(objL);
    	oos.close();

    }
    //basically adds the specified arraylists to file but as a function 
    
   @SuppressWarnings("unchecked")
private <T> ArrayList<T> readListFromFile(ArrayList<T> objL, String file) throws IOException, ClassNotFoundException {
	   FileInputStream fis = new FileInputStream(file);
	   ObjectInputStream ois = new ObjectInputStream(fis);
	   objL = (ArrayList<T>) ois.readObject();
	   ois.close();
	   
	   return objL;
	  

    }
	
	
	//t == placeholder for class  
	private <T> T checkID(ArrayList<T> objL, String objT) {
		//object list, object type 
	    T obj = null;
	    int pd;
	    //holder variables 

	    while (obj == null) {
	    	try {
		        System.out.println("Enter " + objT + " ID (do not include leading zeros): ");
		        //accidentally found this out but apparently you can include the leading zeros however for simplicity's sake im going to keep that message in. 
		        pd = imp.nextInt();
	            imp.nextLine();
		        if (pd == -1) {
		            return obj;
		        }
		        //if user wants to exit then it returns a null object so they can return to menu 
		        
		        for (int i = 0; i < objL.size(); i++) {
		            if (getObjectID(objL.get(i)) == pd) {
		                obj = objL.get(i);
		                break;
		                //calls on the method getobjectid to check if the user input == any of the IDs
		                //if there is, then we get that object from the list and assign obj's value to that object. or obj = object from list 
		                
		            }
		        }
		        
		        if (obj == null) {
		            System.out.println("Please enter a valid ID or -1 to exit");
		            //if we havent found any with that ID
		        }
	   
	        }
	    	
	    	catch (Exception e) {
	    		System.out.println("Please enter a number!");
	    		imp.nextLine();
	    }
	        
	    }
	    return obj;
	}

	// Helper method to get ID from any object
	private <T> int getObjectID(T object) {
	    try {
	        // Use reflection to call getID() on any object
	        Method getID = object.getClass().getMethod("getID");
	        //searchers for the getter method "getID" 
	        
	        return (int) getID.invoke(object);
	        //returns the result of that method in the form of an integer 
	        
	        //for debugging 
	    } catch (Exception e) {
	        e.printStackTrace();
	        return -1;
	    }
	}
	
	
	
	

}
