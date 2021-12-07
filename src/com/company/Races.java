package com.company;

import java.util.ArrayList;

public class Races {

    private String date;
    ArrayList<Formula1Driver> RaceDetails = new ArrayList<>();

    Races(){}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<Formula1Driver> getRaceDetails() {
        return RaceDetails;
    }

    public void setRaceDetails(ArrayList<Formula1Driver> raceDetails) {
        RaceDetails = raceDetails;
    }
}
