package main;

import java.util.ArrayList; 
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import api.AdminResources;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;
import model.RoomType;

public class AdminMenu {
	static Scanner scanner = new Scanner(System.in);
	static HotelResource resource = new HotelResource();
	static AdminResources admin = new AdminResources();
	static MainMenu menu = new MainMenu();
	
	public void handleRequest() {
	        System.out.println("1. See all Customers");
	        System.out.println("2. See all Rooms");
	        System.out.println("3. See all Reservations");
	        System.out.println("4. Add a Room");
	        System.out.println("5. Back to Main Menu");
	        int choice = 0;
	        try {
	        choice = Integer.parseInt(scanner.next());
	        }catch(Exception e) {
	         System.out.println("Invalid input!");
	         handleRequest();
	        }
	        switch (choice) {
	            case 1:
	            	seeAllCustomers();
	                break;
	            case 2:
	            	seeAllRooms();
	                break;
	            case 3:
	            	seeAllReservation();
	                break;
	            case 4:
	            	addRoom();
	                break;
	            case 5:
	                menu.handleRequest();
	            default:
	                System.out.println("Invalid selection. Please select an option between 1 and 5.");
	                break;
	        }
	        handleRequest();
	   }
	
	
	public void seeAllCustomers() {
		Collection<Customer> allCustomers = admin.getAllCustomers();
		for (Customer customer : allCustomers) {
			System.out.println(customer);
		}
	}

	public void seeAllRooms() {
		Collection<IRoom> rooms = admin.getAllRooms();
		for (IRoom room : rooms) {
			System.out.println(room);
		}
	}

	public void seeAllReservation() {
		Collection<Reservation> customers = admin.displayAllReservations();
		for (Reservation customer : customers) {
			System.out.println(customer);
		}
	}
	
	public Room createRoom() {
		 
			System.out.println("Please enter the room number:");
			int roomNumber = scanner.nextInt();
			System.out.println("Please enter the room price:");
			double roomPrice = scanner.nextDouble();

			RoomType roomType = null;
			boolean validInput = false;
			while (!validInput) {
				System.out.println("Please enter the room type (single or double):");
				String input = scanner.next();

				try {
					roomType = RoomType.valueOf(input.toUpperCase());
					validInput = true;
				} catch (IllegalArgumentException e) {
					System.out.println("Invalid room type. Please enter single or double.");
				}
			}

			return new Room(String.valueOf(roomNumber), roomPrice, roomType);
		
	}
	
    public void addRoom() {
    	
    	List<IRoom> rooms = new ArrayList<>();
    	
   
			String input;

			do {
			    rooms.add(createRoom());
			    System.out.println("Would you like to add a room again? (y/n)");
			    System.out.println(rooms);
			    input = scanner.next();
			    switch (input.toLowerCase()) {
			        case "y":
			            break;
			        case "n":
			        	admin.addRoom(rooms);
			            break;
			        default:
			        	addRoom();
			    }
			} while (input.equals("y"));
		}
    
   
}
