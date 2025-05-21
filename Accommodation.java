package assignment1;

import java.io.Serializable;

public class Accommodation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ID;
	private String name;
	private double ppN;
	//price per night = ppN
	private int ar;
	//ar = available rooms 
	private static int nextID = 1;
	private String type;
	
	//constructors	
	public Accommodation(String name, double ppN, String type) {
		this.ID = nextID++;
		this.name = name;
		this.ppN = ppN;
		this.ar = 1;
		this.type = type;
		
	}
	
	public Accommodation(String name, double ppN, String type, int ar) {
		this.ID = nextID++;
		this.name = name;
		this.ppN = ppN;
		this.ar = ar;
		this.type = type;
	}
	
	//getters
	public String getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAr() {
		return ar;
	}
	public double getppN() {
		return ppN;
	}
	
	public int getID() {
		return ID;
	}
	
	//setters 
	public void setAR(int ar) {
		this.ar = ar;
	}
	
	public void setppN(int ppN) {
		this.ppN = ppN;
	}
	
	public void discount(double percentage) {
		this.ppN -= (percentage/100)*ppN;
	}
	
	
	//toString
	public String toString() {
		return "Name: " + name + " \nHotel ID: " + String.format("%05d", this.ID) +  " \nAvailable Rooms: " + ar;

		//the hotel ID shows formats the integer by adding leading zeros before the number. the 5 specifies the total length of the number so if this.ID became double, triple digits, etc
		//the length would still be 5. You may argue that this is not good for scalability as the number may pass 5 digits however as of right now you can just change the width as it only
		//serves as a printout function. 
	}
	

}
