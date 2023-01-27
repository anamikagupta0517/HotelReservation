package Service;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

public class ReservationService {
    static Set<Reservation> reservations = new HashSet<>();
    static Set<IRoom> rooms = new HashSet<>();
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
        if(reservations.size() > 0) {
            for (Reservation res : reservations) {
                if (res.getRoom().getRoomNumber().equalsIgnoreCase(room.getRoomNumber())) {
                    //If reservation is within he existing reservation
                    if (checkinDate.compareTo(res.getCheckinDate()) < 0 && checkoutDate.compareTo(res.getCheckinDate()) <= 0) {
                        canReserve = true;
                    } else if (checkinDate.compareTo(res.getCheckoutDate()) >= 0 && checkoutDate.compareTo(res.getCheckoutDate()) > 0) {
                        canReserve = true;
                    } else {
                        canReserve = false;
                    }
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

    public static Collection<IRoom> findRooms(Date checkinDate, Date checkoutDate) {
        Collection<IRoom> availableRooms = new ArrayList<>();

        for (IRoom room : rooms) {
            if (reservations.size() > 0) {
                for (Reservation res : reservations) {
                    if (room.getRoomNumber().equalsIgnoreCase(res.getRoom().getRoomNumber())) {
                        //CHeck if checkInDate and checkoutDate is not as requested
                        if (checkinDate.after(res.getCheckoutDate()) || checkoutDate.before(res.getCheckinDate())) {
                            availableRooms.add(room);
                        }
                    } else {
                        availableRooms.add(room);
                    }
                }
            } else {
                availableRooms.add(room);
            }
        }

        if (availableRooms.size() == 0) {
            return findRooms(new Date(checkinDate.getTime() + (1000 * 60 * 60 * 24 * 7)), new Date(checkoutDate.getTime() + (1000 * 60 * 60 * 24 * 7)));
        } else {
            return availableRooms;
        }
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
