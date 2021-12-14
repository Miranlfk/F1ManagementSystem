package com.company;

public class Formula1Driver extends Driver {

    private int noFirst;
    private int noSecond;
    private int noThird;
    private int noRaces;
    private int noPoints;

    public  Formula1Driver(){}

    /**
     * Parameterized Constructor for the Formula1Driver Class
     * @param name
     * @param location
     * @param team
     * @param noFirst
     * @param noSecond
     * @param noThird
     * @param noRaces
     * @param noPoints
     */
    public Formula1Driver(String name, String location, String team, int noFirst, int noSecond, int noThird,
                          int noRaces, int noPoints) {
        super(name, location, team);
        this.noFirst = noFirst;
        this.noSecond = noSecond;
        this.noThird = noThird;
        this.noRaces = noRaces;
        this.noPoints = noPoints;
    }

    /**
     * Getter method to obtain the Number of First Place finishes of the Driver
     * @return
     */
    public int getNoFirst() {
        return noFirst;
    }

    /**
     * Setter method to set the number of first place finishes of the driver using Integer parameter
     * @param noFirst
     */
    public void setNoFirst(int noFirst) {
        this.noFirst = noFirst;
    }

    /**
     * Getter method to obtain the Number of Second Place finishes of the Driver
     * @return
     */
    public int getNoSecond() {
        return noSecond;
    }

    /**
     * Setter method to set the number of second place finishes of the driver using Integer parameter
     * @param noSecond
     */
    public void setNoSecond(int noSecond) {
        this.noSecond = noSecond;
    }

    /**
     * Getter method to obtain the Number of Third Place finishes of the Driver
     * @return
     */
    public int getNoThird() {
        return noThird;
    }

    /**
     * Setter method to set the number of third place finishes of the driver using Integer parameter
     * @param noThird
     */
    public void setNoThird(int noThird) {
        this.noThird = noThird;
    }

    /**
     * Getter method to obtain the Number of Races the Driver participated in
     * @return
     */
    public int getNoRaces() {
        return noRaces;
    }

    /**
     * Setter method to set the number of races the driver participated in using Integer parameter
     * @param noRaces
     */
    public void setNoRaces(int noRaces) {
        this.noRaces = noRaces;
    }

    /**
     * Getter method to obtain the Number of Points of the Driver
     * @return
     */
    public int getNoPoints() {
        return noPoints;
    }

    /**
     * Setter method to set the number of points of the driver using Integer parameter
     * @param noPoints
     */
    public void setNoPoints(int noPoints) {
        this.noPoints = noPoints;
    }

    /**
     * Method used to compare points of drivers
     * If Points of 2 driver are the same, Number of First Place finishes is compared
     * @param o
     * @return
     */
    @Override
    public int compareTo(Object o) {
        int oldPP=((Formula1Driver)o).getNoPoints();

        if (oldPP == this.noPoints){
            int num1PP = ((Formula1Driver)o).getNoFirst();
            return num1PP - this.noFirst;
        }
        return oldPP-this.noPoints;
    }
}
