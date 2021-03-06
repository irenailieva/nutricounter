package com.irenailieva.nutricounter.util;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static LocalDate convertToLocalDate(String date) throws DateTimeException {
        String[] dateFragments = date.split("-");
        int dayOfMonth = Integer.parseInt(dateFragments[2]);
        int month = Integer.parseInt(dateFragments[1]);
        int year = Integer.parseInt(dateFragments[0]);
        return LocalDate.of(year, month, dayOfMonth);
    }

    public static Date convertToDate(LocalDate localDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, localDate.getYear());
        calendar.set(Calendar.MONTH, localDate.getMonthValue());
        calendar.set(Calendar.DAY_OF_MONTH, localDate.getDayOfMonth());
        return calendar.getTime();
    }

    public static Date convertToDate(String dateAsString) {
        Calendar calendar = Calendar.getInstance();
        String[] dateArgs = dateAsString.split("-");
        int year = Integer.parseInt(dateArgs[0]);
        int month = Integer.parseInt(dateArgs[1]);
        int day = Integer.parseInt(dateArgs[2]);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        setTimeToZero(calendar);

        return calendar.getTime();
    }

    public static Date getToday() {
        Calendar today = Calendar.getInstance();
        today.setTime(new Date());
        setTimeToZero(today);
        return today.getTime();
    }

    public static String getDateAsString(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        StringBuilder builder = new StringBuilder();
        builder.append(calendar.get(Calendar.YEAR))
                .append("/")
                .append(calendar.get(Calendar.MONTH) + 1)
                .append("/")
                .append(calendar.get(Calendar.DAY_OF_MONTH));

        return builder.toString();
    }

    public static String getLocalDateAsString(LocalDate date) {
        return date.getYear() + "-" + date.getMonthValue() + "-" + date.getDayOfMonth();
    }

    private static void setTimeToZero(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }
}
