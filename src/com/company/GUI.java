package com.company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


public class GUI extends JFrame {

    //Frames
    JFrame mainDriverTableFrame;
    JFrame newRaceFrame;
    JFrame raceDetailsTableFrame;

    //Panels
    JPanel panelNum1;
    JPanel panelNum2;
    JPanel panelNum3;
    JPanel panelNum4;
    JPanel panelNum5;
    JPanel driverDetailsPanel;
    JPanel newRacePanel;
    JPanel raceDetailsTablePanel;
    JPanel driverRaceDetailsPanel;

    //Tables and Heading
    String[] MainHeading;
    JTable driverDetailsTable;
    JTable newRacesTable;
    JTable raceDetailsTable;
    JTable driverRaceDetailsTable;

    //Table Model
    DefaultTableModel driverDetailsTableModel;
    DefaultTableModel newRaceModel;
    DefaultTableModel raceDetailsTableModel;
    DefaultTableModel driverRaceDetailsTableModel;


    Formula1ChampionshipManager F1Manager;

    /**
     * The following links were referenced when forming this entire class - jframes, jtable, jpanel, jlabel, jbutton, layout, actionListeners etc.
     * https://www.tutorialspoint.com/swing/swing_controls.htm
     * https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html
     * https://docs.oracle.com/javase/7/docs/api/javax/swing/table/DefaultTableModel.html
     * https://www.tutorialspoint.com/how-to-create-defaulttablemodel-which-is-an-implementation-of-tablemodel
     * https://stackoverflow.com/questions/284899/how-do-you-add-an-actionlistener-onto-a-jbutton-in-java
     * https://stackoverflow.com/questions/14704719/my-jframe-does-not-refresh
     */

    public GUI(ArrayList<Formula1Driver> Driverstats, ArrayList<Races> RacesList) {
        F1Manager = new Formula1ChampionshipManager(Driverstats, RacesList);

        //Making 3 Frames, setting up the first frame
        mainDriverTableFrame = new JFrame();
        newRaceFrame = new JFrame();
        raceDetailsTableFrame = new JFrame();

        newRaceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        raceDetailsTableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        mainDriverTableFrame.setSize(1000, 800);
        mainDriverTableFrame.setTitle("Formula1 CHAMPIONSHIP");
        mainDriverTableFrame.setLayout(null);
        mainDriverTableFrame.setVisible(true);
        mainDriverTableFrame.revalidate();
        mainDriverTableFrame.repaint();

        //Setting up the panels and tables for Frame 1
        panelNum1 = new JPanel();
        panelNum1.setBackground(new Color(0x54ae2d));
        panelNum1.setBounds(0, 0, 1000, 250);
        panelNum1.setLayout(new BorderLayout());

        driverDetailsPanel = new JPanel();
        driverDetailsPanel.setBackground(new Color(0xf4dc27));
        driverDetailsPanel.setBounds(0, 250, 1000, 400);
        driverDetailsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Driver Statistics", TitledBorder.CENTER, TitledBorder.TOP, Font.getFont("Arial"), Color.getColor("0xFFFFFF")));
        driverDetailsPanel.setLayout(new BorderLayout());

        panelNum2 = new JPanel();
        panelNum2.setBackground(new Color(0x54ae2d));
        panelNum2.setBounds(0, 650, 1000, 100);
        panelNum2.setLayout(new FlowLayout());


        JLabel label = new JLabel("Formula 1 Championship 2021");
        label.setBounds(40, 50, 50, 70);
        label.setFont(new Font("Monserrat", Font.ITALIC | Font.BOLD, 40));
        label.setForeground(new Color(0xFFFFFF));
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setIconTextGap(-200);

        panelNum1.add(label);

        displayDriverDetailsTable();
        sortPointsButton();
        sortPlacesButton();

        mainDriverTableFrame.add(panelNum1);
        mainDriverTableFrame.add(panelNum2);
        mainDriverTableFrame.add(driverDetailsPanel);

        //Setting up the newRaceFrame to add a race
        newRaceFrame.setSize(1000, 800);
        newRaceFrame.setTitle("Formula 1 Championship New Race");
        newRaceFrame.setLayout(null);
        newRaceFrame.setVisible(false);

        randomRaceButton();
        probabilityRaceButton();

        raceDetailsTableFrame.setSize(1000, 800);
        raceDetailsTableFrame.setTitle("Formula 1 Championship Race Table");
        raceDetailsTableFrame.setLayout(null);
        raceDetailsTableFrame.setVisible(false);
        viewRaceDetailsTableButton();
    }

