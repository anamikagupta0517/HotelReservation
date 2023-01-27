package Service;

import model.Customer;

import java.util.*;

public class CustomerService {
    private static Set<Customer> customers = new HashSet<>();

    private static CustomerService customerServiceInstance = null;

    public static CustomerService getInstance(){
        if (customerServiceInstance == null) {
            customerServiceInstance = new CustomerService();
        }
        return customerServiceInstance;
    }

    public static void addCustomer(String firstName, String lastName, String email) throws Exception {
        if (isEmailDuplicate(email)) {
            throw new Exception("Duplicate email exception, " + email + " already exists.");
        } else {
            customers.add(new Customer(firstName, lastName, email));
        }
    }

    public static Customer getCustomer(String email) {
        Customer cust = null;
        for (Customer c : customers) {
            if (c.getEmail().equalsIgnoreCase(email)) {
                cust = c;
                break;
            }
        }
        return cust;
    }

    public static Collection<Customer> getAllCustomers() {
        return customers;
    }


    private static boolean isEmailDuplicate(String email) {
        boolean isDup = false;
        for (Customer cust : customers) {
            if (cust.getEmail().equalsIgnoreCase(email)) {
                isDup = true;
                break;
            }
        }
        return isDup;
    }
}
