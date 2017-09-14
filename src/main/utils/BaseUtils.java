package utils;

import java.util.Calendar;
import java.util.Date;

public class BaseUtils {

    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

}
