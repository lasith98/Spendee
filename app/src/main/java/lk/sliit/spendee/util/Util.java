package lk.sliit.spendee.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 * author: Lasith Hansana
 * date: 5/9/2021
 * time: 2:02 PM
 */
public class Util {

    public static String convertDateSQLIteFormat(String date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        try {
            date = simpleDateFormat.format(Objects.requireNonNull(simpleDateFormat.parse(date)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String formatYearOfMonth(String date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm");
        try {
            date = simpleDateFormat.format(Objects.requireNonNull(simpleDateFormat.parse(date)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
