package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        Scanner input = new Scanner(System.in);
        Formula1ChampionshipManager f1manager = new Formula1ChampionshipManager();
        f1manager.readDetailsfromTxt();

    /*
     * Menu Displayed to the User
     */
        System.out.println('\n' + "Welcome to the Formula1 CHAMPIONSHIP!" + '\n');

        System.out.println("Enter '1' to Add a New Driver");
        System.out.println("Enter '2' to Remove a Driver");
        System.out.println("Enter '3' to Change a Team's Driver");
        System.out.println("Enter '4' to Display Statistics of a Specific Driver");
        System.out.println("Enter '5' to Display Statistics of All Drivers");
        System.out.println("Enter '6' to Add a Race");
        System.out.println("Enter '7' to Store program data into a Text file");
        System.out.println("Enter '8' to access the Graphical User Interface");
        System.out.println("Enter '0' to Exit the program" );

        String userInputValue;
        do {
            System.out.print( '\n' + "Enter the value: ");
            userInputValue = input.next();
            if (userInputValue.equals("1")) {
                f1manager.createNewDriver();

            } else if (userInputValue.equals("2")) {
                f1manager.removeADriver();

            } else if (userInputValue.equals("3")) {
                f1manager.changeTeamDriver();

            } else if (userInputValue.equals("4")) {
                f1manager.displaySpecificDriversStatistics();

            } else if (userInputValue.equals("5")) {
                f1manager.displayAllDriversStatistics();

            } else if (userInputValue.equals("6")) {
                f1manager.addARace();

            } else if (userInputValue.equals("7")) {
                f1manager.saveDetailstoTxt();

            } else if (userInputValue.equals("8")) {
                f1manager.showGUI();
            }


        } while (!(userInputValue.equals("0")));

    }

}



