package lt.ak.lunchvoter;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {

    private static final Logger log = Logger.getLogger(TimeUtil.class);

    public static Date today() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date parseTime(String strTime) {
        Date time = null;
        try {
            time = new SimpleDateFormat("HH:mm:ss").parse(strTime);
            Calendar calendarWithoutDate = Calendar.getInstance();
            calendarWithoutDate.setTime(time);
            Calendar calendarWithDate = Calendar.getInstance();
            calendarWithDate.set(Calendar.HOUR_OF_DAY, calendarWithoutDate.get(Calendar.HOUR_OF_DAY));
            calendarWithDate.set(Calendar.MINUTE, calendarWithoutDate.get(Calendar.MINUTE));
            calendarWithDate.set(Calendar.SECOND, calendarWithoutDate.get(Calendar.SECOND));
            calendarWithDate.set(Calendar.MILLISECOND, 0);
            return calendarWithDate.getTime();
        } catch (ParseException e) {
            log.warn(e.getMessage());
        }
        return null;
    }

    public static  Date now() {
        return Calendar.getInstance().getTime();
    }

}