package assignment1;

import java.io.Serializable;

public class Customer implements Serializable {
	
    private static final long serialVersionUID = 1L;

	
	private String name;
	private int age;
	private String email;
	private int id;
	private static int nextID = 1;
	
	
	
	//constructors
	public Customer() {	
		this.id = nextID++;
	}
	
    public Customer(String name, int age, String email) {
        this.id = nextID++;
        this.name = name;
        this.age = age;
        this.email = email;
    }

	
	
	
    //getters
    public int getID() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getCustomerType() {
    	if (age < 12) {
    		return "Child";
    	}
    	
    	else if (age <= 65) {
    		return "Adult";
    	}
    	
    	else {
    		return "Senior";
    	}
    }
    
    
    
    //setters   
    public void setName(String name) {
    	this.name = name;
    }
    
    public void setAge(int age) {
    	this.age = age;
    }
    
    public void setEmail(String email) {
    	this.email = email;
    }
    
	public static void setNextID(int id) {
		nextID = id;
	}
	
    
    
    //flight prices depending on type 
    public double getFlightCost() {
        if (age < 12) {
            return 300.0;
        } 
        
        else if (age <= 65) {
            return 450.0;
        } 
        
        else {
            return 400.0;
        }
    }
    
    
    public String toString() {
    	//go to accommodation tostring for explanation on ID printout 
        return "Customer ID: " + String.format("%05d", id) + " \nName: " + name + " \nAge: " + age + " (" + this.getCustomerType() + ") " + "\nEmail: " + email + "\n";
    }

}
