package UiComponents;

import Resources.DataValidator;
import Service.CustomerService;
import Service.ReservationService;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {

    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        String optionSelection;
        boolean cont = true;
        ReservationService reservationService = ReservationService.getInstance();
        CustomerService customerService = CustomerService.getInstance();

        System.out.println("Welcome to Ana's Hotel Reservation Application");

        do {
            MainMenu.printOptions();
            optionSelection = in.next();

            switch (optionSelection) {
                case "1":
                    //Find and reserve a room
                    try {
                        System.out.println("Enter CheckIn Date mm/dd/yyyy example 02/01/2023");
                        Date checkinDate = new SimpleDateFormat("MM/dd/yyyy").parse(in.next());
                        System.out.println("Enter CheckOut Date mm/dd/yyyy example 02/01/2023");
                        Date checkoutDate = new SimpleDateFormat("MM/dd/yyyy").parse(in.next());
                        if (checkinDate.before(checkoutDate)) {
                            Collection<IRoom> availableRooms = reservationService.findRooms(checkinDate, checkoutDate);
                            if (availableRooms.size() > 0) {
                                printAvailableRooms(availableRooms);
                            } else {
                                checkinDate = new Date(checkinDate.getTime() + (1000 * 60 * 60 * 24 * 7));
                                checkoutDate = new Date(checkoutDate.getTime() + (1000 * 60 * 60 * 24 * 7));
                                System.out.println("Searching rooms after a week, for checkin date:"
                                        + new SimpleDateFormat("MM/dd/yyyy").format(checkinDate)
                                        + " and checkout date:"
                                        + new SimpleDateFormat("MM/dd/yyyy").format(checkoutDate));
                                availableRooms = reservationService.findRooms(checkinDate, checkoutDate);
                                if (availableRooms.size() > 0) {
                                    printAvailableRooms(availableRooms);
                                } else {
                                    System.out.println("We are sorry, no rooms are available right now.");
                                    break;
                                }
                            }
                            System.out.println("\n");
                            System.out.println("Would you like to book a room? y/n");
                            String yesOrNo = in.next();
                            if (DataValidator.isYesOrNo(yesOrNo) && yesOrNo.equalsIgnoreCase("y")) {
                                System.out.println("Do you have an account with us? y/n");
                                String yOrN = in.next();
                                Customer customer = null;
                                if (DataValidator.isYesOrNo(yOrN) && yOrN.equalsIgnoreCase("y")) {
                                    System.out.println("Enter your email format: name@domain.com");
                                    String email = in.next();
                                    if (DataValidator.isEmailValid(email)) {
                                        customer = customerService.getCustomer(email);
                                    } else {
                                        System.out.println("Invalid/Incorrect email provided. Please try again.");
                                    }
                                } else {
                                    customer = createAccount();
                                }
                                System.out.println("What room number would you like to reserve");
                                String roomNum = in.next();
                                IRoom room = reservationService.getARoom(roomNum);
                                if (customer != null && room != null) {
                                    Reservation reservation = reservationService.reserveARoom(customer, room, checkinDate, checkoutDate);
                                    if (reservation == null) {
                                        System.out.println("I am sorry, We could not reserve this room for you. It has a conflicting reservation.");
                                    } else {
                                        System.out.println(reservation);
                                    }
                                } else {
                                    System.out.println("Something went wrong, Please contact service desk.");
                                }
                            } else {
                                System.out.println("No problem, thank you for visiting us.");
                            }
                        } else {
                            System.out.println("Error occurred: Check in date should be before checkout date.");
                        }
                    } catch (Exception ex) {
                        System.out.println("Exception occurred, description: " + ex.getMessage());
                    }
                    break;
                case "2":
                    //See my reservation
                    System.out.println("Please enter your email format: abc@xyz.com");
                    String email = in.next();
                    if (DataValidator.isEmailValid(email)) {
                        Customer cust = customerService.getCustomer(email);
                        if (cust != null) {
                            Collection<Reservation> reservations = reservationService.getCustomerReservation(cust);
                            if (reservations.size() > 0) {
                                for (Reservation res : reservations) {
                                    System.out.println(res.toString());
                                }
                            } else {
                                System.out.println("No reservations found....");
                            }
                        } else {
                            System.out.println("No customer found with the email provided.");
                        }
                    } else {
                        System.out.println("Error occurred: Invalid email format.");
                    }
                    break;
                case "3":
                    //Create an account
                    createAccount();
                    break;
                case "4":
                    //Go to Admin menu
                    AdminMenu.loadAdminMenu();
                    break;
                case "5":
                    //Exit the application
                    System.out.println("Good Bye!!! Thank you for your visit. Hope you see you again soon.");
                    cont = false;
                    break;
                default:
                    System.out.println("Invalid option, please try again. Below are the options.");
            }
        } while (cont);


    }

    private static Customer createAccount() {
        CustomerService customerService = CustomerService.getInstance();
        System.out.println("Please enter your first name:");
        String firstName = in.next();
        System.out.println("Please enter your last name:");
        String lastName = in.next();
        System.out.println("Please enter your email:");
        String email = in.next();
        try {
            customerService.addCustomer(firstName, lastName, email);
        } catch (Exception ex) {
            System.out.println("Couldn't create account due to exception: " + ex.getMessage());
        }
        return customerService.getCustomer(email);
    }

    private static void printOptions() {
        System.out.println("=============================================");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservation");
        System.out.println("3. Create an account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        System.out.println("=============================================");
        System.out.println("Please select a number for menu option");
    }

    private static void printAvailableRooms(Collection<IRoom> rooms) {
        System.out.println("Below rooms are available for you to reserve.");
        for (IRoom room : rooms) {
            System.out.println(room.toString());
        }
    }


}
