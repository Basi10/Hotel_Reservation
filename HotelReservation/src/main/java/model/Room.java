package model;

public class Room implements IRoom {
	
	private String roomNumber;
	private Double price;
	private RoomType enumeration;
	
	

	public Room(String roomNumber, Double price, RoomType enumeration) {
		super();
		this.roomNumber = roomNumber;
		this.price = price;
		this.enumeration = enumeration;
	}

	@Override
	public String getRoomNumber() {
		return this.roomNumber;
	}

	@Override
	public Double getRoomPrice() {
		return this.price;
	}

	@Override
	public RoomType getRoomType() {
		return this.enumeration;
	}

	@Override
	public boolean isFree() {
		return false;
	}

	@Override
	public String toString() {
		return "RoomNumber: " + roomNumber + ", Price: " + price + ", Room Type: " + enumeration + "]";
	}
	
	

	
}
