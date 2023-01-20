package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation {
    Customer customer;
    IRoom room;
    Date checkinDate;
    Date checkoutDate;

    public Reservation(Customer customer, IRoom room, Date checkinDate, Date checkoutDate) {
        this.customer = customer;
        this.room = room;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public IRoom getRoom() {
        return room;
    }

    public void setRoom(IRoom room) {
        this.room = room;
    }

    public Date getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(Date checkinDate) {
        this.checkinDate = checkinDate;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    @Override
    public String toString() {
        return String.format("Customer Details: %s \n%s per night, \nCheck-in Date: %s, Checkout Date: %s \nTotal price : $%,.2f",
                customer.toString(),
                room.toString(),
                new SimpleDateFormat("MM-dd-yyyy").format(checkinDate),
                new SimpleDateFormat("MM-dd-yyyy").format(checkoutDate),
                room.getRoomPrice() * (int) (checkoutDate.getTime() - checkinDate.getTime()) / (1000 * 60 * 60 * 24));
    }
}
