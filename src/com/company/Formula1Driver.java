package com.company;

public class Formula1Driver extends Driver {

    private int noFirst;
    private int noSecond;
    private int noThird;
    private int noRaces;
    private int noPoints;

    public  Formula1Driver(){}

    public Formula1Driver(String name, String location, String team, int noFirst, int noSecond, int noThird,
                          int noRaces, int noPoints) {
        super(name, location, team);
        this.noFirst = noFirst;
        this.noSecond = noSecond;
        this.noThird = noThird;
        this.noRaces = noRaces;
        this.noPoints = noPoints;
    }

    public int getNoFirst() {
        return noFirst;
    }

    public void setNoFirst(int noFirst) {
        this.noFirst = noFirst;
    }

    public int getNoSecond() {
        return noSecond;
    }

    public void setNoSecond(int noSecond) {
        this.noSecond = noSecond;
    }

    public int getNoThird() {
        return noThird;
    }

    public void setNoThird(int noThird) {
        this.noThird = noThird;
    }

    public int getNoRaces() {
        return noRaces;
    }

    public void setNoRaces(int noRaces) {
        this.noRaces = noRaces;
    }

    public int getNoPoints() {
        return noPoints;
    }

    public void setNoPoints(int noPoints) {
        this.noPoints = noPoints;
    }


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
