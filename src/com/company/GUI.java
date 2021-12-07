package com.company;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class GUI extends JFrame {


    JFrame frame1;
    JFrame frame2;
    JPanel panel1;
    JPanel panel2;
    JPanel panelTable;
    JPanel panel3;
    JPanel panel4;
    JPanel panel5;
    JPanel panel6;
    String[] Heading;
    JTable table;
    JTable dateInfo;
    JTable raceDates;
    DefaultTableModel tableModel;
    DefaultTableModel FindModel;
    DefaultTableModel DateModel;

    int raceCount = 0;

    Formula1ChampionshipManager F1obj = new Formula1ChampionshipManager();
    ArrayList<Races> newRaceInfo = new ArrayList<>();


    GUI() {

        frame1 = new JFrame();
        frame2 = new JFrame();

        frame1.setSize(1000, 800);
        frame1.setTitle("Formula1 Championship ");
        frame1.setLayout(null);
        frame1.setVisible(true);

        //Setting up panels and tables for Frame 1
        panel1 = new JPanel();
        panel1.setBackground(new Color(0x2E8BC0));
        panel1.setBounds(0, 0, 1000, 250);
        panel1.setLayout(new BorderLayout());

        panelTable = new JPanel();
        panelTable.setBackground(new Color(0x2E8BC0));
        panelTable.setBounds(0, 250, 1000, 400);
        panelTable.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Driver Statistics", TitledBorder.CENTER, TitledBorder.TOP, Font.getFont("SANS_SERIF"), Color.getColor("0x75E6DA")));
        panelTable.setLayout(new BorderLayout());


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

        //JTable Column
        Heading = new String[]{"NAME OF DRIVER", "Team Name", "Location", "First Positions", "Second Positions", "Third Positions", "Total Points", "Number Of Participated Races"};

        tableModel = new DefaultTableModel(0, 0);
        table = new JTable(tableModel);
        tableModel.setColumnIdentifiers(Heading);
        table.setModel(tableModel);

        //Adding data to JTable
        for (int i = 0; i < F1obj.DriverStats.size(); i++) {
            Object[] data = new Object[]{
                    F1obj.DriverStats.get(i).getName(),
                    F1obj.DriverStats.get(i).getTeam(),
                    F1obj.DriverStats.get(i).getLocation(),
                    F1obj.DriverStats.get(i).getNoFirst(),
                    F1obj.DriverStats.get(i).getNoSecond(),
                    F1obj.DriverStats.get(i).getNoThird(),
                    F1obj.DriverStats.get(i).getNoPoints(),
                    F1obj.DriverStats.get(i).getNoRaces()
            };

            tableModel.addRow(data);

        }

        table.setBounds(0, 250, 1000, 400);
        table.setOpaque(true);
        JScrollPane sp1 = new JScrollPane(table);
        sp1.setBounds(0, 250, 1000, 400);


        //button that sort according to ascending on points
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


                                       tableModel.setRowCount(0);
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

                                           tableModel.addRow(data);

                                       }

                                   }
                               }
        );
        //button that sort according to descending on First Position
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


                tableModel.setRowCount(0);
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
                    tableModel.addRow(data);
                }

            }

        });
        panelTable.add(sp1);
        panel2.add(btn1);
        panel2.add(btn2);
        frame1.add(panel1);
        frame1.add(panel2);
        frame1.add(panelTable);

        frame2.setSize(1000, 900);
        frame2.setTitle("Formula1 Championship Race Info");
        frame2.setLayout(null);
        frame2.setVisible(false);


        //Button that showing JFrame 2 and Adding a new Race
        JButton AddRace = new JButton("Add Race");
        AddRace.setBounds(290, 20, 40, 15);
        panel2.add(AddRace);
        AddRace.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frame2.setVisible(true);


                Heading = new String[]{"NAME OF DRIVER", "Team Name", "Location", "Starting Position", "First Positions", "Second Positions", "Third Positions", "Total Points", "Number Of Participated Races"};

                tableModel = new DefaultTableModel(0, 0);
                table = new JTable(tableModel);


                tableModel.setColumnIdentifiers(Heading);
                table.setModel(tableModel);

                //Generate Random starting Positions and Validating Overlapped positions
                LinkedHashSet<Integer> startPositionStore = new LinkedHashSet<>();

                for (int x = 0; x < F1obj.DriverStats.size(); x++) {
                    int position = (int) (Math.random() * F1obj.DriverStats.size() - 1 + 1) + 1;
                    startPositionStore.add(position);
                }
                boolean equality = true;

                //Adding unique positions to LinkedHashSet
                while (equality) {
                    if (startPositionStore.size() != F1obj.DriverStats.size()) {
                        int newPosition = (int) (Math.random() * F1obj.DriverStats.size() - 1 + 1) + 1;
                        if (!startPositionStore.contains(newPosition)) {
                            startPositionStore.add(newPosition);
                        }
                    } else {
                        equality = false;
                    }
                }

                Integer[] positionArray = startPositionStore.toArray(new Integer[startPositionStore.size()]);//Converting HashSet to an Array

                //Adding new info to table
                for (int x = 0; x < F1obj.DriverStats.size(); x++) {

                    Object[] data = new Object[]{
                            F1obj.DriverStats.get(x).getName(),
                            F1obj.DriverStats.get(x).getTeam(),
                            F1obj.DriverStats.get(x).getLocation(),
                            positionArray[x],
                            F1obj.DriverStats.get(x).getNoFirst(),
                            F1obj.DriverStats.get(x).getNoSecond(),
                            F1obj.DriverStats.get(x).getNoThird(),
                            F1obj.DriverStats.get(x).getNoPoints(),
                            F1obj.DriverStats.get(x).getNoRaces()
                    };


                    tableModel.addRow(data);

                }
                table.setBounds(0, 0, 1000, 200);
                table.setOpaque(true);
                JScrollPane sp2 = new JScrollPane(table);
                sp2.setBounds(0, 0, 1000, 200);

                //Setting up Frame 2 panels and Buttons
                panel3 = new JPanel();
                panel3.setBackground(new Color(0x2E8BC0));
                panel3.setBounds(0, 0, 1000, 250);
                panel3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Race Statistics", TitledBorder.CENTER, TitledBorder.TOP, Font.getFont("SANS_SERIF"), Color.getColor("0x75E6DA")));
                panel3.setLayout(new BorderLayout());


                panel4 = new JPanel(new FlowLayout());
                panel4.setBackground(new Color(0x2E8BC0));
                panel4.setBounds(0, 250, 1000, 100);
                panel4.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "***************", TitledBorder.CENTER, TitledBorder.TOP, Font.getFont("SANS_SERIF"), Color.getColor("0x75E6DA")));


                panel5 = new JPanel();
                panel5.setBackground(new Color(0x2E8BC0));
                panel5.setBounds(0, 350, 1000, 250);
                panel5.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Race Dates", TitledBorder.CENTER, TitledBorder.TOP, Font.getFont("SANS_SERIF"), Color.getColor("0x75E6DA")));
                panel5.setLayout(new BorderLayout());

                panel6 = new JPanel();
                panel6.setBackground(new Color(0x2E8BC0));
                panel6.setBounds(0, 600, 1000, 250);
                panel6.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Race History", TitledBorder.CENTER, TitledBorder.TOP, Font.getFont("SANS_SERIF"), Color.getColor("0x75E6DA")));
                panel6.setLayout(new BorderLayout());


                JButton StartRace = new JButton("StartRace");
                StartRace.setBounds(200, 300, 400, 250);
                ImageIcon StartIcon = new ImageIcon("Start.png");
                StartRace.setBounds(200, 300, 400, 250);
                StartRace.setFocusable(false);
                StartRace.setIcon(StartIcon);

                //Generating Random Final positions And Validating Positions
                StartRace.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {

                                                    raceCount++;

                                                    int final_position = 0;

                                                    int random_position = (int) (Math.random() * 100);


                                                    LinkedHashSet<Integer> finalPositionStore = new LinkedHashSet<>();
                                                    boolean checkSize = true;
                                                    while (checkSize) {


                                                        for (int x = 0; x < positionArray.length; x++) {
                                                            if (positionArray[x] == 1) {
                                                                if (random_position <= 40) {
                                                                    final_position = 1;
                                                                } else {
                                                                    final_position = (int) (Math.random() * F1obj.DriverStats.size() - 2 + 1) + 2;
                                                                }
                                                            } else if (positionArray[x] == 2) {
                                                                if (random_position <= 70 && random_position > 40) {
                                                                    final_position = 1;
                                                                } else {
                                                                    final_position = (int) (Math.random() * F1obj.DriverStats.size() - 2 + 1) + 2;
                                                                }
                                                            } else if (positionArray[x] == 3) {
                                                                if (random_position <= 80 && random_position > 70) {
                                                                    final_position = 1;
                                                                } else {
                                                                    final_position = (int) (Math.random() * F1obj.DriverStats.size() - 2 + 1) + 2;
                                                                }
                                                            } else if (positionArray[x] == 4) {
                                                                if (random_position <= 90 && random_position > 80) {
                                                                    final_position = 1;
                                                                } else {
                                                                    final_position = (int) (Math.random() * F1obj.DriverStats.size() - 2 + 1) + 2;
                                                                }
                                                            } else if (positionArray[x] == 5) {
                                                                if (random_position <= 92 && random_position > 90) {
                                                                    final_position = 1;
                                                                } else {
                                                                    final_position = (int) (Math.random() * F1obj.DriverStats.size() - 2 + 1) + 2;
                                                                }
                                                            } else if (positionArray[x] == 6) {
                                                                if (random_position <= 94 && random_position > 92) {
                                                                    final_position = 1;
                                                                } else {
                                                                    final_position = (int) (Math.random() * F1obj.DriverStats.size() - 2 + 1) + 2;
                                                                }

                                                            } else if (positionArray[x] == 7) {
                                                                if (random_position <= 96 && random_position > 94) {
                                                                    final_position = 1;
                                                                } else {
                                                                    final_position = (int) (Math.random() * F1obj.DriverStats.size() - 2 + 1) + 2;
                                                                }
                                                            } else if (positionArray[x] == 8) {
                                                                if (random_position <= 98 && random_position > 96) {
                                                                    final_position = 1;
                                                                } else {
                                                                    final_position = (int) (Math.random() * F1obj.DriverStats.size() - 2 + 1) + 2;
                                                                }
                                                            } else if (positionArray[x] == 9) {
                                                                if (random_position <= 100 && random_position > 98) {
                                                                    final_position = 1;
                                                                } else {
                                                                    final_position = (int) (Math.random() * F1obj.DriverStats.size() - 2 + 1) + 2;
                                                                }
                                                            }
                                                            finalPositionStore.add(final_position);
                                                            if (finalPositionStore.size() == F1obj.DriverStats.size()) {
                                                                checkSize = false;
                                                            }
                                                            //updating player Statistics


                                                        }
                                                    }
                                                    Integer[] finalPositionsArray = finalPositionStore.toArray(new Integer[finalPositionStore.size()]);
                                                    for (int x = 0; x < finalPositionsArray.length; x++) {
                                                        F1obj.DriverStats.get(x).setNoRaces(F1obj.DriverStats.get(x).getNoRaces() + 1);
                                                        switch (finalPositionsArray[x]) {
                                                            case 1 -> {
                                                                F1obj.DriverStats.get(x).setNoPoints(F1obj.DriverStats.get(x).getNoPoints() + 25);
                                                                F1obj.DriverStats.get(x).setNoFirst(F1obj.DriverStats.get(x).getNoFirst() + 1);

                                                            }
                                                            case 2 -> {
                                                                F1obj.DriverStats.get(x).setNoPoints(F1obj.DriverStats.get(x).getNoPoints() + 18);
                                                                F1obj.DriverStats.get(x).setNoSecond(F1obj.DriverStats.get(x).getNoSecond() + 1);

                                                            }
                                                            case 3 -> {
                                                                F1obj.DriverStats.get(x).setNoPoints(F1obj.DriverStats.get(x).getNoPoints() + 15);
                                                                F1obj.DriverStats.get(x).setNoThird(F1obj.DriverStats.get(x).getNoThird() + 1);

                                                            }
                                                            case 4 -> F1obj.DriverStats.get(x).setNoPoints(F1obj.DriverStats.get(x).getNoPoints() + 12);
                                                            case 5 -> F1obj.DriverStats.get(x).setNoPoints(F1obj.DriverStats.get(x).getNoPoints() + 10);
                                                            case 6 -> F1obj.DriverStats.get(x).setNoPoints(F1obj.DriverStats.get(x).getNoPoints() + 8);
                                                            case 7 -> F1obj.DriverStats.get(x).setNoPoints(F1obj.DriverStats.get(x).getNoPoints() + 6);
                                                            case 8 -> F1obj.DriverStats.get(x).setNoPoints(F1obj.DriverStats.get(x).getNoPoints() + 4);
                                                            case 9 -> F1obj.DriverStats.get(x).setNoPoints(F1obj.DriverStats.get(x).getNoPoints() + 2);
                                                            case 10 -> F1obj.DriverStats.get(x).setNoPoints(F1obj.DriverStats.get(x).getNoPoints() + 1);

                                                        }
                                                    }
                                                    finalPositionStore.clear();


                                                    Heading = new String[]{"NAME OF DRIVER", "Team Name", "Location", "Starting Position", "Final position", "First Positions", "Second Positions", "Third Positions", "Total Points", "Number Of Participated Races", "Date"};
                                                    tableModel.setRowCount(0);
                                                    tableModel.setColumnCount(0);
                                                    tableModel.setColumnIdentifiers(Heading);

                                                    DateFormat formatter = new SimpleDateFormat("dd-MM-YYYY");
                                                    Date date = new Date();


                                                }
                                            }
                );

            }

        });
        validate();
    }
}


