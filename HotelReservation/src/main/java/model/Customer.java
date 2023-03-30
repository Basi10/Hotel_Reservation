package model;

public class Customer {
	
	String firstName;
	String lastName;
	String email;
	public Customer(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmail() {
		return email;
	}
	@Override
	public String toString() {
		return "FirstName: " + firstName + ", LastName: " + lastName + ", Email: " + email + "]";
	}
	
	

}
