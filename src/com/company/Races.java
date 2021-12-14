package com.company;

import java.util.ArrayList;

public class Races {

    private String name;
    private String date;

    ArrayList<Formula1Driver> RaceSPositions = new ArrayList<>();
    ArrayList<Formula1Driver> RaceEDetails = new ArrayList<>();

    public Races() {}

    /**
     * Parameterised Constructor for the Races Class
     * @param name
     * @param date
     * @param racePositions
     * @param raceEDetails
     */
    public Races(String name, String date, ArrayList<Formula1Driver> racePositions, ArrayList<Formula1Driver> raceEDetails) {
        this.name = name;
        this.date = date;
        RaceSPositions = racePositions;
        RaceEDetails = raceEDetails;
    }

    /**
     * Getter method to obtain the Date of the Race
     * @return
     */
    public String getDate() {
        return date;
    }

    /**
     * Setter method to set the date of the Race using String parameter
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Getter method to obtain the Name of the Race
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method to set the name of the Race using String parameter
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method to obtain the Arraylist of the Starting postions of the drivers in a Race
     * @return
     */
    public ArrayList<Formula1Driver> getRacePositions() {
        return RaceSPositions;
    }

    /**
     * Setter method to set the starting postions of the driver in a race using Arraylist parameter
     * @param racePositions
     */
    public void setRaceSPositions(ArrayList<Formula1Driver> racePositions) {
        RaceSPositions = racePositions;
    }

    /**
     * Getter method to obtain the Arraylist of the Ending postions of the drivers in a Race
     * @return
     */
    public ArrayList<Formula1Driver> getRaceEDetails() {
        return RaceEDetails;
    }

    /**
     * Setter method to set the ending postions of the driver in a race using Arraylist parameter
     * @param raceEDetails
     */
    public void setRaceEDetails(ArrayList<Formula1Driver> raceEDetails) {
        RaceEDetails = raceEDetails;
    }




}


