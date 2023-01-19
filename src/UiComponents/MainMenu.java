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
        int optionSelection;
        boolean cont = true;



        System.out.println("Welcome to Ana's Hotel Reservation Application");

        do {
            MainMenu.printOptions();
            optionSelection = in.nextInt();

            switch (optionSelection) {
                case 1:
                    System.out.println("Find and reserve a room");

                    try {
                        System.out.println("Enter CheckIn Date mm/dd/yyyy example 02/01/2023");
                        Date checkinDate = new SimpleDateFormat("MM/dd/yyyy").parse(in.next());
                        System.out.println("Enter CheckOut Date mm/dd/yyyy example 02/01/2023");
                        Date checkoutDate = new SimpleDateFormat("MM/dd/yyyy").parse(in.next());
                        Collection<IRoom> availableRooms = ReservationService.findRooms(checkinDate, checkoutDate);
                        for(IRoom room : availableRooms) {
                            room.toString();
                        }
                        System.out.println("\n");
                        System.out.println("Would you like to book a room? y/n");
                        String yesOrNo = in.next();
                        if(DataValidator.isYesOrNo(yesOrNo) && yesOrNo.equalsIgnoreCase("y")) {
                            System.out.println("Do you have an account with us? y/n");
                            String yOrN = in.next();
                            Customer customer = null;
                            if(DataValidator.isYesOrNo(yOrN) && yOrN.equalsIgnoreCase("y")) {
                                System.out.println("Enter your email format: name@domain.com");
                                String email = in.next();
                                if(DataValidator.isEmailValid(email)) {
                                    customer = CustomerService.getCustomer(email);
                                } else {
                                    System.out.println("Invalid/Incorrect email provided. Please try again.");
                                }
                            } else {
                                customer = createAccount();
                            }
                            System.out.println("What room number would you like to reserve");
                            String roomNum = in.next();
                            IRoom room = ReservationService.getARoom(roomNum);
                            if(customer != null && room != null) {
                                Reservation reservation = ReservationService.reserveARoom(customer, room, checkinDate, checkoutDate);
                                reservation.toString();
                            } else {
                                System.out.println("Something went wrong, Please contact service desk.");
                            }
                        } else {
                            System.out.println("No problem, thank you for visiting us.");
                        }
                    } catch (Exception ex) {
                        System.out.println("Exception occurred, description: " + ex.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("See my reservation");
                    break;
                case 3:
                    createAccount();
                    break;
                case 4:
                    System.out.println("Loading Admin menu \n");
                    AdminMenu.loadAdminMenu();
                    break;
                case 5:
                    System.out.println("Good Bye!!! Thank you for your visit. Hope you see you again soon.");
                    cont = false;
                    break;
                default:
                    System.out.println("Invalid number, please try again. Below are the options.");
                    MainMenu.printOptions();
            }
        } while (cont);


    }

    private static Customer createAccount() {
        System.out.println("Please enter your first name:");
        String firstName = in.next();
        System.out.println("Please enter your last name:");
        String lastName = in.next();
        System.out.println("Please enter your email:");
        String email = in.next();
        try {
            CustomerService.addCustomer(firstName, lastName, email);
        } catch (Exception ex) {
            System.out.println("Couldn't create account due to exception: " + ex.getMessage());
        }
        return CustomerService.getCustomer(email);
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


}
