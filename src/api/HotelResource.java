package api;

import Service.CustomerService;
import Service.ReservationService;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class HotelResource {

    public static Customer getCustomer(String customerEmail) {
        return CustomerService.getCustomer(customerEmail);
    }

    public static void createACustomer(String firstName, String lastName, String email) {
        try {
            CustomerService.addCustomer(firstName, lastName, email);
        } catch (Exception ex) {
            System.out.println("Exception occurred while creating customer, as: " + ex.getMessage());
        }
    }

    public static IRoom getRoom(String roomNumber) {
        return ReservationService.getARoom(roomNumber);
    }

    public static Reservation bookARoom(String customerEmail, IRoom room, Date checkinDate, Date checkoutDate) {
        Customer customer = CustomerService.getCustomer(customerEmail);
        Reservation res = null;
        if(customer == null) {
            System.out.println("Cannot find this customer in system. Please add the customer.");
        } else {
            res = ReservationService.reserveARoom(customer, room, checkinDate, checkoutDate);
        }
        return res;
    }

    public static Collection<Reservation> getCustomerReservations(String customerEmail) {
        Customer cust = CustomerService.getCustomer(customerEmail);
        Collection<Reservation> custReservations = new ArrayList<>();
        if(cust == null) {
            System.out.println("Cannot find this customer in system. Please correct customer email.");
        } else {
            custReservations = ReservationService.getCustomerReservation(cust);
        }
        return custReservations;
    }

    public static Collection<IRoom> findARoom(Date checkinDate, Date checkoutDate) {
        return ReservationService.findRooms(checkinDate, checkoutDate);
    }

}
