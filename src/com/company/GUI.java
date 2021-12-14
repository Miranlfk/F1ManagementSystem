package com.company;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.text.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.swing.*;
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
    String[] Heading;
    JTable driverTable;
    JTable newRaceDetailsTable;
    JTable raceTable;
    DefaultTableModel driverTableModel;
    DefaultTableModel newRaceDetailsModel;
    DefaultTableModel raceTableModel;

    Formula1ChampionshipManager F1obj;

    public GUI(ArrayList<Formula1Driver> inDriverstats, ArrayList<Races> Races) {
        F1obj = new Formula1ChampionshipManager(inDriverstats, Races);


        driverTableFrame = new JFrame();
        addNewRaceFrame = new JFrame();
        raceTableFrame = new JFrame();

        addNewRaceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        raceTableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        driverTableFrame.setSize(1000, 800);
        driverTableFrame.setTitle("Formula1 Championship ");
        driverTableFrame.setLayout(null);
        driverTableFrame.setVisible(true);

        //Setting up panels and tables for Frame 1
        panel1 = new JPanel();
        panel1.setBackground(new Color(0x2E8BC0));
        panel1.setBounds(0, 0, 1000, 250);
        panel1.setLayout(new BorderLayout());

        driverTablePanel = new JPanel();
        driverTablePanel.setBackground(new Color(0x2E8BC0));
        driverTablePanel.setBounds(0, 250, 1000, 400);
        driverTablePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Driver Statistics", TitledBorder.CENTER, TitledBorder.TOP, Font.getFont("SANS_SERIF"), Color.getColor("0x75E6DA")));
        driverTablePanel.setLayout(new BorderLayout());

        panel2 = new JPanel();
        panel2.setBackground(new Color(0x2E8BC0));
        panel2.setBounds(0, 650, 1000, 100);
        panel2.setLayout(new FlowLayout());
        ImageIcon logo = new ImageIcon("logo.png");

        JLabel label = new JLabel("Formula1 Championship 2021");
        label.setBounds(40, 50, 50, 70);
        label.setFont(new Font("SANS_SERIF", Font.ITALIC | Font.BOLD, 40));
        label.setForeground(new Color(0x0C2D48));
        label.setIcon(logo);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setIconTextGap(-200);

        panel1.add(label);

        //displays the driver driverTable
        displayDriverTable();
        driverTableFrame.revalidate();
        driverTableFrame.repaint();


        //driver driverTable sort buttons
        addSortPointButton();
        addSortPlacesButton();

        driverTableFrame.add(panel1);
        driverTableFrame.add(panel2);
        driverTableFrame.add(driverTablePanel);

        // setting up addNewRaceFrame to add a race
        addNewRaceFrame.setSize(1000, 900);
        addNewRaceFrame.setTitle("Formula1 Championship New Race");
        addNewRaceFrame.setLayout(null);
        addNewRaceFrame.setVisible(false);

        //button displays random new race and updates existing drivers
        addRandomRaceButton();
        //button displays new race using stats and updates existing drivers
        addStatRaceButton();

        raceTableFrame.setSize(1000, 900);
        raceTableFrame.setTitle("Formula1 Championship Race Table");
        raceTableFrame.setLayout(null);
        raceTableFrame.setVisible(false);
        //button displays new frame with table of races and searchbar
        addViewRaceTableButton();
    }

    //displays the driver driverTable
    public void displayDriverTable() {
        //JTable Column
        Heading = new String[]{"NAME OF DRIVER", "Team Name", "Location", "First Positions", "Second Positions", "Third Positions", "Total Points", "Number Of Participated Races"};

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

    //button that sort according to ascending on points
    public void addSortPointButton() {
        JButton btn1 = new JButton("Ascending Points");
        btn1.setBounds(200, 20, 40, 15);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Comparator<Formula1Driver> comparator = new Comparator<Formula1Driver>() {
                    public int compare(Formula1Driver driver1, Formula1Driver driver2) {
                        if (driver1.getNoPoints() > driver2.getNoPoints()) {
                            return 1;
                        } else if (driver1.getNoPoints() == driver2.getNoPoints()) {
                            if (driver1.getNoFirst() > driver2.getNoFirst()) {
                                return 1;
                            } else {
                                return -1;
                            }

                        } else {
                            return -1;
                        }
                    }
                };
                F1obj.DriverStats.sort(comparator);
                drawDriverTable();
            }
        });
        panel2.add(btn1);
    }

    //button that sort according to descending on First Position
    public void addSortPlacesButton() {
        JButton btn2 = new JButton("Descending to FirstPosition");
        btn2.setBounds(250, 20, 40, 15);
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Comparator<Formula1Driver> comparator = new Comparator<Formula1Driver>() {
                    public int compare(Formula1Driver driver1, Formula1Driver driver2) {
                        if (driver1.getNoFirst() > driver2.getNoFirst()) {
                            return -1;
                        } else {
                            return 1;
                        }
                    }
                };
                F1obj.DriverStats.sort(comparator);
                drawDriverTable();
            }
        });
        panel2.add(btn2);
    }

    public void drawDriverTable() {
        driverTableModel.setRowCount(0);
        for (int x = 0; x < F1obj.DriverStats.size(); x++) {
            Object[] data = new Object[]{
                    F1obj.DriverStats.get(x).getName(),
                    F1obj.DriverStats.get(x).getTeam(),
                    F1obj.DriverStats.get(x).getLocation(),
                    F1obj.DriverStats.get(x).getNoFirst(),
                    F1obj.DriverStats.get(x).getNoSecond(),
                    F1obj.DriverStats.get(x).getNoThird(),
                    F1obj.DriverStats.get(x).getNoPoints(),
                    F1obj.DriverStats.get(x).getNoRaces()
            };
            driverTableModel.addRow(data);
            driverTableFrame.repaint();
        }
    }

    public void addRandomRaceButton() {
        JButton randomRaceButton = new JButton("Add Random Race");
        randomRaceButton.setBounds(300, 20, 40, 15);
        randomRaceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Races newRace = calculateRandomRace();
                String title = "Added New Random Race";
                updateDriverDetails(newRace);
                F1obj.ListofRaces.add(newRace);
                displayNewRaceRetails(newRace, title);
                drawDriverTable();
            }
        });
        panel2.add(randomRaceButton);
    }

    public void addStatRaceButton() {
        JButton statRaceButton = new JButton("Add Stat Race");
        statRaceButton.setBounds(350, 20, 40, 15);
        statRaceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Races newRace = calculateStatRace();
                String title = "Added New Statistical Race";
                updateDriverDetails(newRace);
                F1obj.ListofRaces.add(newRace);
                displayNewRaceRetails(newRace, title);
                drawDriverTable();
            }
        });
        panel2.add(statRaceButton);
    }

    public void addSearchBar() {
        JTextField textField = new JTextField(20);
        textField.setSize(200,24);
        JButton searchButton = new JButton("Search");
        searchButton.setBounds(200, 20, 40, 15);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String getValue = textField.getText();
                drawRaceTable(getValue);
                textField.setText("");
            }
        });
        panel5.add(textField);
        panel5.add(searchButton);

    }

    public String getRaceName(){
        String nameTemp = "Race";
        return nameTemp+ " " +(1+F1obj.ListofRaces.size());
    }

    public Races calculateRandomRace() {
        Races newRace = new Races();
        Dates newDate = new Dates();
        String name = getRaceName();
        newRace.setName(name);
        newRace.setDate(newDate.getRandomDate());
        ArrayList<Formula1Driver> startingPositions = new ArrayList<>(F1obj.DriverStats);
        ArrayList<Formula1Driver> endingPositions = new ArrayList<>(F1obj.DriverStats);
        Collections.shuffle(startingPositions);
        Collections.shuffle(endingPositions);
        newRace.setRacePositions(startingPositions);
        newRace.setRaceDetails(endingPositions);
        return newRace;
    }


    public Races calculateStatRace() {
        Races newRace = new Races();
        Dates newDate = new Dates();
        String name = getRaceName();
        newRace.setName(name);
        newRace.setDate(newDate.getRandomDate());
        ArrayList<Formula1Driver> startingPositions = new ArrayList<>(F1obj.DriverStats);
        ArrayList<Formula1Driver> endingPositionsTemp = new ArrayList<>(F1obj.DriverStats);
        Collections.shuffle(startingPositions);
        int chance = new Random().nextInt(100);
        int winningPosition;
        if (chance < 40) {
            winningPosition = 1;
        } else if (chance < 70) {
            winningPosition = 2;
        } else if (chance < 80) {
            winningPosition = 3;
        } else if (chance < 90) {
            winningPosition = 4;
        } else if (chance < 92) {
            winningPosition = 5;
        } else if (chance < 94) {
            winningPosition = 6;
        } else if (chance < 96) {
            winningPosition = 7;
        } else if (chance < 98) {
            winningPosition = 8;
        } else {
            winningPosition = 9;
        }
        if (winningPosition > F1obj.DriverStats.size()){
            winningPosition = F1obj.DriverStats.size();
        }
        Formula1Driver Driver1 = startingPositions.get(winningPosition-1);
        System.out.println(Driver1.getName());
        for (int i = 0 ; i < endingPositionsTemp.size(); i++){
            if (endingPositionsTemp.get(i).getTeam().equals(Driver1.getTeam())){
                endingPositionsTemp.remove(i);
            }
        }
        ArrayList<Formula1Driver> endingPositionsFinal = new ArrayList<>();
        endingPositionsFinal.add(Driver1);
        endingPositionsFinal.addAll(endingPositionsTemp);
        newRace.setRacePositions(startingPositions);
        newRace.setRaceDetails(endingPositionsFinal);

        return newRace;
    }

    //TODO complete function to update the DriverList using new race
    public void updateDriverDetails(Races newRace) {
        // Example code:
        int Points [] = {25, 18, 15, 12, 10, 8, 6, 4, 2, 1};
        for (int i = 0; i < F1obj.ListofRaces.size();i++){
            newRace.getRaceDetails().get(i).setNoPoints(newRace.getRaceDetails().get(i).getNoPoints()+Points[i]); //doesnt work properly
            newRace.getRaceDetails().get(i).setNoRaces(newRace.getRaceDetails().get(i).getNoRaces() + 1);
            if (Points[i] == 25){
                newRace.getRaceDetails().get(i).setNoFirst(newRace.getRaceDetails().get(i).getNoFirst() + 1);
            } else if (Points[i] == 18){
                newRace.getRaceDetails().get(i).setNoSecond(newRace.getRaceDetails().get(i).getNoSecond() + 1);
            } else if (Points[i] == 15){
                newRace.getRaceDetails().get(i).setNoThird(newRace.getRaceDetails().get(i).getNoThird() + 1);
            }
        }

    }

    public void displayNewRaceRetails(Races newRace, String title) {
        addNewRaceFrame.setVisible(true);
        panel3 = new JPanel();
        panel3.setBackground(new Color(0x2E8BC0));
        panel3.setBounds(0, 650, 1000, 100);
        panel3.setLayout(new FlowLayout());

        newRaceDetailsPanel = new JPanel();
        newRaceDetailsPanel.setBackground(new Color(0x2E8BC0));
        newRaceDetailsPanel.setBounds(0, 250, 1000, 400);
        newRaceDetailsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Driver Statistics", TitledBorder.CENTER, TitledBorder.TOP, Font.getFont("SANS_SERIF"), Color.getColor("0x75E6DA")));
        newRaceDetailsPanel.setLayout(new BorderLayout());

        // TODO format labels
        // TODO put new label title to show title of page(use title variable)
        JLabel label = new JLabel("Race Name: " + newRace.getName() + "  Date: " + newRace.getDate());
        label.setBounds(40, 50, 50, 70);
        label.setFont(new Font("SANS_SERIF", Font.ITALIC | Font.BOLD, 40));
        label.setForeground(new Color(0x0C2D48));
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

        //array list to store the ending positions using starting positions of a driver
        ArrayList<String> endingPos = new ArrayList<>();
        for (int i = 0; i < newRace.getRacePositions().size(); i++) {
            for (int j = 0; j < newRace.getRaceDetails().size(); j++) {
                if (newRace.getRacePositions().get(i).getTeam().equals(newRace.getRaceDetails().get(j).getTeam())) {
                    endingPos.add(Integer.toString(j + 1));
                }
            }
        }

        //Adding data to JTable
        for (int i = 0; i < newRace.getRacePositions().size(); i++) {
            Object[] data = new Object[]{
                    newRace.getRacePositions().get(i).getName(),
                    i + 1,
                    endingPos.get(i),
                    newRace.getRacePositions().get(i).getTeam()
            };
            newRaceDetailsModel.addRow(data);
        }
        newRaceDetailsTable.setBounds(0, 250, 1000, 400);
        newRaceDetailsTable.setOpaque(true);
        JScrollPane sp2 = new JScrollPane(newRaceDetailsTable);
        sp2.setBounds(0, 250, 1000, 400);
        newRaceDetailsPanel.add(sp2);
        panel3.add(label);
        addNewRaceFrame.add(panel3);
        addNewRaceFrame.add(newRaceDetailsPanel);

    }

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

    public void drawRaceTable(String searchName) {
        ArrayList<Races> tempRaces = new ArrayList<>(F1obj.ListofRaces);
        ArrayList<Races> searchRaces = new ArrayList<>();
        ArrayList<RaceDrivers> raceDrivers = new ArrayList<>();

        if (!(searchName==null || searchName.isEmpty())) {
            for (Races r: tempRaces) {
                ArrayList<Formula1Driver> driverSearch = r.getRaceDetails();
                for (Formula1Driver d: driverSearch){
                    if (d.getName().contains(searchName)){
                        RaceDrivers rd =new RaceDrivers();
                        rd.setDriverName(d.getName());
                        rd.setRaceDate(r.getDate());
                        rd.setRaceName(r.getName());
                        rd.setStartPosition(r.getRacePositions().indexOf(d));
                        rd.setEndPosition(r.getRaceDetails().indexOf(d));
                        raceDrivers.add(rd);

                    }
                }
            }
            Heading = new String[]{"Driver Name", "Race Name", "Race Date", "Starting Position", "Ending Position"};
            raceTableModel.setRowCount(0);

            for (int i = 0; i < raceDrivers.size(); i++) {
                Object[] data = new Object[]{
                        raceDrivers.get(i).getDriverName(),
                        raceDrivers.get(i).getRaceName(),
                        raceDrivers.get(i).getRaceDate(),
                        raceDrivers.get(i).getStartPosition(),
                        raceDrivers.get(i).getEndPosition()

                };
                raceTableModel.addRow(data);
            }

        } else {
            Heading = new String[]{"Race Name", "Race Date", "Number of Drivers"};
            searchRaces = new ArrayList<>(F1obj.ListofRaces);

            //sort searchRaces arraylist by Date
            Comparator<Races> comparator = sortRacebyDate();
            searchRaces.sort(comparator);
            raceTableModel.setRowCount(0);

            for (int i = 0; i < searchRaces.size(); i++) {
                Object[] data = new Object[]{
                        searchRaces.get(i).getName(),
                        searchRaces.get(i).getDate(),
                        searchRaces.get(i).getRaceDetails().size()

                };
                raceTableModel.addRow(data);
            }


        }


    }

    public void viewRaceTableFrame() {
        raceTableFrame.setVisible(true);
        panel4 = new JPanel();
        panel4.setBackground(new Color(0x2E8BC0));
        panel4.setBounds(0, 0, 1000, 250);
        panel4.setLayout(new FlowLayout());

        raceTablePanel = new JPanel();
        raceTablePanel.setBackground(new Color(0x2E8BC0));
        raceTablePanel.setBounds(0, 250, 1000, 400);
        raceTablePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Driver Statistics", TitledBorder.CENTER, TitledBorder.TOP, Font.getFont("SANS_SERIF"), Color.getColor("0x75E6DA")));
        raceTablePanel.setLayout(new BorderLayout());

        panel5 = new JPanel();
        panel5.setBackground(new Color(0x2E8BC0));
        panel5.setBounds(0, 650, 1000, 100);
        panel5.setLayout(new FlowLayout());

        addSearchBar();

        // TODO format labels
        JLabel label = new JLabel("View Race Details");
        label.setBounds(40, 50, 50, 70);
        label.setFont(new Font("SANS_SERIF", Font.ITALIC | Font.BOLD, 40));
        label.setForeground(new Color(0x0C2D48));
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setIconTextGap(-200);

        //JTable Column
        raceTableModel = new DefaultTableModel(0, 0);
        raceTable = new JTable(raceTableModel);
        raceTableModel.setColumnIdentifiers(Heading);
        raceTable.setModel(raceTableModel);

        drawRaceTable("");

        raceTable.setBounds(0, 250, 1000, 400);
        raceTable.setOpaque(true);
        JScrollPane sp3 = new JScrollPane(raceTable);
        sp3.setBounds(0, 250, 1000, 400);
        raceTablePanel.add(sp3);

        panel4.add(label);
        raceTableFrame.add(panel4);
        raceTableFrame.add(raceTablePanel);
        raceTableFrame.add(panel5);

    }
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










