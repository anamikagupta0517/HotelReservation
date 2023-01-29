package UiComponents;

import Resources.DataValidator;
import Service.CustomerService;
import Service.ReservationService;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.Scanner;

public class AdminMenu {

    public static void loadAdminMenu() {
        CustomerService customerService = CustomerService.getInstance();
        String optionSelection;
        boolean cont = true;

        Scanner in = new Scanner(System.in);


        do {
            AdminMenu.printAdminMenu();

            optionSelection = in.next();

            switch (optionSelection) {
                case "1":
                    if (customerService.getAllCustomers().size() == 0) {
                        System.out.println("No customers found in the system.");
                    }
                    for (Customer customer : customerService.getAllCustomers()) {
                        System.out.println(customer.toString());
                    }
                    break;
                case "2":
                    if (ReservationService.getAllRooms().size() == 0) {
                        System.out.println("No rooms found in the system.");
                    }
                    for (IRoom room : ReservationService.getAllRooms()) {
                        System.out.println(room.toString());
                    }
                    break;
                case "3":
                    ReservationService.printAllReservations();
                    break;
                case "4":
                    boolean wantToAddMore = true;
                    do {
                        System.out.println("Enter room number:");
                        String roomNumber = in.next();
                        System.out.println("Enter price per night:");
                        double price = in.nextDouble();
                        System.out.println("Enter room type: 1 for single bed and 2 for double bed");
                        int singleOrDouble = in.nextInt();

                        if (ReservationService.getARoom(roomNumber) == null) {
                            Room room = new Room();
                            room.setRoomNumber(roomNumber);
                            room.setPrice(price);
                            room.setRoomType(singleOrDouble == 1 ? RoomType.SINGLE : RoomType.DOUBLE);
                            ReservationService.addRoom(room);
                        } else {
                            System.out.println("Room number " + roomNumber + " is already present in the system.");
                        }
                        System.out.println("Would you like to add another room? Enter y for yes and n for no.");
                        String yesOrNo = in.next();
                        if (DataValidator.isYesOrNo(yesOrNo)) {
                            if (yesOrNo.equalsIgnoreCase("n")) {
                                wantToAddMore = false;
                            }
                        } else {
                            System.out.println("Error occurred: Invalid option, only \"y\" or \"n\" is allowed");
                            wantToAddMore = false;
                        }

                    } while (wantToAddMore);
                    break;
                case "5":
                    System.out.println("-------- Going back to main menu ----------");
                    cont = false;
                    break;
                default:
                    System.out.println("Invalid option, please try again. Below are the options.");
            }
        } while (cont);

    }


    private static void printAdminMenu() {
        System.out.println("=============================================");
        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms");
        System.out.println("3. See all Reservations");
        System.out.println("4. Add a room");
        System.out.println("5. Back to main menu");
        System.out.println("=============================================");
        System.out.println("Please select a number for menu option");
    }
}
