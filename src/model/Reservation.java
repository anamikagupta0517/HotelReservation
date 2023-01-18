package model;

import java.util.Date;

public class Reservation {
    Customer customer;
    IRoom room;
    Date checkinDate;
    Date checkoutDate;

    @Override
    public String toString(){
        return String.format("Customer Details: %s \n Room Details: %s, Check-in Date: %s, Checkout Date: %s", customer.toString(), room.toString(), checkinDate.toString(), checkoutDate.toString());
    }
}
