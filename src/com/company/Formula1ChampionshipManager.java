package com.company;

import java.io.*;
import java.util.*;

public class Formula1ChampionshipManager implements ChampionshipManager{

    ArrayList<Formula1Driver> DriverStatistics  = new ArrayList<>();
    ArrayList<Races> RacesList = new ArrayList<>();
    Scanner input = new Scanner(System.in);

    int Points [] = {0, 25, 18, 15, 12, 10, 8, 6, 4, 2, 1};

    public Formula1ChampionshipManager(){}

    /**
     * Parametrized Constructor of Formula1ChampionshipManager Class
     * @param DriverStatistics
     * @param racesList
     */
    public Formula1ChampionshipManager(ArrayList<Formula1Driver> DriverStatistics, ArrayList<Races> racesList) {
        this.DriverStatistics = DriverStatistics;
        this.RacesList = racesList;
    }

    /**
     * Method used to create a driver
     * Driver details obtained from user through prompts
     * Driver details added to Formula1Driver Object which is then added to an Arraylist of Formula1Drivers
     */
    @Override
    public void createNewDriver() {

        boolean raceDetail = false, teamExist = false;
        int no1s = 0, no2s = 0, no3s = 0;
        String teams = " ";

        System.out.print("Enter new Driver's Name: ");
        String name = input.next();

        System.out.print("Enter new Driver's Location: ");
        String location = input.next();

        while (!teamExist){
            int count = 0;
            System.out.print("Enter new Driver's Team: ");
            teams = input.next();
            if (DriverStatistics.size() != 0){
                for (int i = 0; i < DriverStatistics.size(); i++) {
                    Formula1Driver driver = DriverStatistics.get(i);
                    if (teams.equalsIgnoreCase(driver.getTeam())) {
                        count++;
                        System.out.println("ERROR! Team Already exists, Re-Enter the Team Name");

                    }else {
                        teamExist = true;
                    }
                }
                if (count>0){
                    teamExist = false;
                }
            } else{
                teamExist = true;
            }


        }

        System.out.print("Enter number of races particpated by New Driver: ");
        int races = input.nextInt();

        while (!(raceDetail)){

            System.out.print("Enter the new Driver's 1st Place Finishes: ");
            no1s = input.nextInt();

            System.out.print("Enter the new Driver's 2nd Place Finishes: ");
            no2s = input.nextInt();

            System.out.print("Enter the new Driver's 3rd Place Finishes: ");
            no3s = input.nextInt();

            if (no1s + no2s +no3s > races){
                System.out.println("Invalid Data has been entered! Please enter Number of Races again!");

            }
            else {
                raceDetail = true;
            }
        }

        System.out.print("Enter new Driver's Points: ");
        int numPoints = input.nextInt();

        Formula1Driver driver1 = new Formula1Driver("", "" , "", 0, 0,0, 0, 0);
        driver1.setName(name);
        driver1.setLocation(location);
        driver1.setTeam(teams);
        driver1.setNumOfFirst(no1s);
        driver1.setNumOfSecond(no2s);
        driver1.setNumOfThird(no3s);
        driver1.setNumOfRaces(races);
        driver1.setNumOfPoints(numPoints);
        DriverStatistics.add(driver1);

    }

    /**
     * Method used to Remove a Driver
     * User is required to enter Team of Driver (as it is the unique attribute)
     */
    @Override
    public void removeADriver() {

        boolean driverExists = false;
        while (!(driverExists)){
            System.out.println("Enter the Driver's Team of the Driver you wish to remove: ");
            String teams = input.next();
            for (int i=0; i < DriverStatistics.size(); i++) {
                Formula1Driver formula1 = DriverStatistics.get(i);
                if (teams.equalsIgnoreCase(formula1.getTeam())){
                    DriverStatistics.remove(i);
                    driverExists = true;
                }
            }
        }

    }

