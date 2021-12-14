package com.company;

public class Formula1Driver extends Driver {

    private int numFirst;
    private int numSecond;
    private int numThird;
    private int numRaces;
    private int numPoints;

    public  Formula1Driver(){}

    /**
     * Parameterized Constructor for the Formula1Driver Class
     * @param name
     * @param location
     * @param team
     * @param numFirst
     * @param numSecond
     * @param numThird
     * @param numRaces
     * @param numPoints
     */
    public Formula1Driver(String name, String location, String team, int numFirst, int numSecond, int numThird,
                          int numRaces, int numPoints) {
        super(name, location, team);
        this.numFirst = numFirst;
        this.numSecond = numSecond;
        this.numThird = numThird;
        this.numRaces = numRaces;
        this.numPoints = numPoints;
    }

    /**
     * Getter method to obtain the Number of First Place finishes of the Driver
     * @return
     */
    public int getNumOfFirst() {
        return numFirst;
    }

    /**
     * Setter method to set the number of first place finishes of the driver using Integer parameter
     * @param numFirst
     */
    public void setNumOfFirst(int numFirst) {
        this.numFirst = numFirst;
    }

    /**
     * Getter method to obtain the Number of Second Place finishes of the Driver
     * @return
     */
    public int getNumOfSecond() {
        return numSecond;
    }

    /**
     * Setter method to set the number of second place finishes of the driver using Integer parameter
     * @param numSecond
     */
    public void setNumOfSecond(int numSecond) {
        this.numSecond = numSecond;
    }

    /**
     * Getter method to obtain the Number of Third Place finishes of the Driver
     * @return
     */
    public int getNumOfThird() {
        return numThird;
    }

    /**
     * Setter method to set the number of third place finishes of the driver using Integer parameter
     * @param numThird
     */
    public void setNumOfThird(int numThird) {
        this.numThird = numThird;
    }

    /**
     * Getter method to obtain the Number of Races the Driver participated in
     * @return
     */
    public int getNumOfRaces() {
        return numRaces;
    }

    /**
     * Setter method to set the number of races the driver participated in using Integer parameter
     * @param numRaces
     */
    public void setNumOfRaces(int numRaces) {
        this.numRaces = numRaces;
    }

    /**
     * Getter method to obtain the Number of Points of the Driver
     * @return
     */
    public int getNumOfPoints() {
        return numPoints;
    }

    /**
     * Setter method to set the number of points of the driver using Integer parameter
     * @param numPoints
     */
    public void setNumOfPoints(int numPoints) {
        this.numPoints = numPoints;
    }

    /**
     * Method used to compare points of drivers
     * If Points of 2 driver are the same, Number of First Place finishes is compared
     * @param o
     * @return
     */
    @Override
    public int compareTo(Object o) {
        int oldPoints=((Formula1Driver)o).getNumOfPoints();

        if (oldPoints == this.numPoints){
            int num1stPos = ((Formula1Driver)o).getNumOfFirst();
            return num1stPos - this.numFirst;
        }
        return oldPoints-this.numPoints;
    }
}
