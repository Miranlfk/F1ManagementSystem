package com.company;

public class RaceDrivers {
    String raceName;
    String raceDate;
    String driverName;
    int StartPosition;
    int EndPosition;

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public String getRaceDate() {
        return raceDate;
    }

    public void setRaceDate(String raceDate) {
        this.raceDate = raceDate;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public int getStartPosition() {
        return StartPosition;
    }

    public void setStartPosition(int startPosition) {
        StartPosition = startPosition;
    }

    public int getEndPosition() {
        return EndPosition;
    }

    public void setEndPosition(int endPosition) {
        EndPosition = endPosition;
    }
}
