package main;

import java.text.SimpleDateFormat; 
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import api.HotelResource;
import model.Customer;
import model.IRoom;
public class MainMenu {
	static private Scanner scanner = new Scanner(System.in);
	static private HotelResource resource = new HotelResource();
	static private AdminMenu admin = new AdminMenu();
	
	
	 public static void main(String[] args) {
		 MainMenu menu = new MainMenu();
		 menu.handleRequest();
	 }
	
	
	public void handleRequest() {
			int choice = 0;
	        System.out.println("1. Find and reserve a room");
	        System.out.println("2. See my reservations");
	        System.out.println("3. Create an account");
	        System.out.println("4. Admin");
	        System.out.println("5. Exit");
	        try {
	        choice = Integer.parseInt(scanner.nextLine()); 
	        }catch(Exception e) {
	        }
	        switch (choice) {
	            case 1:
	            	findAndReserveRoom();
	                break;
	            case 2:
	            	seeMyReservation();
	                break;
	            case 3:
	            	createUserAccount();
	                break;
	            case 4:
	                admin.handleRequest(); 
	                break;
	            case 5:
	            	System.exit(0);
	            	break;
	            default:
	                System.out.println("Invalid selection. Please select an option between 1 and 5.");
	                break;
	        }
	        handleRequest();
	
	    }
	
	
	
	public void seeMyReservation() {
		System.out.println("Please enter your email address:");
		String email = scanner.nextLine();
		System.out.println(resource.getCustomersReservations(email)); 
	}
	
	 public void createUserAccount() {
				System.out.println("Please enter your first name:");
				String firstName = scanner.nextLine();
				System.out.println("Please enter your last name:");
				String lastName = scanner.nextLine();
				boolean validEmail = false;
				while(!validEmail) {
				try {
				System.out.println("Please enter your email address:");
				String email = scanner.nextLine();
				resource.createACustomer(email, firstName, lastName);
				System.out.println("Thank you for creating an account, " + firstName + " " + lastName + "!");
				System.out.println("Your email address is " + email + ".");
				validEmail = true;
				}catch(Exception e) {
					System.out.println("Invalid email format!");
				}
				}
	    }
	 
	 public void findAndReserveRoom() {
	        
	        // Get initial and final date of booking
		 	List<Date> dates = getDates();
	        
	        // Display available rooms
	        Collection<IRoom> availableRooms = resource.findARoom(dates.get(0), dates.get(1));
	        if (availableRooms.isEmpty()) {
	            System.out.println("No rooms available for the selected dates.");
	            return;
	        }
	        System.out.println("Available rooms:");
	        for (IRoom room : availableRooms) {
	            System.out.println(room.toString());
	        }
	        
	        // Check if customer has an account
	        System.out.print("Do you have an account with us? (y/n): ");
	        String answer = scanner.nextLine();
	        if (answer.equalsIgnoreCase("y")) {
	            // If customer has an account, book the room of choice
	            System.out.print("Enter your email address: ");
	            String email = scanner.nextLine();
	            Customer customer = resource.getCustomer(email); 
	            if (customer == null) {
	                System.out.println("Customer with email " + email + " not found.");
	                return;
	            }
	            System.out.print("Enter the room number you want to book: ");
	            int roomNumber = scanner.nextInt();
	            scanner.nextLine();
	            IRoom room = resource.getRoom(String.valueOf(roomNumber));
	            if (room == null) {
	                System.out.println("Room with number " + roomNumber + " not found.");
	                return;
	            }
	            resource.bookARoom(email, room, dates.get(0), dates.get(1)); 
	            System.out.println("Room " + roomNumber + " has been booked by " + email);
	        } else if (answer.equalsIgnoreCase("n")) {
	            // If customer does not have an account, ask if they want to create one
	            System.out.print("Would you like to create an account? (y/n): ");
	            answer = scanner.nextLine();
	            if (answer.equalsIgnoreCase("y")) {
	                // Lead the customer to account creation process
	                createUserAccount();
	            } else {
	                System.out.println("Booking cancelled.");
	            }
	        } else {
	            System.out.println("Invalid input.");
	        }
	    }
	 
	 
	 public List<Date> getDates() {
		 	List<Date> dates = new ArrayList<>();
	        Calendar c1 = Calendar.getInstance();  
	        Date today = c1.getTime();
	        // Get initial date
	        Date initialDate = null;
	        while (initialDate == null) {
	            System.out.print("Enter initial date (yyyy-MM-dd): ");
	            String initialDateString = scanner.nextLine();
	            try {
	                initialDate = new SimpleDateFormat("yyyy-MM-dd").parse(initialDateString);
	            } catch (Exception e) {
	                System.out.println("Invalid date format. Please enter a date in yyyy-MM-dd format.");
	            }
	            if (initialDate != null && initialDate.before(today)) {
	                System.out.println("Initial date cannot be before today's date.");
	                initialDate = null;
	            }
	        }
	        dates.add(initialDate);
	        
	        // Get final date
	        Date finalDate = null;
	        while (finalDate == null) {
	            System.out.print("Enter final date (yyyy-MM-dd): ");
	            String finalDateString = scanner.nextLine();
	            try {
	                finalDate = new SimpleDateFormat("yyyy-MM-dd").parse(finalDateString);
	            } catch (Exception e) {
	                System.out.println("Invalid date format. Please enter a date in yyyy-MM-dd format.");
	            }
	            if (finalDate != null && finalDate.before(initialDate)) {
	                System.out.println("Final date cannot be before initial date.");
	                finalDate = null;
	            }
	        }
	        dates.add(finalDate);
	        
	        return dates;
	    }
	 
}
