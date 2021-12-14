package com.company;

import java.util.ArrayList;

public class Races {

    private String name;
    private String date;

    ArrayList<Formula1Driver> RaceStartPositions = new ArrayList<>();
    ArrayList<Formula1Driver> RaceEndPositions = new ArrayList<>();

    public Races() {}

    /**
     * Parameterised Constructor for the Races Class
     * @param name
     * @param date
     * @param raceStartPositions
     * @param raceEndPositions
     */
    public Races(String name, String date, ArrayList<Formula1Driver> raceStartPositions, ArrayList<Formula1Driver> raceEndPositions) {
        this.name = name;
        this.date = date;
        RaceStartPositions = raceStartPositions;
        RaceEndPositions = raceEndPositions;
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
        return RaceStartPositions;
    }

    /**
     * Setter method to set the starting postions of the driver in a race using Arraylist parameter
     * @param raceStartPositions
     */
    public void setRaceStartPositions(ArrayList<Formula1Driver> raceStartPositions) {
        RaceStartPositions = raceStartPositions;
    }

    /**
     * Getter method to obtain the Arraylist of the Ending postions of the drivers in a Race
     * @return
     */
    public ArrayList<Formula1Driver> getRaceEndPositions() {
        return RaceEndPositions;
    }

    /**
     * Setter method to set the ending postions of the driver in a race using Arraylist parameter
     * @param raceEndPositions
     */
    public void setRaceEndPositions(ArrayList<Formula1Driver> raceEndPositions) {
        RaceEndPositions = raceEndPositions;
    }



}


