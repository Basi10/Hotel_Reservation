package api;

import java.util.Collection;
import java.util.Date;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

public class HotelResource {
	
	static CustomerService customer = new CustomerService();
	static ReservationService service = new ReservationService();
	
	public Customer getCustomer(String email) {
		return customer.getCustomerEmail(email);
	}
	
	public void createACustomer(String email, String firstName, String lastName) {
		customer.addCustomer(email, firstName, lastName);
	}
	
	public IRoom getRoom(String roomNumber) {
		return service.getRoom(roomNumber);
	}
	
	public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date CheckOutDate) {
		return service.reserveRoom(customer.getCustomerEmail(customerEmail), room, checkInDate, CheckOutDate);
		
	}
	
	public Collection<Reservation> getCustomersReservations(String customerEmail){
		return service.getCustomerReservations(customer.getCustomerEmail(customerEmail));
	}
	
	public Collection<IRoom> findARoom(Date checkIn, Date checkOut){
		return service.findAvailableRooms(checkIn, checkOut);
	}
	

}
