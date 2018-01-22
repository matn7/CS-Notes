package basic_java.other;

import java.text.DateFormatSymbols;
import java.util.Calendar;

public class MainCalendar {

    public static void main(String[] args) {
        // Create a calendar with year and day of year.
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.DAY_OF_YEAR, 180);

        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.YEAR, 2018);
        cal2.set(Calendar.MONTH, 0);
        System.out.println("Check");
        System.out.println(cal2.getActualMaximum(Calendar.DAY_OF_MONTH));
        System.out.println(cal2.get(Calendar.DAY_OF_WEEK));

        int monthDuration = cal2.getActualMaximum(Calendar.DAY_OF_MONTH);
        int weekdays = cal2.get(Calendar.DAY_OF_WEEK);

        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] weekdayName = {"Sun", "Mon", "Tue", "Wed", "Thr", "Fri", "Sat", "Sun"};

        for (int i = 1; i <= monthDuration; i++) {
            System.out.println(dfs.getWeekdays()[weekdays] + " : " + weekdayName[weekdays-1]);
            weekdays++;
            if (weekdays % 8 == 0) {
                weekdays = 1;
            }
        }



        // See the full information of the calendar object.
        System.out.println(calendar.getTime().toString());

        // Get the weekday and print it
        int weekday = calendar.get(Calendar.DAY_OF_WEEK);
        System.out.println("Weekday: " + weekday);

        // Get weekday name

        System.out.println("Weekday: " + dfs.getWeekdays()[weekday]);
    }

}
