package com.company;

import java.util.ArrayList;

public class Races {

    private String name;
    private String date;

    ArrayList<Formula1Driver> RacePositions = new ArrayList<>();
    ArrayList<Formula1Driver> RaceDetails = new ArrayList<>();

    public Races() {}

    public Races(String name, String date, ArrayList<Formula1Driver> racePositions, ArrayList<Formula1Driver> raceDetails) {
        this.name = name;
        this.date = date;
        RacePositions = racePositions;
        RaceDetails = raceDetails;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Formula1Driver> getRaceDetails() {
        return RaceDetails;
    }

    public void setRaceDetails(ArrayList<Formula1Driver> raceDetails) {
        RaceDetails = raceDetails;
    }

    public ArrayList<Formula1Driver> getRacePositions() {
        return RacePositions;
    }

    public void setRacePositions(ArrayList<Formula1Driver> racePositions) {
        RacePositions = racePositions;
    }


}