    /**
     * Method used to Change a Team's Driver
     * User is required to enter Team of Driver (as it is the unique attribute)
     * Once Team is located user is required to enter New Driver's Name and Location
     */
    @Override
    public void changeTeamDriver() {

        boolean driverExists = false;
        while (!(driverExists)){
            System.out.println("Enter the Driver's Team of the Driver you wish to Change: ");
            String team = input.next();
            for (int i=0; i < DriverStatistics.size(); i++) {
                Formula1Driver formula1 = DriverStatistics.get(i);
                if (team.equalsIgnoreCase(formula1.getTeam())){
                    System.out.println("Enter the New Driver's Name: ");
                    String newDriverName = input.next();
                    System.out.println("Enter the New Driver's Location: ");
                    String newDriverLocation = input.next();
                    formula1.setName(newDriverName);
                    formula1.setLocation(newDriverLocation);
                    DriverStatistics.set(i,formula1);
                    driverExists = true;

                }
            }

        }

    }

    /**
     * Method used to Display a Specific Drivers Statistics
     * User is required to enter Team of Driver (as it is the unique attribute)
     * Once Team is located all the details of Driver is Displayed to the user
     */
    @Override
    public void displaySpecificDriversStatistics() {

        boolean driverExists = false;
        while (!(driverExists)){
            System.out.println("Enter the Driver's Team of the Driver you wish to display: ");
            String teams = input.next();
            for (int i=0; i < DriverStatistics.size(); i++) {
                Formula1Driver formula1 = DriverStatistics.get(i);
                if (teams.equalsIgnoreCase(formula1.getTeam())){
                    System.out.println("Driver Name: " + formula1.getName() + '\n' + "Team: " + formula1.getTeam() + '\n' + "Country: " + formula1.getLocation() + '\n'
                            +"Points: " + formula1.getNumOfPoints() + '\n' + "Number of 1st Place: " + formula1.getNumOfFirst() + '\n' + "Number of 2nd Place: "
                            + formula1.getNumOfSecond() + '\n' + "Number of 3rd Place: " + formula1.getNumOfThird()
                            + '\n' + "Number of Races: " + formula1.getNumOfRaces());
                    driverExists = true;
                }
            }
        }
    }

    /**
     * Method used to Display all the Drivers Statistics to the User
     * Displays using a Table format on the Console
     * Displays the details formatted by Points and Number of First Place Finishes
     */
    @Override
    public void displayAllDriversStatistics() {
        Table F1ChampionshipTable = new Table();
        F1ChampionshipTable.setShowVerticalLines(true);
        F1ChampionshipTable.setHeaders("Name", "Team", "Country", "Points", "1st Places", "2nd Places", "3rd Places", "Number of Races");
        Collections.sort(DriverStatistics);
        for (int i=0; i < DriverStatistics.size(); i++){
            Formula1Driver formula1 = DriverStatistics.get(i);
            F1ChampionshipTable.addRow(String.valueOf(formula1.getName()), String.valueOf(formula1.getTeam()), String.valueOf(formula1.getLocation()),
                    String.valueOf(formula1.getNumOfPoints()), String.valueOf(formula1.getNumOfFirst()),
                    String.valueOf(formula1.getNumOfSecond()), String.valueOf(formula1.getNumOfThird()),
                    String.valueOf(formula1.getNumOfRaces()));
        }

        F1ChampionshipTable.print();


    }

