package assignment1;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.SwingUtilities;


public class WesternSafariAdmin {

	public static void main(String[] args) {
		
		WesternSafariTravel p = new WesternSafariTravel();		
		Scanner imp = new Scanner(System.in);
		int userInp = 0;
		//user input + scanner input 
		
		
		try {
			p.importPackages();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
        do {
            try {
                System.out.print("1: Display all accommodations\n" +
"2: Display available accommodations\n" + "3: Add customer\n" + "4: List customers\n" + "5: Create a package\n" + "6: List packages\n" + "7: Add a taxi pass to package\n" + "8: Add a scenic flight to package\n"
                		+ "9: Save packages to file\n" + "10: Read packages from file\n" + "11: quit\n");
                userInp = imp.nextInt();
                imp.nextLine(); 
                
                switch (userInp) {
                    case 1:
                        p.displayAccom();
                        break;
                    case 2:
                        p.displayAvailable();
                        break;
                    case 3:
                        p.addCustomer();
                        break;
                    case 4:
                        p.displayCustomers();
                        break;
                    case 5:
                    	p.addPackage();
                        break;
                    case 6:
                    	p.displayPackages();
                        break;
                    case 7:
                        p.addTaxiPass();
                        break;
                    case 8:
                    	p.addScenicFlight();
                        break;
                    case 9:
                        p.savePackagesToFile();
                        break;
                    case 10:
                        p.readPackagesFromFile();
                        break;
                    case 11:
                    	p.savePackagesToFile();
                        System.out.println("Shutting down....");
                        break;
                    //if the user types in a number that isnt from 1-11
                    default:
                        System.out.println("Please enter a number from the menu.");
                }
                
                if (userInp != 11) {
                	System.out.println("Press enter when you're ready to move on.");
                	imp.nextLine();
                }
                

                //if the user enters something other than a int it catches that exception and redirects the user back to the system page instead of causing it to stop
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                imp.nextLine(); 
                
               //any other errors also does same thing but with error occurred + what ever error it is
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                e.printStackTrace();
                //good for debugging but can remove 
            }
        } while (userInp != 11);
        //do while does the thing first before checking if its true. 





		
		
		

		

	}
}
