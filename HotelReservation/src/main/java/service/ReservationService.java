package service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Customer;
import model.IRoom;
import model.Reservation;

	public class ReservationService {
	    static private Map<String, IRoom> rooms; 
	    static private Map<String, Reservation> reservations;
	    
	    public ReservationService() {
	    	rooms = new HashMap<>();
	    	reservations = new HashMap<>();
	    }

	    public void addRoom(IRoom room) {
	        rooms.put(room.getRoomNumber(), room);
	    }

	    public IRoom getRoom(String roomNumber) {
	        return rooms.get(roomNumber);
	    }

	    public Reservation reserveRoom(Customer customer, IRoom roomNumber, Date checkInDate, Date checkOutDate) {
	        IRoom room = rooms.get(roomNumber.getRoomNumber());  
	        if (room == null) {
	            throw new IllegalArgumentException("Invalid room number.");
	        }
	        if (!isAvailable(room,checkInDate, checkOutDate)) {
	            throw new IllegalArgumentException("Room is already booked for the given dates.");
	        }
	        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
	        reservations.put(room.getRoomNumber(), reservation);
	        return reservation;
	    }


	    public Collection<IRoom> findAvailableRooms(Date checkInDate, Date checkOutDate) {
	        Collection<IRoom> availableRooms = new ArrayList<>();
	        for (IRoom room : rooms.values()) {
	            if (isAvailable(room,checkInDate, checkOutDate)) {
	                availableRooms.add(room);
	            }
	        }
	        return availableRooms;
	    }

	    public List<Reservation> getCustomerReservations(Customer customer) {
	        List<Reservation> customerReservations = new ArrayList<>();
	        for (Reservation reservation : reservations.values()) {
	            if (reservation.getCustomer().equals(customer)) {
	                customerReservations.add(reservation);
	            }
	        }
	        return customerReservations;
	    }
	
	
	  public boolean isAvailable(IRoom room, Date checkInDate, Date checkOutDate) {
	        for (Reservation reservation : reservations.values()) {
	            if (reservation.getRoom().equals(room)) {
	                Date reservationCheckIn = reservation.getCheckInDate();
	                Date reservationCheckOut = reservation.getCheckOutDate();
	                if (checkInDate.before(reservationCheckOut) && reservationCheckIn.before(checkOutDate)) {
	                    return false;
	                }
	            }
	        }
	        return true;
	    }
	  
	  public Collection<IRoom> getAllRooms(){
		  return rooms.values();
	  }
	  
	  public Collection<Reservation> getAllReservations(){
		  return reservations.values();
	  }
}


