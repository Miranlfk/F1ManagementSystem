package com.company;

import java.io.*;
import java.util.*;

public class Formula1ChampionshipManager implements ChampionshipManager{

    public ArrayList<Formula1Driver> DriverStats  = new ArrayList<>();
    ArrayList<Races> ListofRaces = new ArrayList<>();
    Scanner input = new Scanner(System.in);

    int Points [] = {0, 25, 18, 15, 12, 10, 8, 6, 4, 2, 1};

    public Formula1ChampionshipManager(ArrayList<Formula1Driver> DriverStats, ArrayList<Races> races) {
        this.DriverStats = DriverStats;
        this.ListofRaces = races;
    }

    public Formula1ChampionshipManager(){}

    @Override
    public void createDriver() {

        boolean raceStuff = false, teamexist = false;
        int num1s = 0, num2s = 0, num3s = 0;
        String team = " ";

        System.out.print("Enter new Driver's Name: ");
        String name = input.next();

        System.out.print("Enter new Driver's Location: ");
        String location = input.next();

        while (!teamexist){
            System.out.print("Enter new Driver's Team: ");
            team = input.next();
            for (int i = 0; i < DriverStats.size(); i++) {
                Formula1Driver f1 = DriverStats.get(i);
                if (!(team.equalsIgnoreCase(f1.getTeam()))) {
                    continue;

                }else {
                    System.out.println("ERROR! Team Already exists, Re-Enter the Team Name");
                    teamexist = true;
                    break;
                }
            }

        }




        System.out.print("Enter number of races particpated by New Driver: ");
        int races = input.nextInt();

        while (!(raceStuff)){

            System.out.print("Enter new Driver's 1st Place Finishes: ");
            num1s = input.nextInt();

            System.out.print("Enter new Driver's 2nd Place Finishes: ");
            num2s = input.nextInt();

            System.out.print("Enter new Driver's 3rd Place Finishes: ");
            num3s = input.nextInt();

            if (num1s + num2s +num3s > races){
                System.out.println("Invalid Data has been entered (Number of Races cannot be less than the podium finishes)");

            }
            else {
                raceStuff = true;
            }
        }


        System.out.print("Enter new Driver's Points: ");
        int points = input.nextInt();

        Formula1Driver d1 = new Formula1Driver("", "" , "", 0, 0,0, 0, 0);
        d1.setName(name);
        d1.setLocation(location);
        d1.setTeam(team);
        d1.setNoFirst(num1s);
        d1.setNoSecond(num2s);
        d1.setNoThird(num3s);
        d1.setNoRaces(races);
        d1.setNoPoints(points);
        DriverStats.add(d1);


    }

    @Override
    public void removeDriver() {

        boolean driverThere = false;
        while (!(driverThere)){
            System.out.println("Enter the Driver's Team of the Driver you wish to remove: ");
            String team = input.next();
            for (int i=0; i < DriverStats.size(); i++) {
                Formula1Driver f1 = DriverStats.get(i);
                if (team.equalsIgnoreCase(f1.getTeam())){
                    DriverStats.remove(i);
                    driverThere = true;
                }
            }
        }

    }

    @Override
    public void changeTeamsDriver() {

        boolean driverThere = false;
        while (!(driverThere)){
            System.out.println("Enter the Driver's Team of the Driver you wish to Change: ");
            String team = input.next();
            System.out.println("Enter the New Driver's Name: ");
            String newDriver = input.next();
            System.out.println("Enter the New Driver's Location: ");
            String newDriverLoc = input.next();
            for (int i=0; i < DriverStats.size(); i++) {
                Formula1Driver f1 = DriverStats.get(i);
                if (team.equalsIgnoreCase(f1.getTeam())){
                    f1.setName(newDriver);
                    f1.setLocation(newDriverLoc);
                    DriverStats.set(i,f1);
                    driverThere = true;
                }
            }
        }

    }

    @Override
    public void displayDriverStat() {

        boolean driverThere = false;
        while (!(driverThere)){
            System.out.println("Enter the Driver's Team of the Driver you wish to display: ");
            String team = input.next();
            for (int i=0; i < DriverStats.size(); i++) {
                Formula1Driver f1 = DriverStats.get(i);
                if (team.equalsIgnoreCase(f1.getTeam())){
                    System.out.println("Driver Name: " + f1.getName() + '\n' + "Team: " + f1.getTeam() + '\n' + "Country: " + f1.getLocation() + '\n' +"Points: " + f1.getNoPoints()
                            + '\n' + "Number of 1st Place: " + f1.getNoFirst() + '\n' + "Number of 2nd Place: " + f1.getNoSecond() + '\n' + "Number of 3rd Place: " + f1.getNoThird()
                            + '\n' + "Number of Races: " + f1.getNoRaces());
                    driverThere = true;
                }
            }
        }



    }

