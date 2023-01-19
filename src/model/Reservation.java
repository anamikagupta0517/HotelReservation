package model;

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
    public String toString(){
        return String.format("Customer Details: %s \n Room Details: %s, Check-in Date: %s, Checkout Date: %s", customer.toString(), room.toString(), checkinDate.toString(), checkoutDate.toString());
    }
}
