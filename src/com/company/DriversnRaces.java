package com.company;

public class DriversnRaces {
    String raceName;
    String raceDate;
    String driverName;
    int StartPosition;
    int EndPosition;

    /**
     * Getter method to obtain the Name of the Race
     * @return
     */
    public String getRaceName() {
        return raceName;
    }

    /**
     * Setter method to set the name of the Race using String parameter
     * @param raceName
     */
    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    /**
     * Getter method to obtain the Date of the Race
     * @return
     */
    public String getRaceDate() {
        return raceDate;
    }

    /**
     * Setter method to set the date of the Race using String parameter
     * @param raceDate
     */
    public void setRaceDate(String raceDate) {
        this.raceDate = raceDate;
    }

    /**
     * Getter method to obtain the Name of the Driver
     * @return
     */
    public String getDriverName() {
        return driverName;
    }

    /**
     * Setter method to set the name of the driver using String parameter
     * @param driverName
     */
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    /**
     * Getter method to obtain the Starting postions of the drivers in a Race
     * @return
     */
    public int getStartPosition() {
        return StartPosition;
    }

    /**
     * Setter method to set the starting position of a driver in the Race using Integer parameter
     * @param startPosition
     */
    public void setStartPosition(int startPosition) {
        StartPosition = startPosition;
    }

    /**
     * Getter method to obtain the Ending postions of the drivers in a Race
     * @return
     */
    public int getEndPosition() {
        return EndPosition;
    }

    /**
     * Setter method to set the ending position of a driver in the Race using Integer parameter
     * @param endPosition
     */
    public void setEndPosition(int endPosition) {
        EndPosition = endPosition;
    }
}
