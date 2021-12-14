package com.company;

abstract class Driver implements Comparable{
    private String name;
    private String location;
    private String team;

    public  Driver(){}

    /**
     * Parameterised Constructor for the Driver Class
     * @param name
     * @param location
     * @param team
     */
    public Driver(String name, String location, String team) {
        this.name = name;
        this.location = location;
        this.team = team;
    }

    /**
     * Getter method to obtain the Name of the Driver
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method to set the name of the driver using String parameter
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method to obtain the Country/Location of the Driver
     * @return
     */
    public String getLocation() {
        return location;
    }

    /**
     * Setter method to set the location of the driver using String parameter
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Getter method to obtain the team which the driver represents Driver
     * @return
     */
    public String getTeam() {
        return team;
    }

    /**
     * Setter method to set the team of the driver using String parameter
     * @param team
     */
    public void setTeam(String team) {
        this.team = team;
    }

}
