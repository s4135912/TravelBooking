This project prompted students to create a functional booking application which allowed the user to book flights, accommodations, and more with javaswing UI. 

I admittedly did not document my thought process throughout the entire thing but I will describe the functions I have implemented:
When opening the application, saved datafiles will automatically update the current arraylists and there is a save option for the packages and more. 
Accommodation has a variable called AR that shows the amount of available rooms, each time that accommodation is booked it'll go down by 1, if the number of available rooms is equal to 0 then it will no longer show up on the available accommodations list.
Available accommodations displays a list of accommodations and the number of available rooms per each accommodation. 
You can add a travel package which includes your accommodation, customers and dates of when you are going and how long you'll be staying. You can book a travel package with multiple customers and afterwards there'll be a popup window asking if you'd like to add any extras to your package, if you select no it'll provide a full receipt including information on which customers are in your package, the places you've booked, the duration of stay, the date, and a total price. 
You can add on a taxi pass which is discounted if you book for your entire trip or book for longer than a week, if you have already booked a taxi pass then booking again will override your previous booking. 
You can add scenic flights to your package, if you've already booked the same location it'll notify you and ask if you would still like to continue, scenic flights are charged based off of the customers' ages.  
Customers can be added into the system, depending on the age the customer will be classed as one of three age groups (child, adult, senior), the customer will also be asked to provide their email, the email will be seen as invalid if the customer does not provide a proper email (less than 3 string length, missing @, etc).
There is a display customers function which'll show a list of every customer in the system.
There is also a display packages function that shows every package in the system, this includes all the necessary info such as bookings, customers, total pricing, etc.



The code has been modified to help with UI readability, although it includes a console version as well. If you wanted to use the console version, you'd need to change the printout layouts a tiny bit. 