    /**
     * Method used to add a Race to Championship
     * A loop is used to Individually update the drivers
     * Details of the Race are added to an Arraylist of Races
     */
    @Override
    public void addARace() {
        ArrayList<Formula1Driver> DriversOfRace = new ArrayList<>();
        System.out.println("Enter the Race Date: ");
        String DateofRace = input.next();
        System.out.println("Enter the number of Teams participationg the race: ");
        int driverCounter = input.nextInt();
        boolean driverHere = false;

        for (int i=1; i < driverCounter+1; i++){

            System.out.println("Enter the Team which got Number" + i  + " Place: ");
            String teamPos = input.next();
            Formula1Driver formula1 = null;
            //updated Driver
            for (int j=0; j < DriverStatistics.size(); j++){
                formula1 = DriverStatistics.get(j);
                if (teamPos.equalsIgnoreCase(formula1.getTeam())){
                    driverHere = true;
                    formula1.setNumOfRaces(formula1.getNumOfRaces()+1);
                    if (i == 1){
                        formula1.setNumOfFirst(formula1.getNumOfFirst()+1);
                    } else if (i == 2){
                        formula1.setNumOfSecond(formula1.getNumOfSecond()+1);
                    } else if (i == 3){
                        formula1.setNumOfThird(formula1.getNumOfThird()+1);
                    }
                    formula1.setNumOfPoints(formula1.getNumOfPoints()+Points[i]);
                    DriversOfRace.add(formula1);
                }
            }
            if (!driverHere){
                System.out.println("The Team you've entered is incorrect, Please Enter the Team Name again!");
                i--;
            }

        }
        Races newRace = new Races();
        newRace.setDate(DateofRace);
        newRace.setRaceEndPositions(DriversOfRace);
        RacesList.add(newRace);

    }

    /**
     * Method used to save the Details of Driver Statistics in the Championship into a Text Document
     */
    @Override
    public void saveDetailstoTxt() {
        try {

            FileWriter championshipfile = new FileWriter("Championship.txt");

            for (int i=0; i < DriverStatistics.size(); i++){
                Formula1Driver f1 = DriverStatistics.get(i);
                championshipfile.write("Driver Name: " + f1.getName() + ", Team: " + f1.getTeam() + ", Country: " +
                        f1.getLocation() + ", Points: " + f1.getNumOfPoints()+ ", Number of 1st Place: " + f1.getNumOfFirst()
                        + ", Number of 2nd Place: " + f1.getNumOfSecond() + ", Number of 3rd Place: " + f1.getNumOfThird()
                        + ", Number of Races: " + f1.getNumOfRaces() + '\n');
            }
            championshipfile.close();
            System.out.println("Championship.txt has been created!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Method used to read the details from the Text Document
     * The program is updated when program is Re-run
     */
    @Override
    public void readDetailsfromTxt() {
        Scanner sc = null;
        try {
            sc = new Scanner(new File("Championship.txt"));

            while (sc.hasNext()){

                String newLine = sc.nextLine();
                newLine = newLine.replace("Driver Name:","");
                newLine = newLine.replace("Team:","");
                newLine = newLine.replace("Country:","");
                newLine = newLine.replace("Points:","");
                newLine = newLine.replace("Number of 1st Place:","");
                newLine = newLine.replace("Number of 2nd Place:","");
                newLine = newLine.replace("Number of 3rd Place:","");
                newLine = newLine.replace("Number of Races:","");
                newLine = newLine.replace(" ","");
                ArrayList<String> seperatedWords = new ArrayList<String>(Arrays.asList(newLine.split(",")));
                Formula1Driver formula1D = new Formula1Driver();
                for (int i=0 ; i<seperatedWords.size();  i++){

                    formula1D.setName(seperatedWords.get(0));
                    formula1D.setTeam(seperatedWords.get(1));
                    formula1D.setLocation(seperatedWords.get(2));
                    formula1D.setNumOfPoints(Integer.parseInt(seperatedWords.get(3)));
                    formula1D.setNumOfFirst(Integer.parseInt(seperatedWords.get(4)));
                    formula1D.setNumOfSecond(Integer.parseInt(seperatedWords.get(5)));
                    formula1D.setNumOfThird(Integer.parseInt(seperatedWords.get(6)));
                    formula1D.setNumOfRaces(Integer.parseInt(seperatedWords.get(7)));

                }
                DriverStatistics.add(formula1D);

            }

            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            //e.printStackTrace();
        }

    }

    /**
     * Method used to display the Graphical User Interface to the User
     */
    @Override
    public void showGUI() {

        GUI gui = new GUI(DriverStatistics, RacesList);

    }

}