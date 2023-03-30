package service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import model.Customer;

public class CustomerService {
    static private Map<String, Customer> customers;

    public CustomerService() {
        customers = new HashMap<>();
    }

    public void addCustomer(String email, String firstName, String lastName) {
    	if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            throw new IllegalArgumentException("Invalid email format: " + email);
        }
        this.customers.put(email, new Customer(firstName,lastName,email));  
    }

    public Customer getCustomerEmail(String customerEmail) {
        return this.customers.get(customerEmail);
    }

    public Collection<Customer> getAllCustomers() {
        return this.customers.values();
    } 
}