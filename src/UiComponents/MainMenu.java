package UiComponents;

import java.util.Scanner;

public class MainMenu {



    public static void main(String[] args) {
        int optionSelection;
        boolean cont = true;

        Scanner in = new Scanner(System.in);

        System.out.println("Welcome to Ana's Hotel Reservation Application");
        MainMenu.printOptions();

        do {

            optionSelection = in.nextInt();

            switch (optionSelection) {
                case 1:
                    System.out.println("Find and reserve a room");
                    break;
                case 2:
                    System.out.println("See my reservation");
                    break;
                case 3:
                    System.out.println("Create an account");
                    break;
                case 4:
                    System.out.println("Admin");
                    break;
                case 5:
                    System.out.println("Good Bye!!! Thank you for your visit. Hope you see you again soon.");
                    cont = false;
                    break;
                default:
                    System.out.println("Invalid number, please try again. Below are the options.");
                    MainMenu.printOptions();
            }
        } while(cont);


    }

    private static void printOptions(){
        System.out.println("/n");
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
