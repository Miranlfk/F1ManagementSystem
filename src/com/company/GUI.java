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
    JFrame driverTableFrame;
    JFrame addNewRaceFrame;
    JFrame raceTableFrame;

    JPanel panel1;
    JPanel panel2;
    JPanel panel3;
    JPanel panel4;
    JPanel panel5;
    JPanel driverTablePanel;
    JPanel newRaceDetailsPanel;
    JPanel raceTablePanel;
    JPanel driverRaceTablePanel;

    String[] Heading;
    JTable driverTable;
    JTable newRaceDetailsTable;
    JTable raceTable;
    JTable driverRaceTable;

    DefaultTableModel driverTableModel;
    DefaultTableModel newRaceDetailsModel;
    DefaultTableModel raceTableModel;
    DefaultTableModel driverRaceTableModel;


    Formula1ChampionshipManager F1Manager;

    /**
     * The following links were referenced when forming this entire class - jframes, jtable, jlabel, jbutton, layout, actionListeners etc.
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
        driverTableFrame = new JFrame();
        addNewRaceFrame = new JFrame();
        raceTableFrame = new JFrame();

        addNewRaceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        raceTableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        driverTableFrame.setSize(1000, 800);
        driverTableFrame.setTitle("Formula1 CHAMPIONSHIP");
        driverTableFrame.setLayout(null);
        driverTableFrame.setVisible(true);
        driverTableFrame.revalidate();
        driverTableFrame.repaint();

        //Setting up the panels and tables for Frame 1
        panel1 = new JPanel();
        panel1.setBackground(new Color(0x54ae2d));
        panel1.setBounds(0, 0, 1000, 250);
        panel1.setLayout(new BorderLayout());

        driverTablePanel = new JPanel();
        driverTablePanel.setBackground(new Color(0xf4dc27));
        driverTablePanel.setBounds(0, 250, 1000, 400);
        driverTablePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Driver Statistics", TitledBorder.CENTER, TitledBorder.TOP, Font.getFont("Arial"), Color.getColor("0xFFFFFF")));
        driverTablePanel.setLayout(new BorderLayout());

        panel2 = new JPanel();
        panel2.setBackground(new Color(0x54ae2d));
        panel2.setBounds(0, 650, 1000, 100);
        panel2.setLayout(new FlowLayout());


        JLabel label = new JLabel("Formula 1 Championship 2021");
        label.setBounds(40, 50, 50, 70);
        label.setFont(new Font("Monserrat", Font.ITALIC | Font.BOLD, 40));
        label.setForeground(new Color(0xFFFFFF));
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setIconTextGap(-200);

        panel1.add(label);

        displayDriverTable();
        addSortPointButton();
        addSortPlacesButton();

        driverTableFrame.add(panel1);
        driverTableFrame.add(panel2);
        driverTableFrame.add(driverTablePanel);

        //Setting up the addNewRaceFrame to add a race
        addNewRaceFrame.setSize(1000, 800);
        addNewRaceFrame.setTitle("Formula 1 Championship New Race");
        addNewRaceFrame.setLayout(null);
        addNewRaceFrame.setVisible(false);

        addRandomRaceButton();
        addStatRaceButton();

        raceTableFrame.setSize(1000, 800);
        raceTableFrame.setTitle("Formula 1 Championship Race Table");
        raceTableFrame.setLayout(null);
        raceTableFrame.setVisible(false);
        addViewRaceTableButton();
    }

    /**
     * Displays all the driver statistics in a Table
     */
    public void displayDriverTable() {
        //JTable Columns
        Heading = new String[]{"Name of Driver", "Team Name", "Location", "First Positions", "Second Positions", "Third Positions", "Total Points", "Number Of Participated Races"};

        driverTableModel = new DefaultTableModel(0, 0);
        driverTable = new JTable(driverTableModel);
        driverTableModel.setColumnIdentifiers(Heading);
        driverTable.setModel(driverTableModel);

        //Adding data to JTable
        drawDriverTable();
        driverTable.setBounds(0, 250, 1000, 400);
        driverTable.setOpaque(true);
        JScrollPane sp1 = new JScrollPane(driverTable);
        sp1.setBounds(0, 250, 1000, 400);
        driverTablePanel.add(sp1);
    }

    /**
     * Button that sort the Driver Statistcs table in Ascending order using Points
     */
    public void addSortPointButton() {
        JButton btn1 = new JButton("Ascending Points");
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
                drawDriverTable();
            }
        });
        panel2.add(btn1);
    }

    /**
     * Button that sort the Driver Statistcs table in Descending order using Number of First Place Finishes
     */
    public void addSortPlacesButton() {
        JButton btn2 = new JButton("Descending to FirstPosition");
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
                drawDriverTable();
            }
        });
        panel2.add(btn2);
    }

    /**
     * Setting up the Driver details to be added to the JTable
     */
    public void drawDriverTable() {
        driverTableModel.setRowCount(0);
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
            driverTableModel.addRow(data);
        }
    }

    /**
     * Button to generate and display a random Race and updates existing drivers
     */
    public void addRandomRaceButton() {
        JButton randomRaceButton = new JButton("Add Random Race");
        randomRaceButton.setBounds(300, 20, 40, 15);
        randomRaceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Races newRace = calculateRandomRace();
                String title = "Added New Random Race";
                updateDriverDetails(newRace);
                F1Manager.RacesList.add(newRace);
                displayNewRaceRetails(newRace, title);
                drawDriverTable();
            }
        });
        panel2.add(randomRaceButton);
    }

    /**
     * Button to generate and displays a race based on probability and updates existing drivers
     */
    public void addStatRaceButton() {
        JButton statRaceButton = new JButton("Add Stat Race");
        statRaceButton.setBounds(350, 20, 40, 15);
        statRaceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Races newRace = calculateStatRace();
                String title = "Added New Statistical Race";
                updateDriverDetails(newRace);
                F1Manager.RacesList.add(newRace);
                displayNewRaceRetails(newRace, title);
                drawDriverTable();
            }
        });
        panel2.add(statRaceButton);
    }

    /**
     * Method used to make a search bar
     */
    public void addSearchBar() {
        JTextField textField = new JTextField(20);
        textField.setSize(200,24);
        JButton searchButton = new JButton("Search");
        searchButton.setBounds(200, 20, 40, 15);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String getValue = textField.getText();
                if (getValue!=null && !getValue.isEmpty()) {
                    raceTableFrame.remove(raceTablePanel);
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
                raceTableFrame.remove(driverRaceTablePanel);
                //raceTableFrame.repaint();
                drawRaceTable();
            }
        });
        panel5.add(textField);
        panel5.add(searchButton);
        panel5.add(resetButton);
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
        Races newRace = new Races();
        Dates newDate = new Dates();
        String name = getRaceName();
        newRace.setName(name);
        newRace.setDate(newDate.getRandomDate());
        ArrayList<Formula1Driver> startingPositions = new ArrayList<>(F1Manager.DriverStatistics);
        ArrayList<Formula1Driver> endingPositions = new ArrayList<>(F1Manager.DriverStatistics);
        Collections.shuffle(startingPositions);
        Collections.shuffle(endingPositions);
        newRace.setRaceStartPositions(startingPositions);
        newRace.setRaceEndPositions(endingPositions);
        return newRace;
    }

    /**
     * Method used to calculate details of Race based on probability
     * Includes Name, Date, Start and End Positions
     * End Positions determined by probability based on details provided by the Coursework Specification
     * Probability found by generating random number upto 100
     * @return
     */
    public Races calculateStatRace() {
        Races newRace = new Races();
        Dates newDate = new Dates();
        String name = getRaceName();
        newRace.setName(name);
        newRace.setDate(newDate.getRandomDate());
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
        ArrayList<Formula1Driver> endingPositionsFinal = new ArrayList<>();
        endingPositionsFinal.add(Driver1);
        endingPositionsFinal.addAll(endingPositionsTemp);
        newRace.setRaceStartPositions(startingPositions);
        newRace.setRaceEndPositions(endingPositionsFinal);

        return newRace;
    }

    /**
     * Method that updates the DriverList when a new race is added
     * @param newRace
     */
    public void updateDriverDetails(Races newRace) {
        int Points [] = {25, 18, 15, 12, 10, 8, 6, 4, 2, 1};
        for (int i=0; i<newRace.RaceEndPositions.size(); i++) {
            Formula1Driver d1 = newRace.RaceEndPositions.get(i);
            for (int j=0; j<F1Manager.DriverStatistics.size(); j++) {
                Formula1Driver d2 = F1Manager.DriverStatistics.get(j);
                if (d1.getTeam().equals(d2.getTeam())) {
                    d2.setNumOfRaces(d2.getNumOfRaces()+1);
                    d2.setNumOfPoints(d2.getNumOfPoints()+Points[i]);
                    if (i == 0) {
                        d2.setNumOfFirst(d2.getNumOfFirst()+1);
                    } else if (i == 1) {
                        d2.setNumOfSecond(d2.getNumOfSecond()+1);
                    } else if (i == 2) {
                        d2.setNumOfThird(d2.getNumOfThird()+1);
                    }
                    F1Manager.DriverStatistics.set(j, d2);
                }
            }
        }
    }

    /**
     * Method to display the details of the New Generated Race
     * New generated race can be a Random Race or a Probability based Race
     * @param newRace
     * @param title
     */
    public void displayNewRaceRetails(Races newRace, String title) {
        addNewRaceFrame.setVisible(true);
        panel3 = new JPanel();
        panel3.setBackground(new Color(0x54ae2d));
        panel3.setBounds(0, 0, 1000, 250);
        panel3.setLayout(new FlowLayout());

        newRaceDetailsPanel = new JPanel();
        newRaceDetailsPanel.setBackground(new Color(0xf4dc27));
        newRaceDetailsPanel.setBounds(0, 250, 1000, 450);
        newRaceDetailsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Driver Statistics", TitledBorder.CENTER, TitledBorder.TOP, Font.getFont("Arial"), Color.getColor("0xFFFFFF")));
        newRaceDetailsPanel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Race Name: " + newRace.getName() + "  Date: " + newRace.getDate());
        label.setBounds(40, 50, 50, 70);
        label.setFont(new Font("Monserrat", Font.ITALIC | Font.BOLD, 40));
        label.setForeground(new Color(0xFFFFFF));
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setIconTextGap(-200);

        //JTable Column
        Heading = new String[]{"Driver Name", "Starting Position", "Ending Position", "Team Name"};
        newRaceDetailsModel = new DefaultTableModel(0, 0);
        newRaceDetailsTable = new JTable(newRaceDetailsModel);
        newRaceDetailsModel.setColumnIdentifiers(Heading);
        newRaceDetailsTable.setModel(newRaceDetailsModel);
        newRaceDetailsModel.setRowCount(0);

        //Arraylist to store the ending positions using starting positions of the Driver
        ArrayList<String> endingPos = new ArrayList<>();
        for (int i = 0; i < newRace.getRacePositions().size(); i++) {
            for (int j = 0; j < newRace.getRaceEndPositions().size(); j++) {
                if (newRace.getRacePositions().get(i).getTeam().equals(newRace.getRaceEndPositions().get(j).getTeam())) {
                    endingPos.add(Integer.toString(j + 1));
                }
            }
        }

        //Adding data to JTable
        for (int i = 0; i < newRace.getRacePositions().size(); i++) {
            Object[] raceData = new Object[]{
                    newRace.getRacePositions().get(i).getName(),
                    i + 1,
                    endingPos.get(i),
                    newRace.getRacePositions().get(i).getTeam()
            };
            newRaceDetailsModel.addRow(raceData);
        }
        newRaceDetailsTable.setBounds(0, 250, 1000, 400);
        newRaceDetailsTable.setOpaque(true);
        JScrollPane sp2 = new JScrollPane(newRaceDetailsTable);
        sp2.setBounds(0, 250, 1000, 400);
        newRaceDetailsPanel.add(sp2);
        panel3.add(label);
        addNewRaceFrame.add(panel3);
        addNewRaceFrame.add(newRaceDetailsPanel);
        addNewRaceFrame.revalidate();
        addNewRaceFrame.repaint();
    }

    /**
     * Button to display the New Frame with table of races and a searchbar for user to search drivers
     */
    public void addViewRaceTableButton() {
        JButton viewRaceTableButton = new JButton("View Race Tabel");
        viewRaceTableButton.setBounds(400, 20, 40, 15);
        viewRaceTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewRaceTableFrame();
            }
        });
        panel2.add(viewRaceTableButton);
    }

    /**
     * Method used to Add all the Race Details
     * @param searchName
     */
    public void drawDriverRaceTable(String searchName) {
        ArrayList<Races> tempRaces = new ArrayList<>(F1Manager.RacesList);
        ArrayList<DriversnRaces> raceDrivers = new ArrayList<>();
        for (Races r: tempRaces) {
            ArrayList<Formula1Driver> driverSearch = r.getRaceEndPositions();
            for (Formula1Driver d: driverSearch){
                if (d.getName().contains(searchName)){
                    DriversnRaces rd =new DriversnRaces();
                    rd.setDriverName(d.getName());
                    rd.setRaceDate(r.getDate());
                    rd.setRaceName(r.getName());
                    rd.setStartPosition(r.getRacePositions().indexOf(d)+1);
                    rd.setEndPosition(r.getRaceEndPositions().indexOf(d)+1);
                    raceDrivers.add(rd);

                }
            }
        }
        driverRaceTableModel.setRowCount(0);
        for (int i = 0; i < raceDrivers.size(); i++) {
            Object[] data = new Object[]{
                    raceDrivers.get(i).getDriverName(),
                    raceDrivers.get(i).getRaceName(),
                    raceDrivers.get(i).getRaceDate(),
                    raceDrivers.get(i).getStartPosition(),
                    raceDrivers.get(i).getEndPosition()

            };
            driverRaceTableModel.addRow(data);
        }
        raceTablePanel.setVisible(false);
        driverRaceTablePanel.setVisible(true);
        raceTableFrame.remove(raceTablePanel);
        raceTableFrame.add(driverRaceTablePanel);
        raceTableFrame.revalidate();
        raceTableFrame.repaint();
    }

    public void drawRaceTable() {
        raceTableModel.setRowCount(0);
        for (int i = 0; i < F1Manager.RacesList.size(); i++) {
            Object[] data = new Object[]{
                    F1Manager.RacesList.get(i).getName(),
                    F1Manager.RacesList.get(i).getDate(),
                    F1Manager.RacesList.get(i).getRaceEndPositions().size()
            };
            raceTableModel.addRow(data);
        }
        driverRaceTablePanel.setVisible(false);
        raceTablePanel.setVisible(true);
        raceTableFrame.remove(driverRaceTablePanel);
        raceTableFrame.add(raceTablePanel);
        raceTableFrame.revalidate();
        raceTableFrame.repaint();
    }

    /**
     * Method used to Display the Race Table Frame
     */
    public void viewRaceTableFrame() {
        raceTableFrame.setVisible(true);
        panel4 = new JPanel();
        panel4.setBackground(new Color(0x54ae2d));
        panel4.setBounds(0, 0, 1000, 250);
        panel4.setLayout(new FlowLayout());

        raceTablePanel = new JPanel();
        raceTablePanel.setBackground(new Color(0xf4dc27));
        raceTablePanel.setBounds(0, 250, 1000, 400);
        raceTablePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Race Statistics", TitledBorder.CENTER, TitledBorder.TOP, Font.getFont("Arial"), Color.getColor("0xFFFFFF")));
        raceTablePanel.setLayout(new BorderLayout());

        driverRaceTablePanel = new JPanel();
        driverRaceTablePanel.setBackground(new Color(0xf4dc27));
        driverRaceTablePanel.setBounds(0, 250, 1000, 400);
        driverRaceTablePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Driver Search", TitledBorder.CENTER, TitledBorder.TOP, Font.getFont("Arial"), Color.getColor("0xFFFFFF")));
        driverRaceTablePanel.setLayout(new BorderLayout());

        panel5 = new JPanel();
        panel5.setBackground(new Color(0x54ae2d));
        panel5.setBounds(0, 650, 1000, 100);
        panel5.setLayout(new FlowLayout());

        addSearchBar();

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
        Heading = new String[]{"Race Name", "Race Date", "Number of Drivers"};
        raceTableModel = new DefaultTableModel(0, 0);
        raceTable = new JTable(raceTableModel);
        raceTableModel.setColumnIdentifiers(Heading);
        raceTable.setModel(raceTableModel);

        String[] raceTableHeading = new String[]{"Race Name", "Race Date", "Number of Drivers"};
        raceTableModel = new DefaultTableModel(0, 0);
        raceTable = new JTable(raceTableModel);
        raceTableModel.setColumnIdentifiers(raceTableHeading);
        raceTable.setModel(raceTableModel);

        String[] driverRaceTableHeading = new String[]{"Driver Name", "Race Name", "Race Date", "Starting Position", "Ending Position"};
        driverRaceTableModel = new DefaultTableModel(0, 0);
        driverRaceTable = new JTable(driverRaceTableModel);
        driverRaceTableModel.setColumnIdentifiers(driverRaceTableHeading);
        driverRaceTable.setModel(driverRaceTableModel);

        Comparator<Races> comparator = sortRacebyDate();
        F1Manager.RacesList.sort(comparator);
        drawRaceTable();

        raceTable.setBounds(0, 250, 1000, 400);
        raceTable.setOpaque(true);
        JScrollPane sp3 = new JScrollPane(raceTable);
        sp3.setBounds(0, 250, 1000, 400);
        raceTablePanel.add(sp3);

        driverRaceTable.setBounds(0, 250, 1000, 400);
        driverRaceTable.setOpaque(true);
        JScrollPane sp4 = new JScrollPane(driverRaceTable);
        sp4.setBounds(0, 250, 1000, 400);
        driverRaceTablePanel.add(sp4);

        panel4.add(label);
        raceTableFrame.add(panel4);
        //raceTableFrame.add(raceTablePanel);
        raceTableFrame.add(panel5);
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