    @Override
    public void displayAllDriversStats() {
        Table details = new Table();
        //details.setRightAlign(true);//if true then cell text is right aligned
        details.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
        details.setHeaders("Name", "Team", "Country", "Points", "1st Places", "2nd Places", "3rd Places", "Number of Races");//optional - if not used then there will be no header and horizontal lines
        Collections.sort(DriverStats);
        for (int i=0; i < DriverStats.size(); i++){
            Formula1Driver f1 = DriverStats.get(i);
            details.addRow(String.valueOf(f1.getName()), String.valueOf(f1.getTeam()), String.valueOf(f1.getLocation()), String.valueOf(f1.getNoPoints()), String.valueOf(f1.getNoFirst()), String.valueOf(f1.getNoSecond()), String.valueOf(f1.getNoThird()), String.valueOf(f1.getNoRaces()));
        }

        details.print();


    }

    @Override
    public void addRace() {
        ArrayList<Formula1Driver> RaceDrivers = new ArrayList<>();
        System.out.println("Enter the Race Date: ");
        String raceDate = input.next();
        System.out.println("Enter the number of Teams participationg the race: ");
        int driverCount = input.nextInt();
        boolean driverHere = false;

        for (int i=1; i < driverCount+1; i++){

            System.out.println("Enter the Team which got Number" + i  + " Place: ");
            String teamPostions = input.next();
            Formula1Driver f1 = null;
            //updated Driver
            for (int j=0; j < DriverStats.size(); j++){
                f1 = DriverStats.get(j);
                if (teamPostions.equalsIgnoreCase(f1.getTeam())){
                    driverHere = true;
                    f1.setNoRaces(f1.getNoRaces()+1);
                    if (i == 1){
                        f1.setNoFirst(f1.getNoFirst()+1);
                    } else if (i == 2){
                        f1.setNoSecond(f1.getNoSecond()+1);
                    } else if (i == 3){
                        f1.setNoThird(f1.getNoThird()+1);
                    }
                    f1.setNoPoints(f1.getNoPoints()+Points[i]);
                    RaceDrivers.add(f1);
                }
            }
            if (!driverHere){
                System.out.println("The Team you've entered is incorrect, Please Enter the Team Name again!");
                i--;
            }

        }
        Races race = new Races();
        race.setDate(raceDate);
        race.setRaceDetails(RaceDrivers);
        ListofRaces.add(race);

    }

    @Override
    public void saveDetails() {
        try {

            FileWriter boothFile = new FileWriter("Championship.txt");

            for (int i=0; i < DriverStats.size(); i++){
                Formula1Driver f1 = DriverStats.get(i);
                boothFile.write("Driver Name: " + f1.getName() + ", Team: " + f1.getTeam() + ", Country: " + f1.getLocation() + ", Points: " + f1.getNoPoints()
                        + ", Number of 1st Place: " + f1.getNoFirst() + ", Number of 2nd Place: " + f1.getNoSecond() + ", Number of 3rd Place: " + f1.getNoThird()
                        + ", Number of Races: " + f1.getNoRaces() + '\n');
            }
            boothFile.close();
            System.out.println("Champion.txt has been created!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void readDetails() {
        Scanner s = null;
        try {
            s = new Scanner(new File("Championship.txt"));

            while (s.hasNext()){

                String line = s.nextLine();
                line = line.replace("Driver Name:","");
                line = line.replace("Team:","");
                line = line.replace("Country:","");
                line = line.replace("Points:","");
                line = line.replace("Number of 1st Place:","");
                line = line.replace("Number of 2nd Place:","");
                line = line.replace("Number of 3rd Place:","");
                line = line.replace("Number of Races:","");
                line = line.replace(" ","");
                ArrayList<String> sepWords = new ArrayList<String>(Arrays.asList(line.split(",")));
                Formula1Driver f1 = new Formula1Driver();
                for (int i=0 ; i<sepWords.size();  i++){

                    f1.setName(sepWords.get(0));
                    f1.setTeam(sepWords.get(1));
                    f1.setLocation(sepWords.get(2));
                    f1.setNoPoints(Integer.parseInt(sepWords.get(3)));
                    f1.setNoFirst(Integer.parseInt(sepWords.get(4)));
                    f1.setNoSecond(Integer.parseInt(sepWords.get(5)));
                    f1.setNoThird(Integer.parseInt(sepWords.get(6)));
                    f1.setNoRaces(Integer.parseInt(sepWords.get(7)));

                    //assign words in sepWords to object f1 arryalist
                    //assifn f1 to drivers arrylist[
                }
                DriverStats.add(f1);

            }

            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            //e.printStackTrace();
        }

    }

    @Override
    public void showDetailsGUI() {

        GUI guiNew = new GUI(DriverStats, ListofRaces);

    }




}