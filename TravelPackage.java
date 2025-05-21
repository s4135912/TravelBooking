package assignment1;

import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;
import java.text.SimpleDateFormat;


public class TravelPackage implements Serializable {
	
    private static final long serialVersionUID = 1L;
	
	private Accommodation accommodation;
	private ArrayList<Customer> customers;
	private int ID;
	private static int nextID = 1;
	private int duration;
	//in days 
	private int taxiPassLength;
	//in days
	private ArrayList<String> scenicFlights;
	private Date date;
	private double total;
	
	public TravelPackage() {
		this.ID = nextID++;
		taxiPassLength = 0;
		scenicFlights = new ArrayList<>();
		customers = new ArrayList<>();
		this.total = 0;
	
	}
	
	public TravelPackage(Accommodation accommodation, int duration) {
		this.total = 0;
		this.accommodation = accommodation;
		this.duration = duration;
		this.ID = nextID++;
		taxiPassLength = 0;
		scenicFlights = new ArrayList<>();
		customers = new ArrayList<>();

		
	}
	
	//setters
	
	public static void setNextID(int id) {
		nextID = id;
	}
	
	
	//getters
	
	public int getID() {
		return ID;
	}
	
	public double getTotalCost() {
		return total;
	}
	
	
	//return list of customers 
	public ArrayList<Customer> getCustomers() {
		return customers;
	}
	
	
	//not actually getters but for the string function 
	public String getTaxiPass() {
		if (taxiPassLength > 0) {
			return "\nTaxi Pass: " + taxiPassLength + " days";
		}
		
		return "";
	}
	
	public int getTPLength() {
		return taxiPassLength;
	}
	
	public int getDuration() {
		return duration;
	}
	
	//empty string, if the scenic flights list is not null (empty) then append the string s with every item in scenic flights seperated by , 
	@SuppressWarnings("static-access")
	public String getScenicFlights() {
		String s = "";
		if (!scenicFlights.isEmpty()) {
			s = s.join(", ", scenicFlights);
			return "\nScenic Flights: " + s;
		}
		
		return s;
	}
				
	//for each customer (if only one in list still works), append to string str the customers name and ID. 
	public String printCustomers() {
		String str = "";
		ArrayList<String> printCustomers = new ArrayList<>();
		for (Customer c : customers) {
			str = c.getName() + " (" + String.format("%05d", c.getID()) + ")";
			printCustomers.add(str);
			str = "";
		}
		
		return String.join(", " , printCustomers);
	}
	
	
	
	
	
	//setters (and adders)
	public void setTotal(double total) {
		this.total = total;
	}
	
	public void addDate(Date date) {
		this.date = date;
	}
	
	public void addDuration(int days) {
		this.duration = days;
	}
	
    
    public void addScenicFlights(String flight, double total) {
    	scenicFlights.add(flight);
    	this.total += total;
    	
    }
    
    public void addCustomer(Customer c) {
    	customers.add(c);
    }
	
    public void addAccommodation(Accommodation a, double total) {
    	this.accommodation = a;
    	this.total += total;
    }
	
	
	//functions 
	private double discount(double percentage, double amount) {
		return amount - (percentage/100)*amount;
	}
	//repurposed 
	
	
	
	
	
	public double BuyTaxiPass(int days, boolean f) {
		double tppd = 52;
		double ttl = 0;
		//taxi price per day
		if (days >= 7) {
			ttl = discount(10.0,tppd*days);
			
			if (days >= duration && ttl > 1000 || days >= 30 && ttl > 1000) {
				ttl = 1000.00;
			}
		}
		
		else {
			ttl = tppd * days;
		}
		
		taxiPassLength = days;
		if (f == true) {
			this.total += ttl;
		}
		return ttl;
		
		
	}
	

	
	
	public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return "PackageID: " + ID + " \nCustomer(s): " + printCustomers() + " \nSTART DATE: " + dateFormat.format(date) + " \nAccommodation: " + accommodation.getName() + " (" + String.format("%05d", accommodation.getID()) + ")" + " \nLength of stay: " + duration + " days" + getTaxiPass() + getScenicFlights() + " \nTotal: $" + String.format("%.2f", total);
	}



}
