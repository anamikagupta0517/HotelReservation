package Service;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class ReservationService {
    static Collection<Reservation> reservations = new ArrayList<>();
    static Collection<IRoom> rooms = new ArrayList<>();

    public static Collection<IRoom> getAllRooms() {
        return rooms;
    }

    public static void addRoom(IRoom room) {
        rooms.add(room);
    }

    public static IRoom getARoom(String roomNumber) {
        IRoom foundRoom = null;
        for(IRoom room : rooms) {
            if(room.getRoomNumber().equalsIgnoreCase(roomNumber)) {
                foundRoom = room;
                break;
            }
        }
        return foundRoom;
    }

    public static Reservation reserveARoom(Customer customer, IRoom room, Date checkinDate, Date checkoutDate) {
        Reservation res = new Reservation(customer, room, checkinDate, checkoutDate);
        reservations.add(res);
        return res;
    }

    public static Collection<IRoom> findRooms(Date checkinDate, Date checkoutDate) {
        Collection<IRoom> availableRooms = new ArrayList<>();
        for(Reservation res : reservations) {
            if(checkinDate.after(res.getCheckoutDate()) || checkoutDate.before(res.getCheckinDate())) {
                availableRooms.add(res.getRoom());
            }
        }
        return availableRooms;
    }

    public static Collection<Reservation> getCustomerReservation(Customer customer) {
        Collection<Reservation> customerReservations = new ArrayList<>();
        for(Reservation res : reservations) {
            if(res.getCustomer().getEmail().equalsIgnoreCase(customer.getEmail())){
                customerReservations.add(res);
            }
        }
        return customerReservations;
    }

    public static void printAllReservations() {
        for(Reservation res : reservations) {
            System.out.println(res.toString());
        }
    }

}
