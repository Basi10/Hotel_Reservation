package api;

import java.util.Collection;
import java.util.List;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

public class AdminResources {
	
	static CustomerService customer = new CustomerService();
	static ReservationService service = new ReservationService();
	
	public Customer getCustomer(String email) {
		return customer.getCustomerEmail(email);
	}
	
	public void addRoom(List<IRoom> rooms) {
		for(IRoom room : rooms) {
			service.addRoom(room); 
		}
	}
	
	public Collection<IRoom> getAllRooms(){
		return service.getAllRooms();
	}
	
	public Collection<Customer> getAllCustomers(){
		return customer.getAllCustomers();
	}
	
	public Collection<Reservation> displayAllReservations() {
		return service.getAllReservations();
	}

}
 