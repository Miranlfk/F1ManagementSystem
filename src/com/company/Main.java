package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        Scanner input = new Scanner(System.in);
        Formula1ChampionshipManager f1m = new Formula1ChampionshipManager();
        f1m.readDetails();

        System.out.println('\n' + "Welcome to the BRAIN DEAD SOCIETY" + '\n');

        System.out.println("Enter '1' to add a Driver");
        System.out.println("Enter '2' to remove a Driver");
        System.out.println("Enter '3' to change a Team's Driver");
        System.out.println("Enter '4' to display Statistics of a Specific Driver");
        System.out.println("Enter '5' to display Statistics of All Drivers");
        System.out.println("Enter '6' to add a Race");
        System.out.println("Enter '7' to store program data into a file");
        System.out.println("Enter '8' to access the GUI");
        System.out.println("Enter '0' to exit the program" );

        String userInput;
        do {
            System.out.print( '\n' + "Enter the value: ");
            userInput = input.next();
            if (userInput.equals("1")) {
                f1m.createDriver();
            } else if (userInput.equals("2")) {
                f1m.removeDriver();
                //
            } else if (userInput.equals("3")) {
                f1m.changeTeamsDriver(); //ask kstar savior
                //
            } else if (userInput.equals("4")) {
                f1m.displayDriverStat();
                //

            } else if (userInput.equals("5")) {
                f1m.displayAllDriversStats();
                //

            } else if (userInput.equals("6")) {
                f1m.addRace();
            } else if (userInput.equals("7")) {
                f1m.saveDetails();
            } else if (userInput.equals("8")) {
                f1m.showDetailsGUI();
            }


        } while (!(userInput.equals("0")));

    }


}



