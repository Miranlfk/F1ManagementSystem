package com.company;

import java.util.GregorianCalendar;

public class Dates {

    /**
     * Method used to generate a random date between years 2021-2025
     * @return
     */
    public String getRandomDate() {
        GregorianCalendar gcDate = new GregorianCalendar();
        int year = randomNumBetween(2021, 2025);
        gcDate.set(GregorianCalendar.YEAR, year);
        int dayOfYear = randomNumBetween(1, gcDate.getActualMaximum(GregorianCalendar.DAY_OF_YEAR));
        gcDate.set(GregorianCalendar.DAY_OF_YEAR, dayOfYear);
        return(gcDate.get(GregorianCalendar.DAY_OF_MONTH)+ "/" + (gcDate.get(GregorianCalendar.MONTH) + 1) + "/" + gcDate.get(GregorianCalendar.YEAR));
    }

    /**
     * Method used to generate a random number
     * @param start
     * @param end
     * @return
     */
    public static int randomNumBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }


}



