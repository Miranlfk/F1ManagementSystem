package com.company;

import java.util.GregorianCalendar;

public class Dates {
    public String getRandomDate() {
        GregorianCalendar gc = new GregorianCalendar();
        int year = randBetween(2021, 2025);
        gc.set(GregorianCalendar.YEAR, year);
        int dayOfYear = randBetween(1, gc.getActualMaximum(GregorianCalendar.DAY_OF_YEAR));
        gc.set(GregorianCalendar.DAY_OF_YEAR, dayOfYear);
        return(gc.get(GregorianCalendar.DAY_OF_MONTH)+ "/" + (gc.get(GregorianCalendar.MONTH) + 1) + "/" + gc.get(GregorianCalendar.YEAR));
    }
    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }


}