    /**
     * Displays all the driver statistics in a Table
     */
    public void displayDriverDetailsTable() {
        //JTable Columns
        MainHeading = new String[]{"Name of Driver", "Team Name", "Location", "First Positions", "Second Positions", "Third Positions", "Total Points", "Number Of Participated Races"};

        driverDetailsTableModel = new DefaultTableModel(0, 0);
        driverDetailsTable = new JTable(driverDetailsTableModel);
        driverDetailsTableModel.setColumnIdentifiers(MainHeading);
        driverDetailsTable.setModel(driverDetailsTableModel);

        //Adding data to JTable
        makeDriverDetailsTable();
        driverDetailsTable.setBounds(0, 250, 1000, 400);
        driverDetailsTable.setOpaque(true);
        JScrollPane scrollpane1 = new JScrollPane(driverDetailsTable);
        scrollpane1.setBounds(0, 250, 1000, 400);
        driverDetailsPanel.add(scrollpane1);
    }

    /**
     * Button that sort the Driver Statistcs table in Ascending order using Points
     */
    public void sortPointsButton() {
        JButton btn1 = new JButton("Ascending order of Points");
        btn1.setBounds(200, 20, 40, 15);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Comparator<Formula1Driver> comparator = new Comparator<Formula1Driver>() {
                    public int compare(Formula1Driver driver1, Formula1Driver driver2) {
                        if (driver1.getNumOfPoints() > driver2.getNumOfPoints()) {
                            return 1;
                        } else if (driver1.getNumOfPoints() == driver2.getNumOfPoints()) {
                            if (driver1.getNumOfFirst() > driver2.getNumOfFirst()) {
                                return 1;
                            } else {
                                return -1;
                            }

                        } else {
                            return -1;
                        }
                    }
                };
                F1Manager.DriverStatistics.sort(comparator);
                makeDriverDetailsTable();
            }
        });
        panelNum2.add(btn1);
    }

    /**
     * Button that sort the Driver Statistcs table in Descending order using Number of First Place Finishes
     */
    public void sortPlacesButton() {
        JButton btn2 = new JButton("Descending to First Position");
        btn2.setBounds(250, 20, 40, 15);
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Comparator<Formula1Driver> comparator = new Comparator<Formula1Driver>() {
                    public int compare(Formula1Driver driver1, Formula1Driver driver2) {
                        if (driver1.getNumOfFirst() > driver2.getNumOfFirst()) {
                            return -1;
                        } else {
                            return 1;
                        }
                    }
                };
                F1Manager.DriverStatistics.sort(comparator);
                makeDriverDetailsTable();
            }
        });
        panelNum2.add(btn2);
    }

    /**
     * Setting up the Driver details to be added to the JTable
     */
    public void makeDriverDetailsTable() {
        driverDetailsTableModel.setRowCount(0);
        for (int x = 0; x < F1Manager.DriverStatistics.size(); x++) {
            Object[] data = new Object[]{
                    F1Manager.DriverStatistics.get(x).getName(),
                    F1Manager.DriverStatistics.get(x).getTeam(),
                    F1Manager.DriverStatistics.get(x).getLocation(),
                    F1Manager.DriverStatistics.get(x).getNumOfFirst(),
                    F1Manager.DriverStatistics.get(x).getNumOfSecond(),
                    F1Manager.DriverStatistics.get(x).getNumOfThird(),
                    F1Manager.DriverStatistics.get(x).getNumOfPoints(),
                    F1Manager.DriverStatistics.get(x).getNumOfRaces()
            };
            driverDetailsTableModel.addRow(data);
        }
    }

    /**
     * Button to generate and display a random Race and updates existing drivers
     */
    public void randomRaceButton() {
        JButton randomRaceButton = new JButton("Add Random Race");
        randomRaceButton.setBounds(300, 20, 40, 15);
        randomRaceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Races newF1Race = calculateRandomRace();
                String title = "Added New Random Race";
                updateDriverDetails(newF1Race);
                F1Manager.RacesList.add(newF1Race);
                displayRaceDetails(newF1Race, title);
                makeDriverDetailsTable();
            }
        });
        panelNum2.add(randomRaceButton);
    }

    /**
     * Button to generate and displays a race based on probability and updates existing drivers
     */
    public void probabilityRaceButton() {
        JButton statRaceButton = new JButton("Add Probability Race");
        statRaceButton.setBounds(350, 20, 40, 15);
        statRaceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Races newF1Race = calculateStatRace();
                String title = "Added New Probability Race";
                updateDriverDetails(newF1Race);
                F1Manager.RacesList.add(newF1Race);
                displayRaceDetails(newF1Race, title);
                makeDriverDetailsTable();
            }
        });
        panelNum2.add(statRaceButton);
    }

    /**
     * Method used to make a search bar
     */
    public void searchBar() {
        JTextField textField = new JTextField(20);
        textField.setSize(200,24);
        JButton searchButton = new JButton("Search");
        searchButton.setBounds(200, 20, 40, 15);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String getValue = textField.getText();
                if (getValue!=null && !getValue.isEmpty()) {
                    raceDetailsTableFrame.remove(raceDetailsTablePanel);
                    //raceTableFrame.repaint();
                    drawDriverRaceTable(getValue);
                }
                textField.setText("");
            }
        });

        JButton resetButton = new JButton("Reset");
        resetButton.setBounds(250, 20, 40, 15);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                raceDetailsTableFrame.remove(driverRaceDetailsPanel);
                //raceTableFrame.repaint();
                makeRaceDetailsTable();
            }
        });
        panelNum5.add(textField);
        panelNum5.add(searchButton);
        panelNum5.add(resetButton);
    }

    public String getRaceName(){
        String nameTemp = "Race";
        return nameTemp+ " " +(1+F1Manager.RacesList.size());
    }

    /**
     * Method used to calculate details of Random Race
     * Includes Names, Date, Start and End positions of Drivers
     * @return
     */
    public Races calculateRandomRace() {
        Races newF1Race = new Races();
        Dates newRaceDate = new Dates();
        String name = getRaceName();
        newF1Race.setName(name);
        newF1Race.setDate(newRaceDate.getRandomDate());
        ArrayList<Formula1Driver> startingPositions = new ArrayList<>(F1Manager.DriverStatistics);
        ArrayList<Formula1Driver> endingPositions = new ArrayList<>(F1Manager.DriverStatistics);
        Collections.shuffle(startingPositions);
        Collections.shuffle(endingPositions);
        newF1Race.setRaceStartPositions(startingPositions);
        newF1Race.setRaceEndPositions(endingPositions);
        return newF1Race;
    }

    /**
     * Method used to calculate details of Race based on probability
     * Includes Name, Date, Start and End Positions
     * End Positions determined by probability based on details provided by the Coursework Specification
     * Probability found by generating random number upto 100
     * @return
     */
    public Races calculateStatRace() {
        Races newF1Race = new Races();
        Dates newRaceDate = new Dates();
        String name = getRaceName();
        newF1Race.setName(name);
        newF1Race.setDate(newRaceDate.getRandomDate());
        ArrayList<Formula1Driver> startingPositions = new ArrayList<>(F1Manager.DriverStatistics);
        ArrayList<Formula1Driver> endingPositionsTemp = new ArrayList<>(F1Manager.DriverStatistics);
        Collections.shuffle(startingPositions);
        int probability = new Random().nextInt(100);
        int winningPosition;
        if (probability < 40) {
            winningPosition = 1;
        } else if (probability < 70) {
            winningPosition = 2;
        } else if (probability < 80) {
            winningPosition = 3;
        } else if (probability < 90) {
            winningPosition = 4;
        } else if (probability < 92) {
            winningPosition = 5;
        } else if (probability < 94) {
            winningPosition = 6;
        } else if (probability < 96) {
            winningPosition = 7;
        } else if (probability < 98) {
            winningPosition = 8;
        } else {
            winningPosition = 9;
        }
        if (winningPosition > F1Manager.DriverStatistics.size()){
            winningPosition = F1Manager.DriverStatistics.size();
        }
        Formula1Driver Driver1 = startingPositions.get(winningPosition-1);
        for (int i = 0 ; i < endingPositionsTemp.size(); i++){
            if (endingPositionsTemp.get(i).getTeam().equals(Driver1.getTeam())){
                endingPositionsTemp.remove(i);
            }
        }
        ArrayList<Formula1Driver> endingPositions = new ArrayList<>();
        endingPositions.add(Driver1);
        endingPositions.addAll(endingPositionsTemp);
        newF1Race.setRaceStartPositions(startingPositions);
        newF1Race.setRaceEndPositions(endingPositions);

        return newF1Race;
    }

    /**
     * Method that updates the DriverList when a new race is added
     * @param newF1Race
     */
    public void updateDriverDetails(Races newF1Race) {
        int Points [] = {25, 18, 15, 12, 10, 8, 6, 4, 2, 1};
        for (int i=0; i<newF1Race.RaceEndPositions.size(); i++) {
            Formula1Driver driver1 = newF1Race.RaceEndPositions.get(i);
            for (int j=0; j<F1Manager.DriverStatistics.size(); j++) {
                Formula1Driver driver2 = F1Manager.DriverStatistics.get(j);
                if (driver1.getTeam().equals(driver2.getTeam())) {
                    driver2.setNumOfRaces(driver2.getNumOfRaces()+1);
                    driver2.setNumOfPoints(driver2.getNumOfPoints()+Points[i]);
                    if (i == 0) {
                        driver2.setNumOfFirst(driver2.getNumOfFirst()+1);
                    } else if (i == 1) {
                        driver2.setNumOfSecond(driver2.getNumOfSecond()+1);
                    } else if (i == 2) {
                        driver2.setNumOfThird(driver2.getNumOfThird()+1);
                    }
                    F1Manager.DriverStatistics.set(j, driver2);
                }
            }
        }
    }

    /**
     * Method to display the details of the New Generated Race
     * New generated race can be a Random Race or a Probability based Race
     * @param newF1Race
     * @param title
     */
    public void displayRaceDetails(Races newF1Race, String title) {
        newRaceFrame.setVisible(true);
        panelNum3 = new JPanel();
        panelNum3.setBackground(new Color(0x54ae2d));
        panelNum3.setBounds(0, 0, 1000, 250);
        panelNum3.setLayout(new FlowLayout());

        newRacePanel = new JPanel();
        newRacePanel.setBackground(new Color(0xf4dc27));
        newRacePanel.setBounds(0, 250, 1000, 450);
        newRacePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Driver Statistics", TitledBorder.CENTER, TitledBorder.TOP, Font.getFont("Monserrat"), Color.getColor("0xFFFFFF")));
        newRacePanel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Race Name: " + newF1Race.getName() + "  Date: " + newF1Race.getDate());
        label.setBounds(40, 50, 50, 70);
        label.setFont(new Font("Monserrat", Font.ITALIC | Font.BOLD, 40));
        label.setForeground(new Color(0xFFFFFF));
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setIconTextGap(-200);

        //JTable Column
        MainHeading = new String[]{"Driver Name", "Starting Position", "Ending Position", "Team Name"};
        newRaceModel = new DefaultTableModel(0, 0);
        newRacesTable = new JTable(newRaceModel);
        newRaceModel.setColumnIdentifiers(MainHeading);
        newRacesTable.setModel(newRaceModel);
        newRaceModel.setRowCount(0);

        //Arraylist to store the ending positions using starting positions of the Driver
        ArrayList<String> endingPos = new ArrayList<>();
        for (int i = 0; i < newF1Race.getRacePositions().size(); i++) {
            for (int j = 0; j < newF1Race.getRaceEndPositions().size(); j++) {
                if (newF1Race.getRacePositions().get(i).getTeam().equals(newF1Race.getRaceEndPositions().get(j).getTeam())) {
                    endingPos.add(Integer.toString(j + 1));
                }
            }
        }

        //Adding data to JTable
        for (int i = 0; i < newF1Race.getRacePositions().size(); i++) {
            Object[] raceData = new Object[]{
                    newF1Race.getRacePositions().get(i).getName(),
                    i + 1,
                    endingPos.get(i),
                    newF1Race.getRacePositions().get(i).getTeam()
            };
            newRaceModel.addRow(raceData);
        }
        newRacesTable.setBounds(0, 250, 1000, 400);
        newRacesTable.setOpaque(true);
        JScrollPane scrollpane2 = new JScrollPane(newRacesTable);
        scrollpane2.setBounds(0, 250, 1000, 400);
        newRacePanel.add(scrollpane2);
        panelNum3.add(label);
        newRaceFrame.add(panelNum3);
        newRaceFrame.add(newRacePanel);
        newRaceFrame.revalidate();
        newRaceFrame.repaint();
    }

    /**
     * Button to display the New Frame with table of races and a searchbar for user to search drivers
     */
    public void viewRaceDetailsTableButton() {
        JButton viewRaceTableButton = new JButton("View Race Tabel");
        viewRaceTableButton.setBounds(400, 20, 40, 15);
        viewRaceTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewRaceDetailsTableFrame();
            }
        });
        panelNum2.add(viewRaceTableButton);
    }

    /**
     * Method used to Add all the Race Details
     * @param searchName
     */
    public void drawDriverRaceTable(String searchName) {
        ArrayList<Races> tempRaces = new ArrayList<>(F1Manager.RacesList);
        ArrayList<DriversnRaces> driversRace = new ArrayList<>();
        for (Races race: tempRaces) {
            ArrayList<Formula1Driver> searchDriver = race.getRaceEndPositions();
            for (Formula1Driver driver: searchDriver){
                if (driver.getName().contains(searchName)){
                    DriversnRaces raceDriver =new DriversnRaces();
                    raceDriver.setDriverName(driver.getName());
                    raceDriver.setRaceDate(race.getDate());
                    raceDriver.setRaceName(race.getName());
                    raceDriver.setStartPosition(race.getRacePositions().indexOf(driver)+1);
                    raceDriver.setEndPosition(race.getRaceEndPositions().indexOf(driver)+1);
                    driversRace.add(raceDriver);

                }
            }
        }
        driverRaceDetailsTableModel.setRowCount(0);
        for (int i = 0; i < driversRace.size(); i++) {
            Object[] data = new Object[]{
                    driversRace.get(i).getDriverName(),
                    driversRace.get(i).getRaceName(),
                    driversRace.get(i).getRaceDate(),
                    driversRace.get(i).getStartPosition(),
                    driversRace.get(i).getEndPosition()

            };
            driverRaceDetailsTableModel.addRow(data);
        }
        raceDetailsTablePanel.setVisible(false);
        driverRaceDetailsPanel.setVisible(true);
        raceDetailsTableFrame.remove(raceDetailsTablePanel);
        raceDetailsTableFrame.add(driverRaceDetailsPanel);
        raceDetailsTableFrame.revalidate();
        raceDetailsTableFrame.repaint();
    }

    public void makeRaceDetailsTable() {
        raceDetailsTableModel.setRowCount(0);
        for (int i = 0; i < F1Manager.RacesList.size(); i++) {
            Object[] data = new Object[]{
                    F1Manager.RacesList.get(i).getName(),
                    F1Manager.RacesList.get(i).getDate(),
                    F1Manager.RacesList.get(i).getRaceEndPositions().size()
            };
            raceDetailsTableModel.addRow(data);
        }
        driverRaceDetailsPanel.setVisible(false);
        raceDetailsTablePanel.setVisible(true);
        raceDetailsTableFrame.remove(driverRaceDetailsPanel);
        raceDetailsTableFrame.add(raceDetailsTablePanel);
        raceDetailsTableFrame.revalidate();
        raceDetailsTableFrame.repaint();
    }

    /**
     * Method used to Display the Race Table Frame
     */
    public void viewRaceDetailsTableFrame() {
        raceDetailsTableFrame.setVisible(true);
        panelNum4 = new JPanel();
        panelNum4.setBackground(new Color(0x54ae2d));
        panelNum4.setBounds(0, 0, 1000, 250);
        panelNum4.setLayout(new FlowLayout());

        raceDetailsTablePanel = new JPanel();
        raceDetailsTablePanel.setBackground(new Color(0xf4dc27));
        raceDetailsTablePanel.setBounds(0, 250, 1000, 400);
        raceDetailsTablePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Race Details", TitledBorder.CENTER, TitledBorder.TOP, Font.getFont("Monserrat"), Color.getColor("0xFFFFFF")));
        raceDetailsTablePanel.setLayout(new BorderLayout());

        driverRaceDetailsPanel = new JPanel();
        driverRaceDetailsPanel.setBackground(new Color(0xf4dc27));
        driverRaceDetailsPanel.setBounds(0, 250, 1000, 400);
        driverRaceDetailsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Driver Search Results", TitledBorder.CENTER, TitledBorder.TOP, Font.getFont("Monserrat"), Color.getColor("0xFFFFFF")));
        driverRaceDetailsPanel.setLayout(new BorderLayout());

        panelNum5 = new JPanel();
        panelNum5.setBackground(new Color(0x54ae2d));
        panelNum5.setBounds(0, 650, 1000, 100);
        panelNum5.setLayout(new FlowLayout());

        searchBar();

        // TODO format labels
        JLabel label = new JLabel("View Race Details");
        label.setBounds(40, 50, 50, 70);
        label.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 40));
        label.setForeground(new Color(0xFFFFFF));
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setIconTextGap(-200);

        //JTable Column
        MainHeading = new String[]{"Race Name", "Race Date", "Number of Drivers"};
        raceDetailsTableModel = new DefaultTableModel(0, 0);
        raceDetailsTable = new JTable(raceDetailsTableModel);
        raceDetailsTableModel.setColumnIdentifiers(MainHeading);
        raceDetailsTable.setModel(raceDetailsTableModel);

        String[] raceDetailsTableHeading = new String[]{"Race Name", "Race Date", "Number of Drivers"};
        raceDetailsTableModel = new DefaultTableModel(0, 0);
        raceDetailsTable = new JTable(raceDetailsTableModel);
        raceDetailsTableModel.setColumnIdentifiers(raceDetailsTableHeading);
        raceDetailsTable.setModel(raceDetailsTableModel);

        String[] driverRaceDetailsTableHeading = new String[]{"Driver Name", "Race Name", "Race Date", "Starting Position", "Ending Position"};
        driverRaceDetailsTableModel = new DefaultTableModel(0, 0);
        driverRaceDetailsTable = new JTable(driverRaceDetailsTableModel);
        driverRaceDetailsTableModel.setColumnIdentifiers(driverRaceDetailsTableHeading);
        driverRaceDetailsTable.setModel(driverRaceDetailsTableModel);

        Comparator<Races> comparator = sortRacebyDate();
        F1Manager.RacesList.sort(comparator);
        makeRaceDetailsTable();

        raceDetailsTable.setBounds(0, 250, 1000, 400);
        raceDetailsTable.setOpaque(true);
        JScrollPane scrollpane3 = new JScrollPane(raceDetailsTable);
        scrollpane3.setBounds(0, 250, 1000, 400);
        raceDetailsTablePanel.add(scrollpane3);

        driverRaceDetailsTable.setBounds(0, 250, 1000, 400);
        driverRaceDetailsTable.setOpaque(true);
        JScrollPane scrollpane4 = new JScrollPane(driverRaceDetailsTable);
        scrollpane4.setBounds(0, 250, 1000, 400);
        driverRaceDetailsPanel.add(scrollpane4);

        panelNum4.add(label);
        raceDetailsTableFrame.add(panelNum4);
        //raceTableFrame.add(raceTablePanel);
        raceDetailsTableFrame.add(panelNum5);
    }

    /**
     * Method used to sort the Races by Date in Ascending order
     * @return
     */
    public Comparator<Races> sortRacebyDate() {
        Comparator<Races> comparator = new Comparator<Races>() {
            public int compare(Races Race1, Races Race2) {
                try {
                    Date Date1 = new SimpleDateFormat("dd/MM/yyyy").parse(Race1.getDate());
                    Date Date2 = new SimpleDateFormat("dd/MM/yyyy").parse(Race2.getDate());
                    if (Date1.compareTo(Date2) > 0) {
                        return 1;
                    } else if (Date1.compareTo(Date2)< 0){
                        return -1;
                    } else {
                        return 0;
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                    return 0;
                }

            }
        };
        return comparator;
    }

}














