package Service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {
    static Set<Reservation> reservations = new HashSet<>();
    static HashSet<IRoom> rooms = new HashSet<>();
    private static ReservationService reservationServiceInstance = null;

    public static ReservationService getInstance() {
        if (reservationServiceInstance == null) {
            reservationServiceInstance = new ReservationService();
        }
        return reservationServiceInstance;
    }

    public static Collection<IRoom> getAllRooms() {
        return rooms;
    }

    public static void addRoom(IRoom room) {
        rooms.add(room);
    }

    public static IRoom getARoom(String roomNumber) {
        IRoom foundRoom = null;
        for (IRoom room : rooms) {
            if (room.getRoomNumber().equalsIgnoreCase(roomNumber)) {
                foundRoom = room;
                break;
            }
        }
        return foundRoom;
    }

    public static Reservation reserveARoom(Customer customer, IRoom room, Date checkinDate, Date checkoutDate) {
        boolean canReserve = true;
        Reservation newRes = new Reservation(customer, room, checkinDate, checkoutDate);
        if (reservations.size() > 0) {
            for (Reservation res : reservations) {
                if (res.getRoom().getRoomNumber().equalsIgnoreCase(room.getRoomNumber())) {
                    //If reservation is within he existing reservation
                    if (checkinDate.compareTo(res.getCheckinDate()) < 0 && checkoutDate.compareTo(res.getCheckinDate()) <= 0) {
                        canReserve = true;
                    } else
                        canReserve = checkinDate.compareTo(res.getCheckoutDate()) >= 0 && checkoutDate.compareTo(res.getCheckoutDate()) > 0;
                }
            }
        }
        if (canReserve) {
            reservations.add(newRes);
            return newRes;
        } else {
            return null;
        }
    }

    public static Collection<IRoom> findRooms(Date checkinDate, Date checkoutDate) throws Exception {
        Set<IRoom> availableRooms = new HashSet<>();
        availableRooms.addAll(rooms);
        for (Reservation res : reservations) {
            for (IRoom room : rooms) {
                if (res.getRoom().getRoomNumber().equalsIgnoreCase(room.getRoomNumber())) {
                    //IF PROVIDED CHECKIN DATE IS AFTER RESERVATION'S CHECKOUT DATE
                    //AND PROVIDED CHECKOUT DATE IS BEFORE RESERVATION'S CHECKIN DATE
                    //THEN THIS ROOM CAN STAY
                    //ELSE REMOVE FROM LIST
                    if (checkinDate.compareTo(res.getCheckoutDate()) >= 0 || checkoutDate.compareTo(res.getCheckinDate()) <= 0) {
                        availableRooms.add(room);
                    } else {
                        availableRooms.remove(room);
                    }
                }
            }
        }
        return availableRooms;
    }

    public static Collection<Reservation> getCustomerReservation(Customer customer) {
        Collection<Reservation> customerReservations = new ArrayList<>();
        for (Reservation res : reservations) {
            if (res.getCustomer().getEmail().equalsIgnoreCase(customer.getEmail())) {
                customerReservations.add(res);
            }
        }
        return customerReservations;
    }

    public static void printAllReservations() {
        if (reservations.size() > 0) {
            for (Reservation res : reservations) {
                System.out.println(res.toString());
            }
        } else {
            System.out.println("No reservations found.......");
        }
    }


}
