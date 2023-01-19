package test;

import Service.CustomerService;

public class Driver {
    public static void main(String[] args) {
//        Customer cust = new Customer("Anamika", "Gupta", "ana.gupta@gamil.com");
//        System.out.println(cust.toString());

        try {
            CustomerService.addCustomer("A", "B", "a.b@gmail.com");
            CustomerService.addCustomer("A", "B", "a.c@gmail.com");
            CustomerService.addCustomer("A", "B", "a.d@gmail.com");
            System.out.println(CustomerService.getAllCustomers().toString());
            System.out.println(CustomerService.getCustomer("a.c@gmail.com").toString());

        } catch (Exception ex) {

        }



    }
}
